import { createSlice } from "@reduxjs/toolkit";

const initialSelectTimeState = {
  startTime: null,
  endTime: null,
  selectTimeList: [],
  timeList: [],
};

const selectTimeSlice = createSlice({
  name: "selectTime",
  initialState: initialSelectTimeState,
  reducers: {
    getTimeList(state, action) {
      state.timeList = action.payload;
    },
    select(state, action) {
      if (state.selectTimeList.length === 2) {
        state.selectTimeList = [];
        state.selectTimeList.push(Number(action.payload));
      } else {
        state.selectTimeList.push(Number(action.payload));
      }
      state.selectTimeList.sort((a, b) => a - b);
    },
  },
});

export const selectTimeActions = selectTimeSlice.actions;
export default selectTimeSlice.reducer;
