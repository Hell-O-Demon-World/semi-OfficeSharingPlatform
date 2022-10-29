import { faXmark } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import ReactDOM from "react-dom";
import SignIn from "../../pages/SignIn";
import Card from "../UI/Card";
import classes from "./Modal.module.css";

const Backdrop = (props) => {
  return <div className={classes.backdrop} onClick={props.onConfirm} />;
};
const ModalOverlay = (props) => {
  return (
    <Card className={classes.modal}>
      <FontAwesomeIcon
        icon={faXmark}
        className={classes.close}
        onClick={props.onConfirm}
      />
      <SignIn />
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
