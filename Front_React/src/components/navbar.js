import { Link } from 'react-router-dom'

const Navbar = () => {
  return (
    <nav
      style={{ backgroundColor: '#db0f62' }}
      className='navbar navbar-expand-lg navbar-dark'>
      <div className='container-fluid'>
        <Link className='navbar-brand' to='/'>
          Courier Services
        </Link>

        <button
          className='navbar-toggler'
          type='button'
          data-bs-toggle='collapse'
          data-bs-target='#navbarSupportedContent'
          aria-controls='navbarSupportedContent'
          aria-expanded='false'
          aria-label='Toggle navigation'>
          <span className='navbar-toggler-icon'></span>
        </button>

        <div className='collapse navbar-collapse'>
          <ul className='navbar-nav me-auto mb-2 mb-lg-0'>
            <li className='nav-item'>
              <Link className='nav-link active' aria-current='page' to='/'>
                Home
              </Link>
            </li>
            <li className='nav-item'>
              <Link className='nav-link active' aria-current='page' to='/aboutUs'>
                About Us
              </Link>
            </li>
      
            <li className='nav-item'>
              <Link
                className='nav-link active'
                aria-current='page'
                to='/signin'>
                Signin
              </Link>
            </li>

        

            {/* <li className='nav-item'>
              <Link className='nav-link active' aria-current='page' to='/addBranch'>
              Add Branch
              </Link>
            </li> */}
              {/* <li className='nav-item'>
              <Link className='nav-link active' aria-current='page' to='/branchAdminHome'>
              BranchAdminHome
              </Link>
            </li>

            <li className='nav-item'>
              <Link className='nav-link active' aria-current='page' to='/showAllBranches'>
              Get All Branches
              </Link>
            </li> */}

            {/* <li className='nav-item'>
              <Link className='nav-link active' aria-current='page' to='/addCourier'>
                Send Courier
              </Link>
            </li> */}

            <li className='nav-item'>
              <Link className='nav-link active' aria-current='page' to='/trackCourier'>
                Track Courier
              </Link>
            </li>


            <li className='nav-item'>
              <Link className='nav-link active' aria-current='page' to='/signout'>
                Sign Out
              </Link>
            </li>

            
          </ul>
        </div>
      </div>
    </nav>
  )
}

export default Navbar



