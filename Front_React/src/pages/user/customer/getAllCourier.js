import {useEffect, useState} from 'react'
import axios from 'axios'
import config from '../../../config'


const GetAllCourier=()=> {

    const[courierList,setCourierList]=useState([])
    const id = sessionStorage.getItem('id')
      const token = sessionStorage.getItem('token')
      if (token != null)
axios.defaults.headers.common["Authorization"] = "Bearer " + token;
useEffect(() => {
  courier();
}, []);

    const courier=()=>{
      
        axios.get(config.serverURL + `/customer/getAllCouriersById/${id}`) //hardcoded value
        .then((response)=>{
            console.log(response)
            setCourierList(response.data)
            
        }).catch((error)=>{
            console.log(error)
        })

    //getACourierDetailsByCourierId/{cId}

    //    const trackCourier=()=>{
    //     axios.get(config.serverURL +`getACourierDetailsByCourierId/1`)
    //     .then((response)=>{
    //         console.log('success')
           
            
    //     }).catch((error)=>{
    //         console.log(error)
    //     })

    //}

    // const deleteBranch=(bId)=>{
    //     axios.delete(config.serverURL +`/admin/deleteByBranchId/${bId}`)
    //     .then((response)=>{
    //         console.log('success')
           
            
    //     }).catch((error)=>{
    //         console.log(error)
    //     })

    }

    return (
        <div>
            <br/>
            <div>MY COURIERS</div>
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
  


export default GetAllCourier