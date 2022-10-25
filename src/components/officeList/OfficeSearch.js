import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import classes from "./OfficeSearch.module.css";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
const OfficeSearch = () => {
  return (
    <div className={classes.search}>
      <form>
        <FontAwesomeIcon className={classes.icon} icon={faMagnifyingGlass} />
        <input
          type="text"
          name="searchWord"
          placeholder="공유 오피스 지점명 or 지역으로 검색"
          className={classes.searchInput}
        />
      </form>
    </div>
  );
};

export default OfficeSearch;
