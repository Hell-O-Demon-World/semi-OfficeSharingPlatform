import React, { Fragment } from "react";
import classes from "./TimeButton.module.css";
const TimeButton = (props) => {
  const click = (e) => {
    e.stopPropagation();
  };
  return (
    <Fragment>
      <div id={props.time} className={classes.box}>
        <span className={classes.text} onClick={click}>
          {props.time + ":00"}
        </span>
      </div>
    </Fragment>
  );
};

export default TimeButton;
