import { useState } from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios'
import config from '../../config'
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'

const Signin = () => {
  // get user inputs
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  // get the navigate function reference
  const navigate = useNavigate()

  const signin = () => {
    // check if user has really entered any value
    if (email.length === 0) {
      toast.error('please enter email')
    } else if (password.length === 0) {
      toast.error('please enter password')
    } else {
      // make the API call to check if user exists
      axios
        .post(config.serverURL + '/auth/signin', {
          email,
          password,
        })
        .then((response) => {
          // get the data returned by server
          const result = response.data
          // const result2= response

          // check if user's authentication is successfull
          if (result['status'] === 'INVALID CREDENTIALS') {
            toast.error('invalid email or password')
          } else {

            console.log(result)
            // console.log(result.jwt)

            sessionStorage['token'] = result.jwt
            console.log(sessionStorage.token)
            // sessionStorage['role'] = result.role
            // console.log(sessionStorage.role)
            // sessionStorage['id'] = result.id
            
            console.log(result)
            toast.success('welcome to Courier Service')
            if(result.cust!=null)
            {
              sessionStorage['role'] = result.cust.role
              console.log(sessionStorage.role)
              sessionStorage['id'] = result.cust.id
              console.log(sessionStorage.id)
              navigate('/customerHome')
            }else if(result.emp!=null){
              sessionStorage['role'] = result.emp.role
              console.log(sessionStorage.role)
              sessionStorage['id'] = result.emp.id
              console.log(sessionStorage.id)
              if(result.emp.branch!=null){
                sessionStorage['branchId']=result.emp.branch.id
              }
              
              console.log(sessionStorage.branchId)
              if (sessionStorage.role==="ADMIN"){

                navigate('/adminHomePage')
              } else if(sessionStorage.role==="BRANCH_ADMIN")
              {
                navigate('/branchAdminHome')
              }else if(sessionStorage.role==="DELIVERY_BOY")
              {
                navigate('/deliveryBoyHome')
              }
            }
           
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
          <label>Email</label>
          <input
            onChange={(event) => {
              setEmail(event.target.value)
            }}
            className='form-control'
            type='email'
          />
        </div>
        <div className='mb-3'>
          <label>Password</label>
          <input
            onChange={(event) => {
              setPassword(event.target.value)
            }}
            className='form-control'
            type='password'
          />
        </div>
        <div className='mb-3' style={{ marginTop: 40 }}>
          <div>
            Dont have an account? <Link to='/customerSignup'>Signup here</Link>
          </div>
          <button onClick={signin} style={styles.signinButton}>
            Signin
          </button>
        </div>
      </div>
    </div>
  )
}

const styles = {
  container: {
    width: 400,
    height: 300,
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
  signinButton: {
    position: 'relative',
    width: '100%',
    height: 40,
    backgroundColor: '#db0f62',
    color: 'white',
    borderRadius: 5,
    border: 'none',
    marginTop: 10,
  },
}

export default Signin
