import { configureStore } from "@reduxjs/toolkit";
import officeLocation from "./officeLocation";
import selectItem from "./selectItem";

const store = configureStore({
  reducer: { item: selectItem, location: officeLocation },
});

export default store;
