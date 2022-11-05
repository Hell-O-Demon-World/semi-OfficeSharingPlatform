import { configureStore } from "@reduxjs/toolkit";
import officeLocation from "./officeLocation";
import selectItem from "./selectItem";
import availableItem from "./availableItem";
import availableTime from "./availableTime";
import selectTime from "./selectTime";
import modal from "./modal";
const store = configureStore({
  reducer: {
    time: availableTime,
    item: selectItem,
    availableItem,
    selectTime,
    location: officeLocation,
    modal,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
});

export default store;
