import React, { Fragment } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Swiper, SwiperSlide } from "swiper/react";
import Button from "../UI/Button";
import classes from "./Product.module.css";
import { selectItemActions } from "../../store/selectItem";
import SwiperCore, { Navigation } from "swiper";
import "swiper/swiper-bundle.min.css";
SwiperCore.use([Navigation]);
const moneyPerHour = (count) => {
  return (count * 5000).toLocaleString("ko-KR");
};
const Office = () => {
  const dispatch = useDispatch();
  const selectItemHandler = (e) => {
    dispatch(selectItemActions.hideTimeLine());
    dispatch(
      selectItemActions.select({
        itemName: `${e.target.id}인 회의실`,
        itemPrice: `${moneyPerHour(e.target.id)} / hour`,
        itemId: `office${e.target.id}`,
      })
    );
  };
  const office = useSelector((state) => state.availableItem.office);
  return (
    <Fragment>
      <h1 className={classes.productName}>Office</h1>
      {!office && <h1>예약 가능한 Office가 없습니다.</h1>}
      {!!office && (
        <Swiper navigation={true} spaceBetween={50} slidesPerView={3}>
          {office.map((elem) => (
            <SwiperSlide
              className={`${classes.container} + ${classes.swiperContainer}`}
              key={elem}
            >
              <div>
                <h1>{`${elem}`}평 회의실</h1>
                <div>
                  기업을 위한 Office 공간 <br /> 한 달 단위 예약 가능
                </div>
                <div>이용 요금 - {`${moneyPerHour(elem)}`} / day</div>
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

export default Office;
