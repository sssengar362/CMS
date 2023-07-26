import image from '../image/CSM.jpg'
import imageLogo from '../image/logo.png'

const Home=()=>{
    return <div class="px-4 py-5 my-5 text-center">
    <img class="d-block mx-auto mb-4" src={imageLogo} alt="" width="72" height="57" />
    <h3 class="display-6 fw-bold">COURIER MANAGEMENT SYSTEM</h3>
    <br/>
        <div>
                 <img src={image} ></img>
        </div>
        <br/>
    <div class="col-lg-6 mx-auto">
      <p class="lead mb-4">The Courier Management System has following roles: Admin, Branch Admin, Delivery Boys and Customer. The system allows Admin to create branches, employees and allot couriers. 
             The customer needs to sign in to book a Courier, which needs to be picked up and delivered at specified address. 
             The system allows allotting of Couriers to the Delivery Boys working at a specific branch by branch admin. </p>
      <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
        {/* <br/>
        <div>
                 <img src={image} ></img>
        </div> */}

      </div>
    </div>
  </div>
    // <div style={{alignItems: 'center'}}> 
    //     <br/>
    //     <h3 style={{alignContent:'center'}}> COURIER MANAGEMENT SYSTEM </h3>
    //     <br/>
    //     <div>
            
    //         <div>
    //             <img src={image} ></img>
    //         </div>
    //         <br/>
    //         <div><p1>
    //         The Courier Management System has following roles: Admin, Branch Admin, Delivery Boys and Customer. The system allows Admin to manage branches, employees and Couriers. 
    //         The customer needs to sign in to book a Courier, which needs to be picked up and delivered at specified address. 
    //         The system allows allotting of Couriers to the Delivery Boys working at a specific branch by branch admin. 
    //         Customer can able to track the status of their courier.
    //         </p1></div>
    //     </div>
    //     </div>
}

export default Home