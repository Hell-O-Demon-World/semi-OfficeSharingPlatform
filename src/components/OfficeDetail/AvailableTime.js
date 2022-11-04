import React, { useEffect } from "react";

import { Swiper, SwiperSlide } from "swiper/react";
import { useDispatch, useSelector } from "react-redux";
import TimeButton from "../UI/TimeButton";
import classes from "./AvailableTime.module.css";
import "swiper/swiper-bundle.min.css";
import Card from "../UI/Card";
import { selectTimeActions } from "../../store/selectTime";
import Button from "../UI/Button";

const AvailableTime = () => {
  const selectDate = useSelector((state) => state.item.date);
  const dispatch = useDispatch();
  const avaialbleTimeList = useSelector((state) => state.time.timelist);
  const timeList = useSelector((state) => state.selectTime.timeList);
  const selectTimeList = useSelector(
    (state) => state.selectTime.selectTimeList
  );
  let availableFullTimeList = [];

  for (let i = selectTimeList[0]; i <= selectTimeList[1]; i++) {
    if (timeList.includes(i)) {
      availableFullTimeList.push(i);
    } else {
      timeList.forEach((elem) => {
        if (!!document.getElementById(`time${elem}`)) {
          document.getElementById(`time${elem}`).style.backgroundColor = "#fff";
        }
      });
      availableFullTimeList = [];
      alert("예약이 불가능한 시간이 포함되어 있습니다.");
      dispatch(selectTimeActions.deleteList());
      break;
    }
  }
  const selectTimeHandler = (e) => {
    if (selectTimeList.length === 2) {
      timeList.forEach(
        (elem) =>
          (document.getElementById(`time${elem}`).style.backgroundColor =
            "#fff")
      );
    }
    dispatch(selectTimeActions.select(e.target.id));
    document.getElementById(`time${e.target.id}`).style.backgroundColor =
      "rgb(106, 158, 255)";
  };
  useEffect(() => {
    availableFullTimeList.forEach((elem) => {
      document.getElementById(`time${elem}`).style.backgroundColor =
        "rgb(106, 158, 255)";
    });
  }, [availableFullTimeList]);
  return (
    <Card className={classes.card}>
      <div className={classes.line}></div>
      <h1>이용 가능 시간</h1>
      <Swiper slidesPerView={8} className={classes.swiper}>
        {avaialbleTimeList.map((elem, idx) =>
          !!elem ? (
            <SwiperSlide
              key={`time${idx}`}
              className={classes.active}
              onClick={selectTimeHandler}
              id={`time${idx}`}
            >
              <TimeButton time={idx} />
            </SwiperSlide>
          ) : (
            <SwiperSlide key={`time${idx}`} className={classes.nonActive}>
              <TimeButton time={idx} />
            </SwiperSlide>
          )
        )}
      </Swiper>
      <div className={classes.checkStatus}>
        <label>불가</label>
        <div className={classes.disabled}></div>
        <label>가능</label>
        <div className={classes.available}></div>
        <label>선택</label>
        <div className={classes.checked}></div>
      </div>
      <div>
        <h3>예약 시간</h3>
        <p>날짜</p>
        {selectDate}
        <p>시간</p>
        {selectTimeList[0] &&
          !selectTimeList[1] &&
          `${selectTimeList[0]}:00 ~ ${selectTimeList[0]}:00`}
        {selectTimeList[1] &&
          `${selectTimeList[0]}:00 ~ ${selectTimeList[1]}:00`}
      </div>
      <div className={classes.line}></div>
      {selectTimeList[0] && <Button>예약</Button>}
    </Card>
  );
};

export default AvailableTime;
