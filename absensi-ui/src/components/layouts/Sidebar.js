// import avatar from '../../assets/images/genos-1.jpg';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faSignOutAlt, faBook, faShareSquare, faServer } from '@fortawesome/free-solid-svg-icons';

const Sidebar = ({sidebarActive}) => {

  return (
    <nav id="sidebar" className={sidebarActive ? 'active' : ''}>
      <div className="sidebar-header">
        <h3>Absensi App</h3>
        {/* <strong>AA</strong> */}
      </div>

      <ul className="list-unstyled components">
        {/* <div className="text-center"> */}
          {/* <img src={avatar} className="rounded m-2 border border-secondary" height="60" width="60" alt="profile-pict" /> */}
          <p id="user-profile"><FontAwesomeIcon className="icon mr-2" icon={faUser} />Yandi AR</p>
        {/* </div> */}
        <li  className="">
          <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle">
          <FontAwesomeIcon className="icon mr-2" icon={faShareSquare} />
            Pengajuan
          </a>
          <ul className="collapse list-unstyled" id="homeSubmenu">
            <li>
              <a href="/izin-sakit">Izin Sakit</a>
            </li>
            <li>
              <a href="/cuti">Cuti</a>
            </li>
            <li>
              <a href="/sppd">Perjalanan Dinas</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" className="dropdown-toggle">
          <FontAwesomeIcon className="icon mr-2" icon={faServer} />
            Data Pengajuan
          </a>
          <ul className="collapse list-unstyled" id="pageSubmenu">
            <li>
              <a href="/izin-sakit-data">Izin Sakit</a>
            </li>
            <li>
              <a href="/cuti-data">Cuti</a>
            </li>
            <li>
              <a href="/sppd-data">Perjalanan Dinas</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="/laporan-absensi"><FontAwesomeIcon className="icon mr-2" icon={faBook} />Laporan Absensi</a>
        </li>
        <li>
          <a href="/logout"><FontAwesomeIcon className="icon mr-2" icon={faSignOutAlt} />Sign Out</a>
        </li>
      </ul>
    </nav>
  );
}
 
export default Sidebar;