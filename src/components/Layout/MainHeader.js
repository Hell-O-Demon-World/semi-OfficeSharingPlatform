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
        <h1>
          <FontAwesomeIcon icon={faBorderTopLeft} /> Office Sharing Platform
        </h1>
        <ul>
          <li onClick={onClickLogin}>SignIn</li>
          <li>|</li>
          <li>
            <Link to="/members/signup" className={classes.link}>
              SignUp
            </Link>
          </li>
        </ul>
      </header>
      {isLoginClicked && <Modal onConfirm={modalHandler} />}
    </Fragment>
  );
};

export default MainHeader;
