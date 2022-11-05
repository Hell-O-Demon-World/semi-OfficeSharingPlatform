import React, { useRef } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import classes from "./OfficeSearch.module.css";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import { useDispatch } from "react-redux";
import { officeLocationActions } from "../../store/officeLocation";
const OfficeSearch = () => {
  const office = [];
  const dispatch = useDispatch();
  const keywordInputRef = useRef();
  const keywordSubmitHandler = (e) => {
    e.preventDefault();
    const sendRequest = async () => {
      try {
        const response = await fetch("/main/search", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            searchWord: keywordInputRef.current.value,
          }),
        });
        if (!response.ok) {
          throw new Error("Someting went wrong");
        }
        const data = await response.json();
        for (const key in data) {
          office.push({
            key: data[key].key,
            name: data[key].name,
            address: data[key].address,
            option: data[key].option,
            postcode: Number(data[key].postcode),
          });
        }

        dispatch(officeLocationActions.addOfficeList(office));
      } catch (error) {}
    };
    sendRequest();
  };
  return (
    <div className={classes.search}>
      <form onSubmit={keywordSubmitHandler}>
        <FontAwesomeIcon className={classes.icon} icon={faMagnifyingGlass} />
        <input
          type="text"
          name="searchWord"
          placeholder="공유 오피스 상호명 검색"
          className={classes.searchInput}
          ref={keywordInputRef}
        />
      </form>
    </div>
  );
};

export default OfficeSearch;
