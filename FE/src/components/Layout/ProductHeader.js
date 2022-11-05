import React, { Fragment } from "react";
import { NavLink, useRouteMatch } from "react-router-dom";

import classes from "./ProductHeader.module.css";

const ProductHeader = (props) => {
  const { url } = useRouteMatch();
  const clickItem = (e) => {
    props.clickItem(e.target.id);
  };
  return (
    <Fragment>
      <header className={classes.header}>
        <NavLink
          className={classes.link}
          id="desk"
          onClick={clickItem}
          activeClassName={classes.active}
          to={url + "/desk"}
        >
          Desk
        </NavLink>
        <NavLink
          className={classes.link}
          id="office"
          onClick={clickItem}
          activeClassName={classes.active}
          to={url + "/office"}
        >
          Office
        </NavLink>
        <NavLink
          className={classes.link}
          id="meetingRoom"
          onClick={clickItem}
          activeClassName={classes.active}
          to={url + "/meetingRoom"}
        >
          MeetingRoom
        </NavLink>
      </header>
      <main>{props.children}</main>
    </Fragment>
  );
};

export default ProductHeader;
