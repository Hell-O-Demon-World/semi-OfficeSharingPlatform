import React from "react";
import classes from "./TimeButton.module.css";
const TimeButton = (props) => {
  return (
    <div className={props.classActive + `${classes.box}`}>
      <p>{props.time + ":00"}</p>
    </div>
  );
};

export default TimeButton;
