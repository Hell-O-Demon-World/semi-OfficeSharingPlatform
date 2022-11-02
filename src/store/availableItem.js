import { createSlice } from "@reduxjs/toolkit";

const availableItemInitialState = {
  desk: false,
  meetingRoom: [],
  office: [],
};
const availableItemSlice = createSlice({
  name: "availableItem",
  initialState: availableItemInitialState,
  reducers: {
    selectdesk(state, action) {
      state.desk = action.payload;
    },
    selectMeetingRoom(state, action) {
      state.meetingRoom = action.payload;
    },
    selectOffice(state, action) {
      state.office = action.payload;
    },
  },
});
export const availableItemActions = availableItemSlice.actions;
export default availableItemSlice.reducer;
