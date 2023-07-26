import {useState,useEffect} from 'react'
import axios from 'axios'
import config from '../../../config'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'





const AllotCourierToRespBranchAdmin=(cId)=>{
const id = sessionStorage.getItem('id')
// const navigate = useNavigate()
const token = sessionStorage.getItem('token')
if (token != null)
axios.defaults.headers.common["Authorization"] = "Bearer " + token;

  // const navigate = useNavigate()
  axios.put(config.serverURL + `/admin/alotCourierToRecieverPincodeBranchAdminAndChangeStatus/${cId}`)
  .then((response)=>{
      console.log(response)
      toast.success("Successfully dispatched to Receiver Branch") 
      // navigate('/adminHomePage')
      
  }).catch((error)=>{
      console.log(error)
  })
}


  

const GetAllCouriers=()=> {

    const[courierList,setCourierList]=useState([])
    const id = sessionStorage.getItem('id')
const token = sessionStorage.getItem('token')
if (token != null)
axios.defaults.headers.common["Authorization"] = "Bearer " + token;
useEffect(() => {
  courier();
}, []);
    

    const courier=()=>{
      axios.get(config.serverURL + `/admin/getAllCouriers`) //hardcoded value
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
            <div>ALL COURIERS</div>
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
                            
          <button type="button" style={styles.button} onClick={()=>AllotCourierToRespBranchAdmin(list.id)}>Dispatch to Receiver Branch</button>
          

               </tr>
            })}
            </table>
            
            </div>
            
      </div>
      <div>
        <Link to='/adminHomePage'>BACK</Link>
      </div>
      </div>
      
    );
  }



  const styles = {
 
    button: {
      position: 'relative',
      width: 230,
      height: 40,
      backgroundColor: '#db0f62',
      color: 'white',
      borderRadius: 5,
      border: 'none',
      marginTop: 10,
    },
  
  }
  


export default GetAllCouriers