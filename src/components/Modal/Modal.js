import React from "react";
import ReactDOM from "react-dom";
import AddOfficeTest from "../officeList/AddOfficeTest";
import Card from "../UI/Card";
import Login from "../UI/Login";
import classes from "./Modal.module.css";

const Backdrop = (props) => {
  return <div className={classes.backdrop} onClick={props.onConfirm} />;
};
const ModalOverlay = (props) => {
  return (
    <Card className={classes.modal}>
      <Login onConfirm={props.onConfirm} />
      <AddOfficeTest />
    </Card>
  );
};

const Modal = (props) => {
  return (
    <div>
      <React.Fragment>
        {ReactDOM.createPortal(
          <Backdrop onConfirm={props.onConfirm} />,
          document.getElementById("backdrop-root")
        )}
        {ReactDOM.createPortal(
          <ModalOverlay onConfirm={props.onConfirm} />,
          document.getElementById("overlay-root")
        )}
      </React.Fragment>
    </div>
  );
};

export default Modal;
