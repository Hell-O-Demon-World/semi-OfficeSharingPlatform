import React, { Fragment } from "react";
import { useDispatch } from "react-redux";
import Button from "../UI/Button";
import classes from "./Product.module.css";
import { selectItemActions } from "../../store/selectItem";
const MeetingRoom = () => {
  const dispatch = useDispatch();
  const selectItemHandler = () => {
    dispatch(selectItemActions.select("MeetingRoom"));
  };
  return (
    <Fragment>
      <main className={classes.container}>
        <div>
          <h1>4인 회의실</h1>
          <div>4인실 - 업무 회의를 위한 공간으로 적합합니다.</div>
        </div>
        <div>
          <Button className={classes.checkBtn} onClick={selectItemHandler}>
            예약하기
          </Button>
        </div>
      </main>
    </Fragment>
  );
};

export default MeetingRoom;
