import React, { Fragment } from "react";

import classes from "./ProductHeader.module.css";

const ProductHeader = (props) => {
  const clickItem = (e) => {
    props.clickItem(e.target.id);
  };
  return (
    <Fragment>
      <header className={classes.header}>
        <div id="desk" onClick={clickItem}>
          Desk
        </div>
        <div id="office" onClick={clickItem}>
          Office
        </div>
        <div id="meetingRoom" onClick={clickItem}>
          MeetingRoom
        </div>
      </header>
      <main>{props.children}</main>
    </Fragment>
  );
};

export default ProductHeader;
