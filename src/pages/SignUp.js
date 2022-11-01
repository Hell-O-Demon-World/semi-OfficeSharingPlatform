import React, { useRef, useState } from "react";
import { Link } from "react-router-dom";
import Button from "../components/UI/Button";
import Card from "../components/UI/Card";
import classes from "./SignUp.module.css";
const SignUp = () => {
  const [isLoading, setIsLoading] = useState(false);
  const emailInputRef = useRef();
  const passwordInputRef = useRef();
  const nameInputRef = useRef();
  const phoneInputRef = useRef();
  const jobInputRef = useRef();
  const deskCheckedRef = useRef();
  const meetingCheckedRef = useRef();
  const officeCheckedRef = useRef();
  const submitHandler = (e) => {
    e.preventDefault();
    const enteredEmail = emailInputRef.current.value;
    const enteredPassword = passwordInputRef.current.value;
    const enteredName = nameInputRef.current.value;
    const enteredPhone = phoneInputRef.current.value;
    const enteredJob = jobInputRef.current.value;
    const checkedDesk = deskCheckedRef.current.checked;
    const checkedMeeting = meetingCheckedRef.current.checked;
    const checkedOffice = officeCheckedRef.current.checked;
    console.log(checkedDesk, checkedMeeting, checkedOffice);
    setIsLoading(true);
    console.log(
      JSON.stringify({
        email: enteredEmail,
        password: enteredPassword,
        name: enteredName,
        phone: enteredPhone,
        job: enteredJob,
        preferType: [
          {
            desk: checkedDesk,
            meetingroom: checkedMeeting,
            office: checkedOffice,
          },
        ],
      })
    );
    fetch(
      "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyDa0MoK4QKzj8EDdhtfP5C2x7bVP7bPMns",
      {
        method: "POST",
        body: JSON.stringify({
          email: enteredEmail,
          password: enteredPassword,
          name: enteredName,
          phone: enteredPhone,
          job: enteredJob,
          preferType: [
            {
              desk: checkedDesk,
              meetingroom: checkedMeeting,
              office: checkedOffice,
            },
          ],
        }),
        headers: {
          "Content-Type": "application/json",
        },
      }
    ).then((res) => {
      setIsLoading(false);
      if (res.ok) {
        console.log("success");
      } else {
        return res.json().then((data) => {
          let errorMessage = "회원가입 오류";
          errorMessage = data.error.message;
          if (data && data.error && data.error.message) {
            alert(errorMessage);
          }
        });
      }
    });
  };
  return (
    <Card className={classes.signUp}>
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
          <input
            type="text"
            name="name"
            placeholder="이름"
            ref={nameInputRef}
          />
          <input
            type="text"
            name="phone"
            placeholder="전화번호"
            ref={phoneInputRef}
          />
          <input type="text" name="job" placeholder="직업" ref={jobInputRef} />
          <div className={classes.select}>
            <p>선호 공간 선택</p>
            <div className={classes.selectBox}>
              <label htmlFor="desk">
                <input
                  type="checkbox"
                  name="desk"
                  value="desk"
                  ref={deskCheckedRef}
                />
                데스크
              </label>
              <label htmlFor="meeting">
                <input
                  type="checkbox"
                  name="meeting"
                  value="meeting"
                  ref={meetingCheckedRef}
                />
                회의실
              </label>
              <label htmlFor="office">
                <input
                  type="checkbox"
                  name="office"
                  value="office"
                  ref={officeCheckedRef}
                />
                오피스
              </label>
            </div>
          </div>
          {isLoading && <p>loading...</p>}
          {!isLoading && <Button type="submit">가입</Button>}
        </form>
        <p className={classes.navLink}>
          계정이 있으신가요?<Link to="/auth/signin">로그인</Link>
        </p>
      </section>
    </Card>
  );
};

export default SignUp;
