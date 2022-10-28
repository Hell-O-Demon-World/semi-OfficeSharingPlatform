import React from "react";
import { Redirect, Route, Switch } from "react-router-dom";

import Main from "./pages/Main";
import MyPage from "./pages/MyPage";
import NotFound from "./pages/NotFound";
import SignIn from "./pages/SignIn";
import SignUp from "./pages/SignUp";
const App = () => {
  return (
    <Switch>
      <Route path="/" exact>
        <Redirect to="/main" />
      </Route>
      <Route path="/main">
        <Main />
      </Route>
      <Route path="/user">
        <MyPage />
      </Route>
      <Route path="/auth/signin">
        <SignIn />
      </Route>
      <Route path="/auth/signup">
        <SignUp />
      </Route>
      <Route path="*" exact>
        <NotFound />
      </Route>
    </Switch>
  );
};

export default App;
