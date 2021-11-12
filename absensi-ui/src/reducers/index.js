import { combineReducers } from 'redux';
import authReducer from './authReducer';
import messageReducer from './messageReducer';
import sidebarReducer from './sidebarReducer';


export default combineReducers({
  sidebar: sidebarReducer,
  auth: authReducer,
  message: messageReducer
})