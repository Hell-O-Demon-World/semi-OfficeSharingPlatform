import React, { Fragment } from "react";
import { Link } from "react-router-dom";
import Button from "../components/UI/Button";
import Card from "../components/UI/Card";
import classes from "./SignUp.module.css";
const SignUp = () => {
  return (
    <Fragment>
      <Card className={classes.signUp}>
        <header>Office Sharing Platform</header>
        <section className={classes.signUpForm}>
          <form>
            <input
              type="email"
              name="email"
              placeholder="아이디(이메일 형식)"
            />
            <input type="password" name="password" placeholder="패스워드" />
            <input type="text" name="name" placeholder="이름" />
            <input type="text" name="phone" placeholder="전화번호" />
            <input type="text" name="job" placeholder="직업" />
            <div className={classes.select}>
              <p>선호 공간 선택</p>
              <div className={classes.selectBox}>
                <label htmlFor="desk">
                  <input type="checkbox" name="desk" value="desk" />
                  데스크
                </label>
                <label htmlFor="meeting">
                  <input type="checkbox" name="meeting" value="meeting" />
                  회의실
                </label>
                <label htmlFor="office">
                  <input type="checkbox" name="office" value="office" />
                  오피스
                </label>
              </div>
            </div>
            <Button>가입</Button>
          </form>
          <p className={classes.navLink}>
            계정이 있으신가요?<Link to="/auth/signin">로그인</Link>
          </p>
        </section>
      </Card>
    </Fragment>
  );
};

export default SignUp;
