import {useState,useEffect} from 'react'
import axios from 'axios'
import config from '../../../config'
import { Link } from 'react-router-dom'

const GetAllFeedbacks=()=> {

    const[feedbackList,setFeedbackList]=useState([])
    useEffect(() => {
      getAllFeedbacks();
    }, []);

    const getAllFeedbacks=()=>{
      const id = sessionStorage.getItem('id')
const token = sessionStorage.getItem('token')
if (token != null)
axios.defaults.headers.common["Authorization"] = "Bearer " + token;
     
        axios.get(config.serverURL + '/admin/getAllFeedbacks')
        .then((response)=>{
            console.log(response)
            setFeedbackList(response.data)
            
        }).catch((error)=>{
            console.log(error)
        })

    }

    

    return (
      <div>
          <br/>
          <div><h2>FEEDBACKS</h2></div>
          <br/>
          <br/>
            <div class="panel">
            <div class="table-bordered table-responsive">
            <table className="table table-bordered">
        <tr>
                            
                            <th>Feedback Id</th>
                            <th>Message</th>
                            <th>Customer Id</th>
                            
                        </tr>
        {feedbackList.map((list)=>{
                
                return <tr> 
                            <td>{list.id}</td>
                            <td>{list.message}</td>
                            <td>{list.customerFeedback.id}</td>
                           
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
  
  
  export default GetAllFeedbacks;
