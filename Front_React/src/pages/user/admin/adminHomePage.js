import { Link } from "react-router-dom"
import imageLogo from '../../../image/logo.png'
const AdminHomePage=()=>{

  return <div>
      <div class="px-4 py-5 my-5 text-center">
    <img class="d-block mx-auto mb-4" src={imageLogo} alt="" width="72" height="57" />
    <h3 class="display-6 fw-bold">Welcome Admin</h3>
    <div class="col-lg-6 mx-auto">
      <p class="lead mb-4">Welcome to Courier Management System Project. We hope you make the best use our services!!</p>
      <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">

      <Link to="/addEmployee">
      <button type="button" class="btn btn-outline-secondary btn-lg px-4">Add Employee</button>
       </Link>
       <Link to="/addBranch">
       <button type="button" class="btn btn-outline-secondary btn-lg px-4">Add Branch</button>
       </Link>
       <Link to="/showAllBranches">
       <button type="button" class="btn btn-outline-secondary btn-lg px-4">Get All Branches</button>
       </Link>
       <Link to="/getAllFeedbacks">
       <button type="button" class="btn btn-outline-secondary btn-lg px-4">Get All Feedbacks</button>
       </Link>
       <Link to="/getAllCouriers">
       <button type="button" class="btn btn-outline-secondary btn-lg px-4">Get All Couriers</button>
       </Link>
       <Link to="/getAllEmployees">
       <button type="button" class="btn btn-outline-secondary btn-lg px-4">Get All Employees</button>
       </Link>


      </div>
    </div>
  </div>
    </div>

    // return <div>Admin Home page
    // <div>
    //    <Link to="/addEmployee">
    //    <button type="button" style={styles.button}> ADD EMPLOYEE</button>
    //    </Link>
    //    <br/>
    //    <Link to="/addBranch">
    //    <button type="button" style={styles.button}>ADD BRANCH</button>
    //    </Link>
    //    <br/>
    //    <Link to="/showAllBranches">
    //    <button type="button" style={styles.button}>SHOW BRANCHES</button>
    //    </Link>
    //    <br/>
    //    <Link to="/getAllFeedbacks">
    //    <button type="button" style={styles.button}>GET ALL FEEDBACKS</button>
    //    </Link>
    //    <br/>
    //    <br/>
    //    <Link to="/getAllCouriers">
    //    <button type="button" style={styles.button}>GET ALL COURIERS</button>
    //    </Link>
    //    <br/>
    //    <Link to="/getAllEmployees">
    //    <button type="button" style={styles.button}>GET ALL EMPLOYEES</button>
    //    </Link>
    //    </div>
    // </div>
}

// const styles = {
 
//     button: {
//       position: 'relative',
//       width: 200,
//       height: 40,
//       backgroundColor: '#db0f62',
//       color: 'white',
//       borderRadius: 5,
//       border: 'none',
//       marginTop: 10,
//     },
//   }

export default AdminHomePage