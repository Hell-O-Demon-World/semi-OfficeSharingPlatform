import React, { useContext } from "react";
import { Redirect, Route, Switch } from "react-router-dom";

import Main from "./pages/Main";
import MyPage from "./pages/MyPage";
import NotFound from "./pages/NotFound";
import OfficeMain from "./pages/OfficeMain";
import SignIn from "./pages/SignIn";
import SignUp from "./pages/SignUp";
import { AuthContext } from "./store/auth-Context";
const App = () => {
  const authCtx = useContext(AuthContext);
  return (
    <Switch>
      <Route path="/" exact>
        <Redirect to="/main" />
      </Route>
      <Route path="/main">
        <Main />
      </Route>
      {authCtx.isLoggedIn && (
        <Route path="/user">
          <MyPage />
        </Route>
      )}
      {!authCtx.isLoggedIn && (
        <Route path="/auth/signin">
          <SignIn />
        </Route>
      )}
      {!authCtx.isLoggedIn && (
        <Route path="/auth/signup">
          <SignUp />
        </Route>
      )}
      <Route path="/officeDetail/:officeId">
        <OfficeMain />
      </Route>
      <Route path="/officeDetail/:officeId/:productType">
        <OfficeMain />
      </Route>
      <Route path="*" exact>
        <NotFound />
      </Route>
    </Switch>
  );
};

export default App;
