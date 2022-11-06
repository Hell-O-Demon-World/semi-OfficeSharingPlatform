import { faXmark } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import ReactDOM from "react-dom";
import { useDispatch } from "react-redux";
import SignIn from "../../pages/SignIn";
import { modalActions } from "../../store/modal";
import Card from "../UI/Card";
import classes from "./Modal.module.css";

const Backdrop = (props) => {
  return <div className={classes.backdrop} onClick={props.onConfirm} />;
};
const ModalOverlay = () => {
  const dispatch = useDispatch();
  const confirmHandler = () => {
    dispatch(modalActions.modalHandler());
  };
  return (
    <Card className={classes.modal}>
      <FontAwesomeIcon
        icon={faXmark}
        className={classes.close}
        onClick={confirmHandler}
      />
      <SignIn />
    </Card>
  );
};

const Modal = () => {
  const dispatch = useDispatch();
  const confirmHandler = () => {
    dispatch(modalActions.modalHandler());
  };
  return (
    <div>
      <React.Fragment>
        {ReactDOM.createPortal(
          <Backdrop onConfirm={confirmHandler} />,
          document.getElementById("backdrop-root")
        )}
        {ReactDOM.createPortal(
          <ModalOverlay onConfirm={confirmHandler} />,
          document.getElementById("overlay-root")
        )}
      </React.Fragment>
    </div>
  );
};

export default Modal;
