import { useState } from 'react'
// import { Link } from 'react-router-dom'
import { toast } from 'react-toastify'
import config from '../../../config'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

const AddBranch=()=>{
  const [branchName, setBranchName] = useState('')
  const [location, setLocation] = useState('')
  const [pincode, setPincode] = useState('')
  
  const navigate = useNavigate()
  const addBranch = () => {
const id = sessionStorage.getItem('id')
const token = sessionStorage.getItem('token')
if (token != null)
axios.defaults.headers.common["Authorization"] = "Bearer " + token;

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
        .post(config.serverURL + '/admin/addBranch', {
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
            navigate('/addEmployee')
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
            
            <button onClick={addBranch} style={styles.addBranchButton}>
              Add Branch
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

export default AddBranch