import React from "react";
import Office from "./Office";

import classes from "./OfficeList.module.css";
import OfficeSearch from "./OfficeSearch";
const DUMMY_OFFICE = [
  {
    name: "롯데월드점",
    address: "서울 송파구 올림픽로 240 (잠실동) 롯데월드 웰빙센터 8층",
    option: "다락 직영ㅣ24시간 방문가능ㅣ무료주차 가능",
    distance: "6.3km",
  },
  {
    name: "건대점",
    address: "서울 광진구 화양동 94-9 프라하임오피스텔 B1",
    option: "다락 직영ㅣ24시간 방문가능ㅣ무료주차 가능",
    distance: "6.7km",
  },
];

const OfficeList = () => {
  return (
    <div className={classes.officeList}>
      <OfficeSearch />
      {DUMMY_OFFICE.map((elem) => (
        <Office item={elem} />
      ))}
    </div>
  );
};

export default OfficeList;
