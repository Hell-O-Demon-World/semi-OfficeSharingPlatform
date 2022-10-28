import { faBorderTopLeft } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { Fragment, useState } from "react";
import { Link } from "react-router-dom";
import Modal from "../Modal/Modal";

import classes from "./MainHeader.module.css";
const MainHeader = () => {
  const [isLoginClicked, setIsLoginClick] = useState(false);
  const onClickLogin = () => {
    setIsLoginClick(true);
  };
  const modalHandler = () => {
    setIsLoginClick(false);
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
          <li onClick={onClickLogin}>SignIn</li>
          <li className={classes.line}>|</li>
          <li>
            <Link to="/auth/signup" className={classes.link}>
              SignUp
            </Link>
          </li>
          <li className={classes.line}>|</li>
          <li>
            <Link to="/user" className={classes.link}>
              MyPage
            </Link>
          </li>
        </ul>
      </header>
      {isLoginClicked && <Modal onConfirm={modalHandler} />}
    </Fragment>
  );
};

export default MainHeader;
