import React from "react";
import { useSelector } from "react-redux";

const AddMarker = () => {
  const officeList = useSelector((state) => state.officeList);
  console.log(officeList);
  return <div>1</div>;
};

export default AddMarker;
