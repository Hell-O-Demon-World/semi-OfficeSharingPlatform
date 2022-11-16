import { createSlice } from "@reduxjs/toolkit";

const officeLocationInitialState = {
  officeList: [],
};

const officeLocationSlice = createSlice({
  name: "officeLocation",
  initialState: officeLocationInitialState,
  reducers: {
    addOfficeList(state, action) {
      state.officeList = [...action.payload];
    },
  },
});

export const officeLocationActions = officeLocationSlice.actions;
export default officeLocationSlice.reducer;
