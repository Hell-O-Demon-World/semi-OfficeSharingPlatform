import React, { Fragment, useState } from "react";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useParams } from "react-router-dom";
import MainHeader from "../components/Layout/MainHeader";
import Desk from "../components/OfficeDetail/DeskProduct";
import MeetingRoom from "../components/OfficeDetail/MeetingRoomProduct";
import OfficeProducts from "../components/OfficeDetail/OfficeProduct";
import ReservationForm from "../components/OfficeDetail/ReservationForm";
import { availableItemActions } from "../store/availableItem";
import classes from "../pages/OfficeMain.module.css";
const OfficeDetail = () => {
  const [isLoading, setIsLoading] = useState(false);
  const dispatch = useDispatch();

  const params = useParams();
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
            <div className={classes.item}>
              <Desk />
            </div>
            <div className={classes.item}>
              <MeetingRoom />
            </div>
            <div className={classes.item}>
              <OfficeProducts />
            </div>
          </div>
          <div className={classes.reservationForm}>
            <ReservationForm />
          </div>
        </main>
      )}
    </Fragment>
  );
};

export default OfficeDetail;
