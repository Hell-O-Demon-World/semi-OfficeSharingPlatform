import { configureStore } from "@reduxjs/toolkit";
import officeLocation from "./officeLocation";
import selectItem from "./selectItem";
import availableItem from "./availableItem";
import availableTime from "./availableTime";

const store = configureStore({
  reducer: {
    time: availableTime,
    item: selectItem,
    availableItem,
    location: officeLocation,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
});

export default store;
