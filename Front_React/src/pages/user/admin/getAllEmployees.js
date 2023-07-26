import {useState,useEffect} from 'react'
import axios from 'axios'
import config from '../../../config'
import { Link } from 'react-router-dom'

const GetAllEmployees=()=> {

    const[employeeList,setEmployeeList]=useState([])
    useEffect(() => {
      getAllEmployees();
    }, []);

    const getAllEmployees=()=>{
      const id = sessionStorage.getItem('id')
const token = sessionStorage.getItem('token')
if (token != null)
axios.defaults.headers.common["Authorization"] = "Bearer " + token;
        axios.get(config.serverURL + '/admin/getAllEmployees')
        .then((response)=>{
            console.log(response)
            setEmployeeList(response.data)
            
        }).catch((error)=>{
            console.log(error)
        })

    }

    // const deleteEmployee=(eId)=>{
    //     axios.delete(config.serverURL +`/admin/deleteEmpById/${eId}`)
    //     .then((response)=>{
    //         console.log('success')
           
            
    //     }).catch((error)=>{
    //         console.log(error)
    //     })

    // }

    return (
      <div>
          <br/>
          <div>EMPLOYEES</div>
          <br/>
          <br/>
            <div class="panel">
            <div class="table-bordered table-responsive">
            <table className="table table-bordered">
        <tr>
                            <th>Employee Id</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Mobile No</th>
                            <th>Role</th>
                            <th>Branch Name</th>
                            <th>Branch Id</th>
                        </tr>
        {employeeList.map((list)=>{
                
                return <tr>
                            <td>{list.id}</td>
                            <td>{list.firstName}</td>
                            <td>{list.lastName}</td>
                            <td>{list.email}</td>
                            <td>{list.mobileNo}</td>
                            <td>{list.role}</td>
                            <td>{list.branch.branchName}</td>
                            <td>{list.branch.id}</td>

                        
                    
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
  
  
  export default GetAllEmployees;