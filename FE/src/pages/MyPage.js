import React, { Fragment, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import MainHeader from "../components/Layout/MainHeader";
import Card from "../components/UI/Card";
import ReservationCard from "../components/UI/ReservationCard";
import { myPageActions } from "../store/myPage";
import classes from "./MyPage.module.css";
const MyPage = () => {
  const [isLoading, setIsLoading] = useState(false);
  const dispatch = useDispatch();
  const userName = useSelector((state) => state.myPage.userName);
  const myPageReservationList = useSelector(
    (state) => state.myPage.myPageReservationList
  );
  const userId = localStorage.getItem("token");
  useEffect(() => {
    const sendRequest = async () => {
      setIsLoading(true);
      try {
        const response = await fetch(`/mypage/${userId}`);
        if (!response.ok) {
          throw new Error("Someting went wrong");
        }
        const reservationList = [];
        const data = await response.json();
        dispatch(myPageActions.userName(data.userName));

        for (const key in data.myPageReservationList) {
          reservationList.push({
            key,
            placeName: data.myPageReservationList[key].placeName,
            resDate: data.myPageReservationList[key].resDate,
            resTime: data.myPageReservationList[key].resTime,
            roomKind: data.myPageReservationList[key].roomKind,
          });
        }
        dispatch(myPageActions.reservationList(reservationList));
      } catch (error) {}
      setIsLoading(false);
    };
    sendRequest();
  }, [dispatch]);
  return (
    <Fragment>
      <MainHeader />
      <header className={classes.header}>My Page</header>
      <main>
        <Card className={classes.card}>
          <h1>회원 정보</h1>
          <div className={classes.userName}>
            이름 <p>{userName}</p>
          </div>
        </Card>
        <Card>
          <h1>예약 리스트</h1>
          <div className={classes.line}></div>
          <div className={classes.listBox}>
            {myPageReservationList.map((elem) => (
              <ReservationCard item={elem} key={elem.key} />
            ))}
          </div>
        </Card>
      </main>
    </Fragment>
  );
};

export default MyPage;
