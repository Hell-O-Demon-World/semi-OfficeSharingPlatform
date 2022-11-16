import { createSlice } from "@reduxjs/toolkit";

const initialMyPageState = {
  userName: "",
  myPageReservationList: [],
};

const myPageSlice = createSlice({
  name: "myPage",
  initialState: initialMyPageState,
  reducers: {
    reservationList(state, action) {
      state.myPageReservationList = [...action.payload];
    },
    userName(state, action) {
      state.userName = action.payload;
    },
  },
});

export const myPageActions = myPageSlice.actions;
export default myPageSlice.reducer;
