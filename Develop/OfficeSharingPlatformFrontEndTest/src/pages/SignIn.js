import React, { useContext, useRef, useState } from "react";
import { useDispatch } from "react-redux";
import { Link, useHistory } from "react-router-dom";
import Button from "../components/UI/Button";
import Card from "../components/UI/Card";
import { AuthContext } from "../store/auth-Context";
import { modalActions } from "../store/modal";
import classes from "./SignUp.module.css";
const SignIn = () => {
  const dispatch = useDispatch();
  const history = useHistory();
  const authCtx = useContext(AuthContext);
  const [isLoading, setIsLoading] = useState(false);
  const emailInputRef = useRef();
  const passwordInputRef = useRef();
  const submitHandler = (e) => {
    e.preventDefault();
    const enteredEmail = emailInputRef.current.value;
    const enteredPassword = passwordInputRef.current.value;
    setIsLoading(true);
    fetch(
      "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyDa0MoK4QKzj8EDdhtfP5C2x7bVP7bPMns",
      {
        method: "POST",
        body: JSON.stringify({
          email: enteredEmail,
          password: enteredPassword,
          returnSecureToken: true,
        }),
        headers: {
          "Content-Type": "application/json",
        },
      }
    )
      .then((res) => {
        setIsLoading(false);
        if (res.ok) {
          return res.json();
        } else {
          return res.json().then((data) => {
            let errorMessage = "로그인 오류";
            errorMessage = data.error.message;
            if (data && data.error && data.error.message) {
              throw new Error(errorMessage);
            }
          });
        }
      })
      .then((data) => {
        dispatch(modalActions.modalHandler());
        localStorage.setItem("token", data.idToken);
        authCtx.login(data.idToken);
        history.replace("/");
      })
      .catch((err) => {
        alert(err.message);
      });
  };
  return (
    <Card className={classes.signIn}>
      <header>
        <Link to="/main" className={classes.headerLink}>
          <h1>Office Sharing Platform</h1>
        </Link>
      </header>
      <section className={classes.signUpForm}>
        <form onSubmit={submitHandler}>
          <input
            type="email"
            name="email"
            placeholder="아이디(이메일 형식)"
            ref={emailInputRef}
          />
          <input
            type="password"
            name="password"
            placeholder="패스워드"
            ref={passwordInputRef}
          />

          {isLoading && <p>loading...</p>}
          {!isLoading && <Button type="submit">로그인</Button>}
          <p className={classes.navLink}>
            계정이 없으신가요?<Link to="/auth/signup">회원가입</Link>
          </p>
        </form>
      </section>
    </Card>
  );
};

export default SignIn;
