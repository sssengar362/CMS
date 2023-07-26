import { useState } from 'react'
import config from '../../config'
import axios from 'axios'
import { toast } from 'react-toastify'


  const TrackCourier=()=> {

    const[courierList,setCourierList]=useState([])
       const[courierId,setCourierId]=useState([])
  
    

    const track=(courierId)=>{
      axios.get(config.serverURL + `/track/getACourierDetailsByCourierId/${courierId}`) //hardcoded value
        .then((response)=>{
            console.log(response)
            setCourierList(response.data)
            //toast.success(response.data.customerCouriers.status)
            //toast.success("status:"+ response.data.status)
            console.log(courierList)
        
            
        }).catch((error)=>{
            console.log(error)
            toast.error("Invalid courier Id")
        })
    }
  
    return (
    <div style={{ marginTop: 100 }}>
      <div style={styles.container}>
        <div className='mb-3'>
          <label>ENTER YOUR COURIER ID</label>
          <input
            onChange={(event) => {
              setCourierId(event.target.value)
            }}
            className='form-control'
            type='text'
          />
        </div>
        <button onClick = {()=>track(courierId)}type = "button" style={styles.feedbackButton}>
            TRACK
        </button>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
  
      </div>
      <div>
        <br/>
        <br/>
      <table className="table table-bordered">
                        <tr>
                            <th>Courier Id</th>
                            <th>Sender Address</th>
                            <th>Sender Pincode</th>
                            <th>Receiver Name</th>
                            <th>Receiver Address</th>
                            <th>Receiver Pincode</th>
                            <th>Category</th>
                            <th>Weight</th>
                            <th>Amount</th>
                            <th>Status</th>
                        </tr>
        
                
                  <tr>
                            <td>{courierList.id}</td>
                            <td>{courierList.sendersAddress}</td>
                            <td>{courierList.sendersPincode}</td>
                            <td>{courierList.receiversName}</td>
                            <td>{courierList.receiversAddress}</td>
                            <td>{courierList.receiversPincode}</td>
                            <td>{courierList.category}</td>
                            <td>{courierList.weight}</td>
                            <td>{courierList.amount}</td>
                            <td>{courierList.status}</td>
                                             </tr>
                                        
            </table>

      </div>
    </div>
  );
}


const styles = {
  container: {
    width: 400,
    height: 200,
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
  feedbackButton: {
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

export default TrackCourier



