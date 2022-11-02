import { configureStore } from "@reduxjs/toolkit";
import officeLocation from "./officeLocation";
import selectItem from "./selectItem";
import availableItem from "./availableItem";

const store = configureStore({
  reducer: {
    item: selectItem,
    location: officeLocation,
    availableItem,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
});

export default store;
