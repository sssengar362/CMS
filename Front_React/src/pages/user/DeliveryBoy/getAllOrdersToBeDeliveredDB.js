import axios from 'axios'
import config from '../../../config'
import {useState,useEffect} from 'react';
// import DropdownButton from 'react-bootstrap/DropdownButton';
// import Dropdown from 'react-bootstrap/Dropdown'
import { Button } from 'react-bootstrap';
import { Navigate, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

const UpdateStatusToDeliver=(cId)=>{
    const id = sessionStorage.getItem('id')
    const branchId = sessionStorage.getItem('branchId')
    const token = sessionStorage.getItem('token')
    // const navigate = useNavigate()
    if (token != null)
    axios.defaults.headers.common["Authorization"] = "Bearer " + token;

    // const navigate = useNavigate()
    axios.put(config.serverURL + `/deliveryBoy/updateCourierStatusToDelivered/${cId}`)
    .then((response)=>{
        console.log(response)
        toast.success("successfully updated status to Delivered")
        // navigate('/getAllCourierByEmpId')
        
    }).catch((error)=>{
        console.log(error)
    })
  }
const GetAllOrdersToBeDeliveredDB=()=> {

    const[ordersToBeDeliveredList,setOrdersToBeDeliveredList]=useState([])

    const id = sessionStorage.getItem('id')
    const branchId = sessionStorage.getItem('branchId')
    const token = sessionStorage.getItem('token')
    const navigate = useNavigate()
    if (token != null)
    axios.defaults.headers.common["Authorization"] = "Bearer " + token;

    useEffect(() => {
        couriers();
      }, []);

    const couriers=()=>{
       
        axios.get(config.serverURL + `/deliveryBoy/getAllOrdersToBeDeliver/${id}`) //hardcoded value
        .then((response)=>{
            console.log(response)
            setOrdersToBeDeliveredList(response.data)
            
        }).catch((error)=>{
            console.log(error)
        })
    }

     return (
        <div>
        <br/>
        <div>LIST OF COURIERS TO BE DELIVERED</div>
        <div className="panel">
        <div className="table-bordered table-responsive text-center">
        <table className="table table-bordered">
          <thead>
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
                        <th>Update Status</th>
                    </tr>
          </thead>
    {ordersToBeDeliveredList.map((list)=>{
            
            return <tbody>
            <tr>
                        <td>{list.id}</td>
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
      <button type="button" style={styles.button} onClick={()=>UpdateStatusToDeliver(list.id)}>Delivered</button>

           </tr>
           </tbody>
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
      width: 200,
      height: 40,
      backgroundColor: '#db0f62',
      color: 'white',
      borderRadius: 5,
      border: 'none',
      marginTop: 10,
    },
  
  }
  


export default GetAllOrdersToBeDeliveredDB