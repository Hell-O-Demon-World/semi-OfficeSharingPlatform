import React, { useContext, useRef, useState } from "react";
import { Link } from "react-router-dom";
import Button from "../components/UI/Button";
import Card from "../components/UI/Card";
import AuthContext from "../store/auth-context";
import classes from "./SignUp.module.css";
const SignIn = () => {
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
        header: {
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
        authCtx.login(data.idToken);
        console.log(authCtx.isLoggenIn);
      })
      .catch((err) => {
        alert(err.message);
      });
  };
  return (
    <Card>
      <header>
        <Link to="/main" className={classes.headerLink}>
          Office Sharing Platform
        </Link>
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
          </form>
        </section>
      </header>
    </Card>
  );
};

export default SignIn;
