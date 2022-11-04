import React from "react";

const TimeButton = (props) => {
  const click = (e) => {
    e.stopPropagation();
  };
  return (
    <div id={props.time}>
      <p onClick={click}>{props.time + ":00"}</p>
    </div>
  );
};

export default TimeButton;
