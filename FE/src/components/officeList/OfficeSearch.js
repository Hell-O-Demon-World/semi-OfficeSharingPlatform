import React, { useRef } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import classes from "./OfficeSearch.module.css";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
const OfficeSearch = () => {
  const keywordSubmitHandler = (e) => {
    e.preventDefault();
  };
  const keywordInputRef = useRef();
  return (
    <div className={classes.search}>
      <form onSubmit={keywordSubmitHandler}>
        <FontAwesomeIcon className={classes.icon} icon={faMagnifyingGlass} />
        <input
          type="text"
          name="searchWord"
          placeholder="공유 오피스 지점명 or 지역으로 검색"
          className={classes.searchInput}
          ref={keywordInputRef}
        />
      </form>
    </div>
  );
};

export default OfficeSearch;
