import { faAngleLeft } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { Fragment } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import { selectItemActions } from "../../store/selectItem";
import Button from "../UI/Button";
import classes from "./OfficeDetail.module.css";
const OfficeDetail = () => {
  const dispatch = useDispatch();
  const history = useHistory();
  const officeList = useSelector((state) => state.location.officeList);
  const params = useParams();
  const office = officeList.filter((elem) => elem.key == params.officeId);

  const prevBtnHandler = () => {
    history.push("/main");
  };
  const showDetailHandler = () => {
    history.push(`/officeDetail/${params.officeId}`);
    dispatch(selectItemActions.select({ itemName: null, itemPrice: null }));
  };
  return (
    <Fragment>
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
      <button onClick={showDetailHandler} className={classes.showDetailBtn}>
        Show Detail
      </button>
    </Fragment>
  );
};

export default OfficeDetail;
