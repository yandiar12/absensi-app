import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import { Redirect } from 'react-router-dom';
import Navbar from './layouts/Navbar';
import Sidebar from './layouts/Sidebar';

const Home = () => {
  const [isActive, setIsActive] = useState(false);

  const { isLoggedIn } = useSelector((state) => state.auth)

  if (!isLoggedIn) {
    return <Redirect to='/login' />
  }

  const handleToggleSidebar = () => {
    
    if (isActive) {
      setIsActive(false);
    } else {
      setIsActive(true);
    }
  }
  return (
    <div className="wrapper">
      <Sidebar sidebarActive={isActive} />
      <Navbar handleToggleSidebar={handleToggleSidebar} />
    </div>
  );
}
 
export default Home;