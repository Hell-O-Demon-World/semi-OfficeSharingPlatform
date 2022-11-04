import React, { useEffect } from "react";

import { Swiper, SwiperSlide } from "swiper/react";
import { useDispatch, useSelector } from "react-redux";
import TimeButton from "../UI/TimeButton";
import classes from "./AvailableTime.module.css";
import "swiper/swiper-bundle.min.css";
import Card from "../UI/Card";
import { selectTimeActions } from "../../store/selectTime";

const AvailableTime = () => {
  const dispatch = useDispatch();
  const avaialbleTimeList = useSelector((state) => state.time.timelist);
  const timeList = useSelector((state) => state.selectTime.timeList);
  const selectTimeList = useSelector(
    (state) => state.selectTime.selectTimeList
  );
  console.log(selectTimeList);
  let availableFullTimeList = [];

  for (let i = selectTimeList[0]; i <= selectTimeList[1]; i++) {
    if (timeList.includes(i)) {
      availableFullTimeList.push(i);
    } else {
      timeList.forEach((elem) => {
        if (!!document.getElementById(`${elem}`)) {
          document.getElementById(`${elem}`).style.backgroundColor = "#fff";
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
        (elem) => (document.getElementById(elem).style.backgroundColor = "#fff")
      );
    }
    dispatch(selectTimeActions.select(e.target.id));
    document.getElementById(`${e.target.id}`).style.backgroundColor =
      "rgb(106, 158, 255)";
  };
  useEffect(() => {
    availableFullTimeList.forEach((elem) => {
      document.getElementById(`${elem}`).style.backgroundColor =
        "rgb(106, 158, 255)";
    });
  }, [availableFullTimeList]);
  return (
    <Card className={classes.card}>
      <div className={classes.line}></div>
      <h1>이용 가능 시간</h1>
      <Swiper slidesPerView={8} spaceBetween={5} className={classes.swiper}>
        {avaialbleTimeList.map((elem, idx) =>
          !!elem ? (
            <SwiperSlide
              key={idx}
              className={classes.active}
              onClick={selectTimeHandler}
              id={idx}
            >
              <TimeButton time={idx} />
            </SwiperSlide>
          ) : (
            <SwiperSlide key={idx} className={classes.nonActive}>
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
      <div className={classes.line}></div>
    </Card>
  );
};

export default AvailableTime;
