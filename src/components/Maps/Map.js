import {
  faLocationCrosshairs,
  faMinus,
  faPlus,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { Fragment, useEffect, useState } from "react";
import { useSelector } from "react-redux";

import classes from "./Map.module.css";
const { kakao } = window;

const Map = () => {
  const officeList = useSelector((state) => state.officeList);
  officeList.map((elem) => {
    const iwContent = `<div class = "wrap">
    <div class = "customOverlay">${elem.name}</div>
    <div class = "arrow"></div>
  </div>`;
    var geocoder = new kakao.maps.services.Geocoder();
    geocoder.addressSearch(elem.address, (result, status) => {
      let coords;
      if (status === kakao.maps.services.Status.OK) {
        coords = new kakao.maps.LatLng(result[0].y, result[0].x);
      }
      return new kakao.maps.CustomOverlay({
        content: iwContent,
        map: map,
        position: coords,
      });
    });
  });
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
    const container = document.getElementById("map");
    const mainLocation = new kakao.maps.LatLng(
      37.523383097083546,
      127.05459973415205
    );
    const options = {
      center: mainLocation,
      level: 9,
    };
    const map = new kakao.maps.Map(container, options);
    setMap(map);
    const iwContent = `<div class = "wrap">
                          <div class = "customOverlay">Golfzon</div>
                          <div class = "arrow"></div>
                        </div>`;
    new kakao.maps.CustomOverlay({
      content: iwContent,
      map: map,
      position: mainLocation,
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
