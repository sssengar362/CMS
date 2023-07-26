import axios from 'axios'
import config from '../../../config'
import {useState,useEffect} from 'react';
// import DropdownButton from 'react-bootstrap/DropdownButton';
// import Dropdown from 'react-bootstrap/Dropdown'
import { Button } from 'react-bootstrap';
import { Navigate, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

const GetAllOrdersToBeDelivered=()=> {

    const[ordersToBeDeliveredList,setOrdersToBeDeliveredList]=useState([])
    const[deliveryBoyList,setDeliveryBoyList]=useState([])
    const [deliveryBoyId, setDeliveryBoyId] = useState('')

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
       
        axios.get(config.serverURL + `/branchAdmin/getAllOrdersToBeDelivered/${id}`) //hardcoded value
        .then((response)=>{
            console.log(response)
            setOrdersToBeDeliveredList(response.data)
            
        }).catch((error)=>{
            console.log(error)
        })
    }

        const getListOfDeliveryBoy=()=>{
            console.log(branchId)
            axios.get(config.serverURL + `/branchAdmin/getAllDeliveryBoysOfABranch/${branchId}`) //hardcoded value
            .then((response)=>{
                console.log(response)
                setDeliveryBoyList(response.data)
                    
            }).catch((error)=>{
                    console.log(error)
            })
        }
                


    const deliveryBoy=(dbid, courierId)=>{
        // axios.put(config.serverURL +`/branchAdmin/alotCourierToOneOfDeliveryBoyToPickUp/${dbid}`)
        axios.put(config.serverURL +`/branchAdmin/alotCourierToOneOfDeliveryBoyToDeliver?dbid=${dbid}&courierId=${courierId}`)
        
        .then((response)=>{
            console.log(response)
            setDeliveryBoyId(response.data)
            console.log('success')
            toast.success("successfully alloted to emp Id " +dbid)
            navigate('/branchAdminHome')
           
            
        }).catch((error)=>{
            console.log(error)
        })


        

    }
    
   




    return (
        <div>
            <br/>
            <div>
                <div>SHOW COURIERS TO BE DELIVERED</div>
            </div>
            <br/>
            
            <div class="panel">
            <div class="table-bordered table-responsive text-center">
            <table className="table table-bordered">
                        <tr>
                            <th>Branch Id</th>
                            <th>Sender Address</th>
                            <th>Sender Pincode</th>
                            <th>Receiver Name</th>
                            <th>Receiver Address</th>
                            <th>Receiver Pincode</th>
                            <th>Category</th>
                            <th>Weight</th>
                            <th>Amount</th>
                            <th>Status</th>
                            <th>Alot To Delivery Boy</th>
                        </tr>
        {ordersToBeDeliveredList.map((list)=>{
                
                return <tr>
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
                            <td>
                                <Button type="button" style={styles.button}
                                 onClick={getListOfDeliveryBoy}>Delivery Boy </Button>
                                     {deliveryBoyList.map((list1)=>{
                                         return <tr>
                                             <td>{list1.firstName}</td>
                                             <td>
                                             <button type="button" onClick={()=>deliveryBoy(list1.id, list.id)}>Alot</button>
                                             </td>
                                             </tr>})}
                                             </td>
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
      width: 200,
      height: 40,
      backgroundColor: '#db0f62',
      color: 'white',
      borderRadius: 5,
      border: 'none',
      marginTop: 10,
    },
  
  }
  


export default GetAllOrdersToBeDelivered