import { faBorderTopLeft } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { Fragment, useContext, useState } from "react";
import { Link, useHistory } from "react-router-dom";
import { AuthContext } from "../../store/auth-Context";

import Modal from "../Modal/Modal";

import classes from "./MainHeader.module.css";
const MainHeader = () => {
  const history = useHistory();
  const authCtx = useContext(AuthContext);
  const [isLoginClicked, setIsLoginClick] = useState(false);
  const onClickLogin = () => {
    setIsLoginClick(true);
  };
  const modalHandler = () => {
    setIsLoginClick(false);
  };
  const logoutHandler = () => {
    authCtx.logout();
    history.replace("/");
  };
  return (
    <Fragment>
      <header className={classes.header}>
        <Link to="/main" className={classes.headerLink}>
          <h1>
            <FontAwesomeIcon icon={faBorderTopLeft} /> Office Sharing Platform
          </h1>
        </Link>
        <ul>
          {!authCtx.isLoggedIn && <li onClick={onClickLogin}>SignIn</li>}
          {!authCtx.isLoggedIn && <li className={classes.line}>|</li>}
          {!authCtx.isLoggedIn && (
            <li>
              <Link to="/auth/signup" className={classes.link}>
                SignUp
              </Link>
            </li>
          )}
          {authCtx.isLoggedIn && (
            <li>
              <Link to="/user" className={classes.link}>
                MyPage
              </Link>
            </li>
          )}
          {authCtx.isLoggedIn && <li className={classes.line}>|</li>}
          {authCtx.isLoggedIn && (
            <li>
              <Link to="/main" className={classes.link} onClick={logoutHandler}>
                Logout
              </Link>
            </li>
          )}
        </ul>
      </header>
      {isLoginClicked && <Modal onConfirm={modalHandler} />}
    </Fragment>
  );
};

export default MainHeader;
