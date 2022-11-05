import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import Office from "./Office";

import classes from "./OfficeList.module.css";
import OfficeSearch from "./OfficeSearch";
import { officeLocationActions } from "../../store/officeLocation";
const OfficeList = () => {
  const office = [];
  const dispatch = useDispatch();
  const [isLoading, setIsLoading] = useState(false);
  const officeList = useSelector((state) => state.location.officeList);
  useEffect(() => {
    const sendRequest = async () => {
      setIsLoading(true);
      try {
        const response = await fetch("/main/search", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            searchWord: "",
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
            postcode: String(data[key].postcode),
          });
        }
        dispatch(officeLocationActions.addOfficeList(office));
      } catch (error) {}
      setIsLoading(false);
    };
    sendRequest();
  }, []);
  return (
    <div className={classes.officeList}>
      <OfficeSearch />
      {!isLoading &&
        officeList.map((elem) => <Office key={elem.key} item={elem} />)}
      {isLoading && <p>Loading...</p>}
    </div>
  );
};

export default OfficeList;
