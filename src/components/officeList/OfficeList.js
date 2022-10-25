import { useEffect, useState } from "react";
import Office from "./Office";

import classes from "./OfficeList.module.css";
import OfficeSearch from "./OfficeSearch";
const OfficeList = () => {
  const office = [];
  const [isLoading, setIsLoading] = useState(false);
  const [officeList, setOfficeList] = useState([]);
  useEffect(() => {
    const sendRequest = async () => {
      setIsLoading(true);
      try {
        const response = await fetch(
          "https://react-http-673e2-default-rtdb.firebaseio.com/office.json"
        );
        if (!response.ok) {
          throw new Error("Someting went wrong");
        }
        const data = await response.json();
        for (const key in data) {
          office.push({
            id: key,
            name: data[key].name,
            address: data[key].address,
            option: data[key].option,
            postcode: data[key].postcode,
          });
        }
        setOfficeList(office);
      } catch (error) {}
      setIsLoading(false);
    };
    sendRequest();
  }, []);
  return (
    <div className={classes.officeList}>
      <OfficeSearch />
      {!isLoading && officeList.map((elem) => <Office item={elem} />)}
      {isLoading && <p>Loading...</p>}
    </div>
  );
};

export default OfficeList;
