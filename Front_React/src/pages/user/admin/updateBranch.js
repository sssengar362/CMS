import { useEffect,useState } from 'react'
// import { Link } from 'react-router-dom'
import { toast } from 'react-toastify'
import config from '../../../config'
import axios from 'axios'
import { useLocation } from 'react-router-dom'

const UpdateBranch=(props)=>{
   const [details, setDetails] = useState({})
  // const[id,setId]=useState('')
  const [branchName, setBranchName] = useState('')
  const [location, setLocation] = useState('')
  const [pincode, setPincode] = useState('')
  const location1 = useLocation()
//   const navigate = useNavigate()
   
  useEffect(() => {
    // get the parameters (state object) sent by the previous page
    // navigate('/edit-home', { state: { homeId: id } })
    // the state passed in the above statement can be accessed by location.state
    const { branchId } = location.state

    // get the details of the home
    getDetails(branchId)
  }, [])

  const getDetails = (id) => {
    axios
      .get(config.serverURL + `/admin/getBranchById/${id}`, {
        // headers: { token: sessionStorage['token'] },
      })
      .then((response) => {
        const result = response.data
        if (result['status'] === 'success') {
          // set the details
          setDetails(result['data'])
          console.log(result['data'])
        } else {
          toast.error(result['error'])

          // if branch not found then to back to the previous page
        //   navigate('/showAllBranches')
        }
      })
  }

  
  const updateBranch = () => {
    // check if user has really entered any value
    if (branchName.length === 0) {
      toast.error('please enter branch name')
    } else if (location.length === 0) {
      toast.error('please enter location')
    } else if (pincode.length === 0) {
      toast.error('please enter pincode')
    }  else {
      // make the API call to check if user exists
      axios
        .put(config.serverURL + '/admin/updateBranchDetails', {
            
            branchName,
            location,
            pincode,
          
        })
        .then((response) => {
          // get the data returned by server
          const result = response.data

          // check if user's authentication is successfull
          if (result['status'] === 'error') {
            toast.error('invalid branch details')
          } else {
            toast.success('successfully added a new branch')

            // navigate to the singin page
            // navigate('/showAllBranches')
          }
        })
        .catch((error) => {
          console.log('error')
          console.log(error)
        })
    }
  }
    return (
        <div style={{ marginTop: 100 }}>
        <div style={styles.container}>
          <div className='mb-3'>
            <label>Branch Name</label>
            <input
              onChange={(event) => {
                setBranchName(event.target.value)
              }}
              className='form-control'
              type='text'
            />
          </div>
  
          <div className='mb-3'>
            <label>Location</label>
            <input
              onChange={(event) => {
                setLocation(event.target.value)
              }}
              className='form-control'
              type='text'
            />
          </div>
  
          <div className='mb-3'>
            <label>Pincode</label>
            <input
              onChange={(event) => {
                setPincode(event.target.value)
              }}
              className='form-control'
              type='tel'
            />
          </div>
  
          <div className='mb-3' style={{ marginTop: 40 }}>
            
            <button onClick={updateBranch} style={styles.addBranchButton}>
              Update Branch
            </button>
          </div>
        </div>
      </div>
    )
  }
  
  const styles = {
    container: {
      width: 400,
      height: 340,
      padding: 20,
      position: 'relative',
      top: 0,
      left: 0,
      right: 0,
      bottom: 0,
      margin: 'auto',
      borderColor: '#db0f62',
      borderRadius: 10,
      broderWidth: 1,
      borderStyle: 'solid',
      boxShadow: '1px 1px 20px 5px #C9C9C9',
    },
    addBranchButton: {
      position: 'relative',
      width: '100%',
      height: 40,
      backgroundColor: '#db0f62',
      color: 'white',
      borderRadius: 5,
      border: 'none',
      marginTop: 10,
    }
    
}

export default UpdateBranch