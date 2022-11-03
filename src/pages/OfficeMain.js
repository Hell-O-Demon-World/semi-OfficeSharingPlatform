import React, { Fragment, useState } from "react";

import { Route } from "react-router-dom";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useParams, useRouteMatch } from "react-router-dom";
import MainHeader from "../components/Layout/MainHeader";
import Desk from "../components/OfficeDetail/DeskProduct";
import MeetingRoom from "../components/OfficeDetail/MeetingRoomProduct";
import OfficeProducts from "../components/OfficeDetail/OfficeProduct";
import ReservationForm from "../components/OfficeDetail/ReservationForm";
import { availableItemActions } from "../store/availableItem";
import classes from "../pages/OfficeMain.module.css";
import ProductHeader from "../components/Layout/ProductHeader";
const OfficeMain = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [clickItem, setClickItem] = useState(null);
  const dispatch = useDispatch();
  const params = useParams();
  const { url } = useRouteMatch();
  const clickItemHandler = (item) => {
    setClickItem((prevState) => (prevState = item));
  };
  useEffect(() => {
    const requestOfficeProducts = async () => {
      try {
        setIsLoading(true);
        const response = await fetch(
          `https://react-http-673e2-default-rtdb.firebaseio.com/office/${params.officeId}.json`
        );
        if (!response.ok) {
          throw new Error("Somethig went wrong");
        }
        setIsLoading(false);
        const data = await response.json();
        dispatch(availableItemActions.selectdesk(data.desk));
        dispatch(availableItemActions.selectMeetingRoom(data.meetingRoom));
        dispatch(availableItemActions.selectOffice(data.office));
      } catch (error) {}
    };
    requestOfficeProducts();
  }, [params.officeId, dispatch]);
  return (
    <Fragment>
      <MainHeader />
      {isLoading && <p>Loading...</p>}
      {!isLoading && (
        <main className={classes.container}>
          <div className={classes.productList}>
            <ProductHeader clickItem={clickItemHandler} />
            <Route path={`${url}/desk`}>
              <div className={classes.item}>
                <Desk />
              </div>
            </Route>
            <Route path={`${url}/meetingRoom`}>
              <div className={classes.item}>
                <MeetingRoom />
              </div>
            </Route>
            <Route path={`${url}/office`}>
              <div className={classes.item}>
                <OfficeProducts />
              </div>
            </Route>
          </div>
          <div className={classes.reservationForm}>
            <ReservationForm />
          </div>
        </main>
      )}
    </Fragment>
  );
};

export default OfficeMain;
