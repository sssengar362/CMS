import { useState } from 'react'
// import { Link } from 'react-router-dom'
import { toast } from 'react-toastify'
import config from '../../../config'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

const AddCourier = () => {
  // get user inputs
  //const [customerCouriers, setCustomerCouriers] = useState('')
  const [sendersAddress, setSendersAddress] = useState('')
  const [sendersPincode, setSendersPincode] = useState('')
  const [receiversName, setReceiversName] = useState('')
  // const [phone, setPhone] = useState('')
  const [receiversAddress, setReceiversAddress] = useState('')
  const [receiversPincode, setReceiversPincode] = useState('')
  const [bookedDate, setBookedDate] = useState('')
  const [category, setCategory] = useState('')
  const [weight, setWeight] = useState('')

  // this function is used to navigate from one component to another programmatically
  // userNavigate() returns a function reference
  const navigate = useNavigate()

  const sendCourier = () => {

    const id = sessionStorage.getItem('id')
const token = sessionStorage.getItem('token')
if (token != null)
axios.defaults.headers.common["Authorization"] = "Bearer " + token;
    // check if user has really entered any value
    if (sendersAddress.length === 0) {
      toast.error('please enter senders address')
    } else if (sendersPincode.length === 0) {
      toast.error('please enter senders Pincode')
    } else if (receiversName.length === 0) {
      toast.error('please enter receivers Name')
    } else if (receiversAddress.length === 0) {
      toast.error('please enter receivers Address')
    } else if (receiversPincode.length === 0) {
      toast.error('please enter receivers pincode')
    } else if (bookedDate.length === 0) {
      toast.error('please enter booked date')
    } else if (category.length === 0) {
      toast.error('please enter category')
    } else if (weight.length === 0) {
      toast.error('please enter weight')
    } else {
      // make the API call to check if user exists
      axios
        .post(config.serverURL + `/customer/addCourier/${id}`, {
          
          sendersAddress,
          sendersPincode,
          receiversName,
          receiversAddress,
          receiversPincode,
          bookedDate,
          category,
          weight
          
        })
        .then((response) => {
          // get the data returned by server
          const result = response.data

          // check if user's authentication is successfull
          if (result['status'] === 'error') {
            toast.error('invalid details')
          } else {
            toast.success('successfully added courier details')

            // navigate to the singin page
            navigate('/customerHome')
          }
        })
        .catch((error) => {
          console.log('error')
          console.log(error)
        })
    }
  }

  return (
    <div style={{ marginTop: 30 }}>
      <div style={styles.container}>

        <div className='mb-3'>
          <label>Senders Address</label>
          <input
            onChange={(event) => {
              setSendersAddress(event.target.value)
            }}
            className='form-control'
            type='text'
          />
        </div>

        <div className='mb-3'>
          <label>Senders Pincode</label>
          <input
            onChange={(event) => {
              setSendersPincode(event.target.value)
            }}
            className='form-control'
            type='text'
          />
        </div>

        

        <div className='mb-3'>
          <label>Receivers Name</label>
          <input
            onChange={(event) => {
              setReceiversName(event.target.value)
            }}
            className='form-control'
            type='email'
          />
        </div>

        <div className='mb-3'>
          <label>Receivers Address</label>
          <input
            onChange={(event) => {
              setReceiversAddress(event.target.value)
            }}
            className='form-control'
            type='text'
          />
        </div>

        <div className='mb-3'>
          <label>Receivers Pincode</label>
          <input
            onChange={(event) => {
              setReceiversPincode(event.target.value)
            }}
            className='form-control'
            type='text'
          />
        </div>

        <div className='mb-3'>
          <label>Booking Date</label>
          <input
            onChange={(event) => {
              setBookedDate(event.target.value)
            }}
            className='form-control'
            type='date'
          />
        </div>

        <div className='mb-3'>
          
          <label >Category</label>
          
          <select 
            onChange={(event) => {
              setCategory(event.target.value)
            }}
            className='form-control'
            type='text'
           
          >
            <option value="DOCUMENTS">SELECT</option>
            <option value="DOCUMENTS">DOCUMENTS</option>
            <option value="ELECTRONICS">ELECTRONICS</option>
            <option value="FURNITURE">FURNITURE</option>
            <option value="EATABLES">EATABLES</option>
            <option value="JEWELLERY">JEWELLERY</option>
            <option value="LUGGAGES ">LUGGAGES </option>

</select>
 
        </div>

        <div className='mb-3'>
          <label>Weight</label>
          
          <input
            onChange={(event) => {
              setWeight(event.target.value)
            }}
            className='form-control'
            type='text'
          />
        </div>





        <div className='mb-3' style={{ marginTop: 40 }}>
          
          <button onClick={sendCourier} style={styles.signinButton}>
            ADD COURIER
          </button>
        </div>
      </div>
    </div>
  )
}

const styles = {
  container: {
    width: 400,
    height: 730,
    padding: 10,
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
    marginTop: 5,
  },
}

export default AddCourier