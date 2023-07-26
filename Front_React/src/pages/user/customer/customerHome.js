import { Link } from "react-router-dom"
import imageLogo from '../../../image/logo.png'
const CustomerHome=()=>{

  return <div>
  <div class="px-4 py-5 my-5 text-center">
<img class="d-block mx-auto mb-4" src={imageLogo} alt="" width="72" height="57" />
<h3 class="display-6 fw-bold">Welcome Customer</h3>
<div class="col-lg-6 mx-auto">
  <p class="lead mb-4">Welcome to Courier Management System. We hope you make the best use our services!!</p>
  <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
  <Link to="/customerFeedback">
  <button type="button" class="btn btn-outline-secondary btn-lg px-4">Add Feedback</button>
   </Link>
   <Link to="/getAllCourier">
   <button type="button" class="btn btn-outline-secondary btn-lg px-4">Get My Couriers</button>
   </Link>
   <Link to="/addCourier">
   <button type="button" class="btn btn-outline-secondary btn-lg px-4">Book a Courier</button>
   </Link>
  </div>
</div>
</div>
</div>
   
    // return <div>Customer Home page
    //    <div>
    //    <Link to="/customerFeedback">
    //    <button type="button" style={styles.button}> ADD FEEDBACK</button>
    //    </Link>
    //    <br/>
    //    <Link to="/getAllCourier">
    //    <button type="button" style={styles.button}>MY COURIERS</button>
    //    </Link>
    //    <br/>
    //    <Link to="/addCourier">
    //    <button type="button" style={styles.button}>ADD COURIER</button>
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

export default CustomerHome