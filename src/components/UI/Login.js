import React, { useRef, useState } from "react";

import Button from "./Button";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faX } from "@fortawesome/free-solid-svg-icons";

import classes from "./Login.module.css";

var emailPattern = new RegExp(
  /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
);
var passwordPattern = new RegExp(
  "^(?=.*[A-Za-z])(?=.*d)(?=.*[@$!%*#?&])[A-Za-zd@$!%*#?&]{8,}$"
);
const Login = (props) => {
  const emailInput = useRef();
  const passwordInput = useRef();

  const [emailError, setEmailError] = useState();
  const [passwordError, setPasswordError] = useState();
  const [enteredEmail, setEnteredEmail] = useState("");
  const [enteredPassword, setEnteredPassword] = useState("");

  const submitFormHandler = (e) => {
    e.preventDefault();
    setEnteredEmail(emailInput.current.value);
    setEnteredPassword(passwordInput.current.value);
    if (!emailPattern.test(enteredEmail)) {
      setEmailError({
        title: "유효하지 않은 이메일 양식",
      });
      return;
    }
    if (!passwordPattern.test(enteredPassword)) {
      setPasswordError({
        title: "유효하지 않은 비밀번호 양식",
      });
      return;
    }
    emailInput.current.value = "";
    passwordInput.current.value = "";
  };
  const emailClass = emailError ? classes.error : "";
  const passwordClass = passwordError ? classes.error : "";
  return (
    <div className={classes.login}>
      <FontAwesomeIcon
        icon={faX}
        className={classes.close}
        onClick={props.onConfirm}
      />
      <header className={classes.header}>Office Sharing Platform</header>
      <form onSubmit={submitFormHandler} className={classes.form}>
        <div className={classes.inputInfo}>
          <div className={classes.inputElem}>
            <input
              type="email"
              placeholder="이메일"
              className={emailClass}
              ref={emailInput}
            />
            {emailError && <p>{emailError.title}</p>}
          </div>
          <div className={classes.inputElem}>
            <input
              type="password"
              placeholder="비밀번호"
              className={passwordClass}
              ref={passwordInput}
            />
            {passwordError && <p>{passwordError.title}</p>}
          </div>
        </div>
        <button type="submit" className={classes.button}>
          Login
        </button>
      </form>
      <ul className={classes.buttonList}>
        <li>
          <Button>아이디 찾기</Button>
        </li>
        <li>|</li>
        <li>
          <Button>비밀번호 찾기</Button>
        </li>
      </ul>
    </div>
  );
};

export default Login;
