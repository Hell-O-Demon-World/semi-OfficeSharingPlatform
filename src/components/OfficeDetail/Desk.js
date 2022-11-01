import React, { Fragment } from "react";
import { useDispatch } from "react-redux";
import Button from "../UI/Button";
import classes from "./Product.module.css";
import { selectItemActions } from "../../store/selectItem";
const Desk = () => {
  const dispatch = useDispatch();
  const selectItemHandler = () => {
    dispatch(selectItemActions.select("desk"));
  };
  return (
    <Fragment>
      <main className={`${classes.desk} + ${classes.container}`}>
        <div>
          <h1>1인 Desk</h1>
          <div>1인이 공부하실 때 적합한 공간에 대한 예약상품입니다.</div>
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

export default Desk;
