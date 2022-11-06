import React from "react";
import Button from "./Button";
import Card from "./Card";
import classes from "./ReservationCard.module.css";
const ReservationCard = (props) => {
  const reservationCancelHandler = async () => {
    try {
      const userId = localStorage.getItem("token");
      const response = await fetch(
        `mypage/${userId}/cancel?order=${props.item.key}`
      );
      if (!response.ok) {
        throw new Error("Someting went wrong");
      }
      alert("취소 성공");
    } catch (error) {
      alert(error);
    }
  };
  return (
    <Card className={classes.Card}>
      <div>
        <p>{props.item.resDate}</p>
        <p>{props.item.resTime}</p>
      </div>
      <div>
        <p>{props.item.placeName}</p>
      </div>
      <div className={classes.productType}>
        <h3>상품명</h3>
        <p>{props.item.roomKind}</p>
      </div>
      <Button onClick={reservationCancelHandler}>예약 취소</Button>
    </Card>
  );
};

export default ReservationCard;
