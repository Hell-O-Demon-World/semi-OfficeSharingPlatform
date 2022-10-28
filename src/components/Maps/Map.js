import {
  faLocationCrosshairs,
  faMinus,
  faPlus,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { Fragment, useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useHistory } from "react-router-dom";

import classes from "./Map.module.css";
const { kakao } = window;

const Map = () => {
  const [map, setMap] = useState();
  const history = useHistory();
  const officeList = useSelector((state) => state.officeList);
  const showDetailHandler = (e) => {
    history.push("/main/detail");
    e.target.style.backgroundColor = "rgb(91, 135, 218)";
    e.target.nextSibling.style.backgroundColor = "rgb(91, 135, 218)";
  };

  const zoomIn = () => {
    map.setLevel(map.getLevel() - 1);
  };
  const zoomOut = () => {
    map.setLevel(map.getLevel() + 1);
  };

  const getCurrentLocation = () => {
    if ("geolocation" in navigator) {
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
  useEffect(() => {
    officeList.map((elem) => {
      let content = document.createElement("div");
      content.classList.add("wrap");
      let customOverlay = document.createElement("div");
      customOverlay.classList.add("customOverlay");
      customOverlay.textContent = elem.name;
      let arrow = document.createElement("div");
      arrow.classList.add("arrow");
      content.appendChild(customOverlay);
      content.appendChild(arrow);
      customOverlay.onclick = showDetailHandler;
      var geocoder = new kakao.maps.services.Geocoder();

      geocoder.addressSearch(elem.address, (result, status) => {
        if (status === kakao.maps.services.Status.OK) {
          const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          new kakao.maps.CustomOverlay({
            content: content,
            map: map,
            position: coords,
          });
        }
      });
      return 0;
    });
  }, [officeList, map]);

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
