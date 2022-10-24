import React from "react";
import classes from "./Office.module.css";
const Office = (props) => {
  return (
    <div className={classes.wrapper}>
      <div>
        <div className={classes.name}>{props.item.name}</div>
        <div className={classes.address}>{props.item.address}</div>
        <div className={classes.option}>{props.item.option}</div>
      </div>
      <div className={classes.distance}>{props.item.distance}</div>
      <div className={classes.line}></div>
    </div>
  );
};

export default Office;
