import React from 'react';

import './App.css';
import Navbar from './components/navbar'

import Signin from './pages/user/signin'
import Signout from './pages/user/signout';
//customer
import CustomerSignup from './pages/user/customer/customerSignup'
import CustomerHome from './pages/user/customer/customerHome'
import AddCourier from './pages/user/customer/addCourier'
import GetAllCourier from './pages/user/customer/getAllCourier'
import CustomerFeedback from './pages/user/customer/customerFeedback'
import TrackCourier from './pages/user/trackCourier';
//Employee

import AdminHomePage from './pages/user/admin/adminHomePage'
import AddBranch from './pages/user/admin/addBranch'
import AddEmployee from './pages/user/admin/addEmployee'
import GetAllBranches from './pages/user/admin/getAllBranches'
import BranchAdminHome from './pages/user/branchAdmin/branchAdminHome';
import GetAllOrdersToBePickedUp from './pages/user/branchAdmin/getAllOrdersToBePickedUp';
import GetAllOrdersToBeDelivered from './pages/user/branchAdmin/getAllOrdersToBeDelivered';

// Admin
import GetAllFeedbacks from './pages/user/admin/getAllFeedbacks'
import GetAllCouriers from './pages/user/admin/getAllCouriers'
import GetAllEmployees from './pages/user/admin/getAllEmployees'
import UpdateBranch from './pages/user/admin/updateBranch'

// Delivery Boy
import DeliveryBoyHome from './pages/user/DeliveryBoy/deliveryBoyHome';
import GetAllCourierByEmpId from './pages/user/DeliveryBoy/getAllCourierByEmpId'
import GetAllOrdersToBePickedUpDB from './pages/user/DeliveryBoy/getAllOrdersToBePickedUpDB';
import GetAllOrdersToBeDeliveredDB from './pages/user/DeliveryBoy/getAllOrdersToBeDeliveredDB';

import Home from './pages/home'

import { ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
import 'react-bootstrap'


import { BrowserRouter, Routes, Route } from 'react-router-dom'
import FooterComponent from './components/footerComponent'
import AboutUs from './pages/aboutUs';



function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <div className='container'>
    
      <Routes>
        {/* ADMIN */}
        <Route path='/getAllFeedbacks' element={<GetAllFeedbacks />} />
        <Route path='/getAllCouriers' element={<GetAllCouriers />} />
        <Route path='/getAllEmployees' element={<GetAllEmployees />} />
        <Route path='/addBranch' element={<AddBranch />} />
        <Route path='/showAllBranches' element={<GetAllBranches />} />
        <Route path='/updateBranch' element={<UpdateBranch />} />


        <Route path='/signin' element={<Signin />} />
        <Route path='/signOut' element={<Signout />} />
        <Route path='/addEmployee' element={<AddEmployee />} />
        <Route path='/customerSignup' element={<CustomerSignup />} />
        <Route path='/customerFeedback' element={<CustomerFeedback />} />


        

        <Route path='/' element={<Home />} />
        <Route path='/aboutUs' element={<AboutUs />} />
        <Route path='/getAllCourier' element={<GetAllCourier/>} />
        <Route path='/addCourier' element={<AddCourier />} />
        
        <Route path='/customerHome' element={<CustomerHome/>} />
        <Route path='/adminHomePage' element={<AdminHomePage/>} />
        <Route path='/branchAdminHome' element={<BranchAdminHome/>} />
        <Route path='/getAllOrdersToBePickedUp' element={<GetAllOrdersToBePickedUp/>} />
        <Route path='/getAllOrdersToBeDelivered' element={<GetAllOrdersToBeDelivered/>} />
        <Route path='/trackCourier' element={<TrackCourier/>} />
       


        {/* Delivery Boy */}
        <Route path='/deliveryBoyHome' element={<DeliveryBoyHome/>} />
        <Route path='/getAllCourierByEmpId' element={<GetAllCourierByEmpId/>} />
        <Route path='/getAllOrdersToBePickedUpDB' element={<GetAllOrdersToBePickedUpDB/>} />
        <Route path='/getAllOrdersToBeDeliveredDB' element={<GetAllOrdersToBeDeliveredDB/>} />


      </Routes>
      </div>
      <FooterComponent/>

      {/* this container is used to show toast messages */}
      <ToastContainer position='top-center' autoClose={3000} />
    </BrowserRouter>
    
   
  )
}

export default App;
