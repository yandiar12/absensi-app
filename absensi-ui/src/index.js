// import 'bootstrap/dist/css/bootstrap.min.css';
// import 'bootstrap/dist/js/bootstrap.bundle.min';
// import '@coreui/coreui/dist/css/coreui.min.css';
// import '@popperjs/core';
// import '@coreui/coreui/dist/js/coreui.min.js';
// import '@coreui/icons';
import 'core-js';
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import store from './store';
import { Provider } from 'react-redux';

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root')
);
