import {useState,useEffect} from 'react'
import axios from 'axios'
import config from '../../../config'
import { useNavigate,Link } from 'react-router-dom'


const GetAllBranches=()=> {
  const token = sessionStorage.getItem('token')
  if (token != null)
  axios.defaults.headers.common["Authorization"] = "Bearer " + token;

    const[branchList,setBranchList]=useState([])
    const navigate = useNavigate()
    useEffect(() => {
      getAllBranches();
    }, []);
    const getAllBranches=()=>{

        axios.get(config.serverURL + '/admin/getAllBranches')
        .then((response)=>{
            console.log(response)
            setBranchList(response.data)
            
        }).catch((error)=>{
            console.log(error)
        })

    }

    // const updateBranch = (id) => {
    //   const token = sessionStorage.getItem('token')
    //   if (token != null)
    //   axios.defaults.headers.common["Authorization"] = "Bearer " + token;
    //   // pass the home id which you want to edit
    //   navigate('/updateBranch', { state: { branchId: id } })
    // }

    

    return (
      <div>
          <br/>
          <div>BRANCHES</div>
          <br/>
          <br/>
            <div class="panel">
            <div class="table-bordered table-responsive">
            <table className="table table-bordered">
        <tr>
                            <th>Branch Id</th>
                            <th>Branch Name</th>
                            <th>Location</th>
                            <th>Pincode</th>
                            
                        </tr>
        {branchList.map((list)=>{
                
                return <tr>
                            <td>{list.id}</td>
                            <td>{list.branchName}</td>
                            <td>{list.location}</td>
                            <td>{list.pincode}</td>
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
 
    button1: {
      position: 'relative',
      width: 200,
      height: 40,
      backgroundColor: '#db0f62',
      color: 'white',
      borderRadius: 5,
      border: 'none',
      marginTop: 10,
    },
    button2: {
        position: 'relative',
        width: 80,
        height: 40,
        backgroundColor: '#db0f62',
        color: 'white',
        borderRadius: 5,
        border: 'none',
        marginTop: 0,
      },
  
  }
  
  
  export default GetAllBranches;