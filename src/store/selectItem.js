import { createSlice } from "@reduxjs/toolkit";

const selectItemInitialState = {
  isSelected: false,
  itemName: null,
  itemPrice: null,
  showTimeLine: false,
};

const selectItemSlice = createSlice({
  name: "selectItem",
  initialState: selectItemInitialState,
  reducers: {
    select(state, action) {
      state.isSelected = !!action.payload.itemName;
      state.itemName = action.payload.itemName;
      state.itemPrice = action.payload.itemPrice;
    },
    hideTimeLine(state) {
      state.showTimeLine = false;
    },
    showTimeLine(state) {
      state.showTimeLine = true;
    },
  },
});
export const selectItemActions = selectItemSlice.actions;
export default selectItemSlice.reducer;
