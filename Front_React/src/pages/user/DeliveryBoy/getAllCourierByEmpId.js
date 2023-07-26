import {useState,useEffect} from 'react'
import axios from 'axios'
import config from '../../../config'
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'

// const UpdateStatusToPickedUp=(id)=>{
//   // const navigate = useNavigate()
//   axios.put(config.serverURL + `/deliveryBoy/updateCourierStatusToPickedUp/${id}`)
//   .then((response)=>{
//       console.log(response)
//       // navigate('/getAllCourierByEmpId')
      
//   }).catch((error)=>{
//       console.log(error)
//   })
// }

const UpdateStatusToInTransit=(id)=>{
  // const navigate = useNavigate()
  axios.put(config.serverURL + `/deliveryBoy/updateCourierStatusToInTransit/${id}`)
  .then((response)=>{
      console.log(response)
      toast.success("successfully updated status to Transit")
      // navigate('/getAllCourierByEmpId')
      
  }).catch((error)=>{
      console.log(error)
  })
}

const UpdateStatusToDelivered=(id)=>{
// const navigate = useNavigate()
  axios.put(config.serverURL + `/deliveryBoy/updateCourierStatusToDelivered/${id}`)
  .then((response)=>{
      console.log(response)
      // navigate('/getAllCourierByEmpId')
      toast.success("successfully updated status to Delivered")
      // navigate('/getAllCourierByEmpId')
      
  }).catch((error)=>{
      console.log(error)
  })
}
  

const GetAllCourierByEmpId=()=> {

    const[courierList,setCourierList]=useState([])
    const id = sessionStorage.getItem('id')
    const token = sessionStorage.getItem('token')
    if (token != null)
    axios.defaults.headers.common["Authorization"] = "Bearer " + token;
    useEffect(() => {
      courier();
    }, []);
    

    const courier=()=>{
        axios.get(config.serverURL + `/deliveryBoy/getAllCouriersByEmpId/${id}`) //hardcoded value
        .then((response)=>{
            console.log(response)
            setCourierList(response.data)
            
        }).catch((error)=>{
            console.log(error)
        })
    }

    

    

    return (
        <div>
            <br/>
            <div>LIST OF COURIERS TO BE PICKED UP/DELIVERED</div>
            <div class="panel">
            <div class="table-bordered table-responsive text-center">
            <table className="table table-bordered">
                        <tr>
                            {/* <th>Branch Id</th> */}
                            <th>Sender Address</th>
                            <th>Sender Pincode</th>
                            <th>Receiver Name</th>
                            <th>Receiver Address</th>
                            <th>Receiver Pincode</th>
                            <th>Category</th>
                            <th>Weight</th>
                            <th>Amount</th>
                            <th>Status</th>
                            <th>Update Status</th>
                        </tr>
        {courierList.map((list)=>{
                
                return <tr>
                            {/* <td>{list.id}</td> */}
                            <td>{list.sendersAddress}</td>
                            <td>{list.sendersPincode}</td>
                            <td>{list.receiversName}</td>
                            <td>{list.receiversAddress}</td>
                            <td>{list.receiversPincode}</td>
                            <td>{list.category}</td>
                            <td>{list.weight}</td>
                            <td>{list.amount}</td>
                            <td>{list.status}</td>
                            
          {/* <button type="button" style={styles.button} onClick={()=>UpdateStatusToPickedUp(list.id)}>Picked Up</button> */}
          <button type="button" style={styles.button} onClick={()=>UpdateStatusToInTransit(list.id)}>In Transit</button>
          <button type="button" style={styles.button} onClick={()=>UpdateStatusToDelivered(list.id)}>Delivered</button>

               </tr>
            })}
            </table>
            </div>
      </div>
      </div>
    );
  }



  const styles = {
 
    button: {
      position: 'relative',
      width: 150,
      height: 40,
      backgroundColor: '#db0f62',
      color: 'white',
      borderRadius: 5,
      border: 'none',
      marginTop: 10,
    },
  
  }
  


export default GetAllCourierByEmpId