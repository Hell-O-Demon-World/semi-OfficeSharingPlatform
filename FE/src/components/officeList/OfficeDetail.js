import { faAngleLeft } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import { useSelector } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import Button from "../UI/Button";
import classes from "./OfficeDetail.module.css";
const OfficeDetail = () => {
  const history = useHistory();
  const officeList = useSelector((state) => state.officeList);
  const params = useParams();
  const office = officeList.filter((elem) => elem.key === params.officeId);
  console.log(office);

  const prevBtnHandler = () => {
    history.push("/main");
  };

  return (
    <div className={classes.officeDetail}>
      <Button type="button" onClick={prevBtnHandler}>
        <FontAwesomeIcon icon={faAngleLeft} />
      </Button>
      <main className={classes.officeMainInfo}>
        <p>{office[0].name}</p>
        <p>{office[0].option}</p>
        <p>{office[0].address}</p>
        <p>{office[0].postcode}</p>
      </main>
    </div>
  );
};

export default OfficeDetail;
