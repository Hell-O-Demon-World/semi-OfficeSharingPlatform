import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { avaialbleTimeActions } from "../../store/availableTime";
import { selectItemActions } from "../../store/selectItem";
import { selectTimeActions } from "../../store/selectTime";
import Button from "../UI/Button";
import Card from "../UI/Card";
import DatePick from "../UI/DatePick";
import AvailableTime from "./AvailableTime";
import classes from "./ReservationForm.module.css";
const ReservationForm = () => {
  const [isLoading, setIsLoading] = useState(false);
  const dispatch = useDispatch();
  const itemName = useSelector((state) => state.item.itemName);
  const isSelected = useSelector((state) => state.item.isSelected);
  const itemPrice = useSelector((state) => state.item.itemPrice);
  const showTimeLine = useSelector((state) => state.item.showTimeLine);
  const availableTimeList = (data) => {
    let timeArr = new Array(24);
    timeArr.fill(0, 0, 24);
    data.forEach((elem) => {
      if (elem !== 0) {
        timeArr[elem] += 1;
      }
    });
    return timeArr;
  };
  const checkAvailableTime = async () => {
    dispatch(selectTimeActions.deleteList());
    dispatch(selectItemActions.showTimeLine());
    try {
      setIsLoading(true);
      const response = await fetch(
        "https://react-http-673e2-default-rtdb.firebaseio.com/timeList.json"
      );
      if (!response.ok) {
        throw new Error("Something went Wrong");
      }
      setIsLoading(false);
      const data = await response.json();
      dispatch(
        avaialbleTimeActions.checkTimeList(availableTimeList(data.timeList))
      );
      dispatch(selectTimeActions.getTimeList(data.timeList));
    } catch (err) {
      return err;
    }
  };
  return (
    <Card>
      {!isSelected && <h1>원하는 상품을 선택</h1>}
      {!!isSelected && (
        <form className={classes.productForm}>
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
      {!!isSelected && showTimeLine && !isLoading && <AvailableTime />}
      {isLoading && <p>Loading...</p>}
    </Card>
  );
};

export default ReservationForm;
