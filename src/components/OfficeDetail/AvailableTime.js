import React from "react";

import { Swiper, SwiperSlide } from "swiper/react";
import { useSelector } from "react-redux";
import TimeButton from "../UI/TimeButton";
import classes from "./AvailableTime.module.css";
import "swiper/swiper-bundle.min.css";
import { useEffect } from "react";

const AvailableTime = () => {
  const avaialbleTimeList = useSelector((state) => state.time.timelist);

  return (
    <div>
      <h1>이용 가능 시간</h1>
      <Swiper slidesPerView={8} spaceBetween={0} className={classes.swiper}>
        {avaialbleTimeList.map((elem, idx) =>
          elem ? (
            <SwiperSlide key={idx} className={classes.active}>
              <TimeButton time={idx} />
            </SwiperSlide>
          ) : (
            <SwiperSlide key={idx} className={classes.nonActive}>
              <TimeButton time={idx} />
            </SwiperSlide>
          )
        )}
      </Swiper>
    </div>
  );
};

export default AvailableTime;
