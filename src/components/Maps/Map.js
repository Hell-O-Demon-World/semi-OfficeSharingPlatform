import {
  faLocationCrosshairs,
  faMinus,
  faPlus,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { Fragment, useEffect, useState } from "react";

import classes from "./Map.module.css";
const { kakao } = window;

const Map = () => {
  const [map, setMap] = useState();
  const zoomIn = () => {
    map.setLevel(map.getLevel() - 1);
  };
  const zoomOut = () => {
    map.setLevel(map.getLevel() + 1);
  };
  const getCurrentLocation = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        const lat = position.coords.latitude,
          lng = position.coords.longitude;
        const currentLocation = new kakao.maps.LatLng(lat, lng);
        map.panTo(currentLocation);
      });
    }
  };
  useEffect(() => {
    kakao.maps.load(() => {
      const container = document.getElementById("map");
      const mainLocation = new kakao.maps.LatLng(
        37.523383097083546,
        127.05459973415205
      );
      const options = {
        center: mainLocation,
        level: 3,
      };
      const map = new kakao.maps.Map(container, options);
      setMap(map);
      const iwContent = `<div class = "wrap">
                          <div class = "customOverlay">Golfzon</div>
                          <div class = "arrow"></div>
                        </div>`;
      let overlay = new kakao.maps.CustomOverlay({
        content: iwContent,
        map: map,
        position: mainLocation,
      });
      overlay.setMap(map);
      const polyline = new kakao.maps.Polyline({
        path: [
          new kakao.maps.LatLng(37.523853615766534, 127.05514873622471),
          new kakao.maps.LatLng(37.49147998517995, 127.07276489941023),
        ],
      });
      console.log(polyline.getLength());
    });
  }, []);
  return (
    <Fragment>
      <div id="map" className={classes.map}>
        <div className={`${classes.customZoomcontrol} ${classes.radiusBorder}`}>
          <span onClick={zoomIn}>
            <FontAwesomeIcon icon={faPlus} className={classes.icon} />
          </span>
          <span onClick={zoomOut}>
            <FontAwesomeIcon icon={faMinus} className={classes.icon} />
          </span>
          <span onClick={getCurrentLocation}>
            <FontAwesomeIcon
              icon={faLocationCrosshairs}
              className={classes.icon}
            />
          </span>
        </div>
      </div>
    </Fragment>
  );
};

export default Map;
