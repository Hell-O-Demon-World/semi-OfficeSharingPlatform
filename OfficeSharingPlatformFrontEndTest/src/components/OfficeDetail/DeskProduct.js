import React, { Fragment } from "react";
import { useDispatch, useSelector } from "react-redux";
import Button from "../UI/Button";
import classes from "./Product.module.css";
import { selectItemActions } from "../../store/selectItem";
const Desk = () => {
  const dispatch = useDispatch();
  const selectItemHandler = () => {
    dispatch(
      selectItemActions.select({
        itemName: "1인 Desk",
        itemPrice: "10,000 / hour",
      })
    );
    dispatch(selectItemActions.hideTimeLine());
  };
  const desk = useSelector((state) => state.availableItem.desk);
  return (
    <Fragment>
      <h1 className={classes.productName}>DESK</h1>
      {!desk && <h1>이용 가능한 Desk가 없습니다.</h1>}
      {!!desk && (
        <main className={`${classes.desk} + ${classes.container}`}>
          <div>
            <h1>1인 Desk</h1>
            <div>1인이 공부하실 때 적합한 공간에 대한 예약상품입니다.</div>
            <br />
            <div>이용 요금 - 10,000 / hour</div>
          </div>
          <div>
            <Button className={classes.checkBtn} onClick={selectItemHandler}>
              예약하기
            </Button>
          </div>
        </main>
      )}
    </Fragment>
  );
};

export default Desk;
