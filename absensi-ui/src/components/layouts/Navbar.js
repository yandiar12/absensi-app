import { useDispatch } from 'react-redux';
import { Redirect } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faBars, faUser, faSignOutAlt } from '@fortawesome/free-solid-svg-icons'
import { logout } from '../../actions/authAction';

const Navbar = ({handleToggleSidebar}) => {

  const dispatch = useDispatch();

  const handleLogout = () => {
    dispatch(logout());
    return <Redirect to='/login' />
  }

  return (
    <div className="container-fluid content">
      <nav className="navbar navbar-expand-lg navbar-light bg-light shadow-sm p-3 mb-5 bg-white rounded">
        <button type="button" className="btn btn-outline-dark" id="sidebarCollapse" onClick={handleToggleSidebar}>
          <span>
            <FontAwesomeIcon icon={faBars} />
          </span>
        </button>
        
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span>
            <FontAwesomeIcon icon={faBars} />
          </span>
        </button>

        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav ml-auto">
            <li className="nav-item">
              <a className="nav-link" href="/profile">
              <FontAwesomeIcon icon={faUser} /> User Profile
              </a>
            </li>
            <li className="nav-item">
              <a type="button" className="nav-link" href="#" onClick={handleLogout}>
                <FontAwesomeIcon icon={faSignOutAlt} /> Sign Out
              </a>
            </li>
          </ul>
        </div>
        
      </nav>
    </div>
  );
}
 
export default Navbar;