import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'

const Signout = () => {
  

    // get the navigate function reference
    const navigate = useNavigate()
    sessionStorage.removeItem('token')
    sessionStorage.removeItem('id')
    sessionStorage.removeItem('role')
    console.log(sessionStorage.id)
    toast.success("Logged out successfully")
    navigate('/home')
  
}

export default Signout
