import React, { Fragment } from "react";
import { useEffect } from "react";
import { useParams } from "react-router-dom";
import MainHeader from "../components/Layout/MainHeader";
import Desk from "../components/OfficeDetail/Desk";
import MeetingRoom from "../components/OfficeDetail/MeetingRoom";
import OfficeProducts from "../components/OfficeDetail/OfficeProduct";
import ReservationForm from "../components/OfficeDetail/ReservationForm";
import classes from "../pages/OfficeMain.module.css";
const OfficeDetail = () => {
  const params = useParams();
  useEffect(() => {
    const requestOfficeProducts = async () => {
      try {
        const response = await fetch(
          `https://react-http-673e2-default-rtdb.firebaseio.com/office/${params.officeId}.json`
        );
        if (!response.ok) {
          throw new Error("Somethig went wrong");
        }
        const data = await response.json();
        console.log(data.office);
      } catch (error) {}
    };
    requestOfficeProducts();
  }, [params.officeId]);
  return (
    <Fragment>
      <MainHeader />
      <main>
        <div className={classes.productList}>
          <Desk />
          <MeetingRoom />
          <OfficeProducts />
        </div>
        <div className={classes.reservationForm}>
          <ReservationForm />
        </div>
      </main>
    </Fragment>
  );
};

export default OfficeDetail;
