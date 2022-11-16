import React, { Fragment } from "react";
import classes from "./NotFound.module.css";
const NotFound = () => {
  return (
    <Fragment>
      <div className={classes.notFound}>
        <h1>404</h1>
        <h1>Not Found Page</h1>
      </div>
    </Fragment>
  );
};

export default NotFound;
