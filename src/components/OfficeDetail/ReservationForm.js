import React, { Fragment } from "react";
import { useSelector } from "react-redux";
import Button from "../UI/Button";
import DatePick from "../UI/DatePick";
import classes from "./ReservationForm.module.css";
const ReservationForm = () => {
  const selecItem = useSelector((state) => state.item.selectItem);
  const isSelected = useSelector((state) => state.item.isSelected);
  return (
    <Fragment>
      {!isSelected && <h1>원하는 상품을 선택</h1>}
      {isSelected && (
        <form>
          <h1>예약 신청</h1>
          <div>
            <label htmlFor="selectProduct">선택 상품명</label>
            <input
              type="text"
              value={selecItem}
              disabled
              className={classes.selectItemInput}
            />
          </div>
          <div>
            <label htmlFor="selectDate">이용 날짜 선택</label>
            <DatePick />
          </div>
          <Button>시간 확인</Button>
        </form>
      )}
    </Fragment>
  );
};

export default ReservationForm;
