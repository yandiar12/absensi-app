import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { HashRouter, Route, Switch } from 'react-router-dom';
import './scss/style.scss';

import { clearMessage } from './actions/messageAction';

import { history } from './helpers/history';

const DefaultLayout = React.lazy(() => import('./layouts/DefaultLayout'));

const loading = (
  <div className="pt-3 text-center">
    <div className="sk-spinner sk-spinner-pulse"></div>
  </div>
)

const App = () => {
  const dispatch = useDispatch();

  useEffect(() => {
    history.listen((location) => {
      dispatch(clearMessage())
    })
  }, [dispatch])

  return (
    <HashRouter>
      <React.Suspense fallback={loading}>
        <Switch>
          <Route path="/" name="Home" render={(props) => <DefaultLayout {...props} />} />
          {/* <Route exact path="/login">
            <Login />
          </Route> */}
        </Switch>
      </React.Suspense>
    </HashRouter>
  );
}

export default App;
