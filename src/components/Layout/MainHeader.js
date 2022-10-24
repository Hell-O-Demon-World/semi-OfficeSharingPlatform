import React, { Fragment, useState } from "react";
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
        <h1>Office Sharing Platform</h1>
        <ul>
          <li onClick={onClickLogin}>Login</li>
          <li>|</li>
          <li>logout</li>
        </ul>
      </header>
      {isLoginClicked && <Modal onConfirm={modalHandler} />}
    </Fragment>
  );
};

export default MainHeader;
