import React, { Fragment } from "react";
import { Swiper, SwiperSlide } from "swiper/react";

import { useDispatch, useSelector } from "react-redux";
import Button from "../UI/Button";
import classes from "./Product.module.css";
import { selectItemActions } from "../../store/selectItem";
import SwiperCore, { Navigation } from "swiper";
import "swiper/swiper-bundle.min.css";

SwiperCore.use([Navigation]);
const moneyPerHour = (count) => {
  return (count * 5000).toLocaleString("ko-KR");
};
const MeetingRoomProduct = () => {
  const dispatch = useDispatch();
  const selectItemHandler = (e) => {
    dispatch(selectItemActions.hideTimeLine());
    dispatch(
      selectItemActions.select({
        itemName: `${e.target.id}인 회의실`,
        itemPrice: `${moneyPerHour(e.target.id)} / hour`,
        itemId: `meetingRoom${e.target.id}`,
      })
    );
  };
  const meetingRoom = useSelector((state) => state.availableItem.meetingRoom);
  return (
    <Fragment>
      <h1 className={classes.productName}>Meeting Room</h1>
      {!meetingRoom.length && <h1>이용 가능한 meetingRoom이 없습니다.</h1>}
      {!!meetingRoom && (
        <Swiper
          navigation={true}
          className="mySwiper"
          spaceBetween={50}
          slidesPerView={3}
        >
          {meetingRoom.map((elem) => (
            <SwiperSlide
              className={`${classes.container} + ${classes.swiperContainer}`}
              key={elem}
            >
              <div>
                <h1>{`${elem}`}인 회의실</h1>
                <div>
                  업무 회의를 위한 공간으로 적합합니다. {`최대 인원${elem}명`}
                </div>
                <br />
                <div>이용 요금 - {`${moneyPerHour(elem)}`}/hour</div>
              </div>
              <div>
                <Button
                  id={elem}
                  className={classes.checkBtn}
                  onClick={selectItemHandler}
                >
                  예약하기
                </Button>
              </div>
            </SwiperSlide>
          ))}
        </Swiper>
      )}
    </Fragment>
  );
};

export default MeetingRoomProduct;
