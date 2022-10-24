import { faMinus, faPlus } from "@fortawesome/free-solid-svg-icons";
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

  useEffect(() => {
    kakao.maps.load(() => {
      const mainLocation = new kakao.maps.LatLng(
        37.523383097083546,
        127.05459973415205
      );
      const container = document.getElementById("map");
      const options = {
        center: mainLocation,
        level: 3,
      };
      const map = new kakao.maps.Map(container, options);
      setMap(map);
      const iwContent = `<div class = "customOverlay">Golfzon
    </div>`;
      let overlay = new kakao.maps.CustomOverlay({
        content: iwContent,
        map: map,
        position: mainLocation,
      });
      overlay.setMap(map);
    });
  }, []);
  return (
    <Fragment>
      <div id="map" className={classes.map}></div>
      <div className={`${classes.customZoomcontrol} ${classes.radiusBorder}`}>
        <span onClick={zoomIn}>
          <FontAwesomeIcon icon={faPlus} className={classes.icon} />
        </span>
        <span onClick={zoomOut}>
          <FontAwesomeIcon icon={faMinus} className={classes.icon} />
        </span>
      </div>
    </Fragment>
  );
};

export default Map;
