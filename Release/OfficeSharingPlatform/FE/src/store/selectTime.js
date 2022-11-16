import { createSlice } from "@reduxjs/toolkit";

const initialSelectTimeState = {
  startTime: null,
  endTime: null,
  selectTimeList: [],
  timeList: [],
  fullTimeList: [],
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
    deleteList(state) {
      state.selectTimeList = [];
    },
    getFullTime(state, action) {
      if (action.payload === []) {
        state.fullTimeList = [];
      }
      state.fullTimeList.push(action.payload);
    },
  },
});

export const selectTimeActions = selectTimeSlice.actions;
export default selectTimeSlice.reducer;
