import { createSlice } from "@reduxjs/toolkit";

const inititalTimeState = {
  timelist: [],
};

const availableTimeSlice = createSlice({
  name: "availableTime",
  initialState: inititalTimeState,
  reducers: {
    checkTimeList(state, action) {
      state.timelist = action.payload;
    },
  },
});

export const avaialbleTimeActions = availableTimeSlice.actions;

export default availableTimeSlice.reducer;
