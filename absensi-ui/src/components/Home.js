import React, { useState } from 'react';
import Navbar from './layouts/Navbar';
import Sidebar from './layouts/Sidebar';

const Home = () => {
  const [isActive, setIsActive] = useState(false);

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