import React, { Fragment } from "react";
import { useSelector } from "react-redux";
import Button from "../UI/Button";
import DatePick from "../UI/DatePick";
import classes from "./ReservationForm.module.css";
const ReservationForm = () => {
  const itemName = useSelector((state) => state.item.itemName);
  const isSelected = useSelector((state) => state.item.isSelected);
  const itemPrice = useSelector((state) => state.item.itemPrice);
  const checkAvailableTime = async () => {
    try {
      const response = await fetch(
        "https://react-http-673e2-default-rtdb.firebaseio.com/timeList.json"
      );
      if (!response.ok) {
        throw new Error("Something went Wrong");
      }
      const data = await response.json();
      console.log(data);
    } catch (err) {
      return err;
    }
  };
  return (
    <Fragment>
      {!isSelected && <h1>원하는 상품을 선택</h1>}
      {!!isSelected && (
        <form>
          <h1>예약 신청</h1>
          <div>
            <label htmlFor="selectProduct">선택 상품명</label>
            <input
              type="text"
              value={itemName}
              disabled
              className={classes.formInput}
            />
          </div>
          <div>
            <label htmlFor="selectDate">이용 날짜 선택</label>
            <DatePick />
          </div>
          <div>
            <label htmlFor="totalPrice">결제 금액</label>
            <input
              type="text"
              className={classes.formInput}
              disabled
              value={itemPrice}
            />
          </div>
          <Button onClick={checkAvailableTime}>시간 확인</Button>
        </form>
      )}
    </Fragment>
  );
};

export default ReservationForm;
