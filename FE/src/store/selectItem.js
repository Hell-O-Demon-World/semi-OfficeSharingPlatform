import { createSlice } from "@reduxjs/toolkit";

const selectItemInitialState = {
  isSelected: false,
  itemName: null,
  itemPrice: null,
  itemId: "",
  showTimeLine: false,
  date: new Date().toLocaleDateString(),
};

const selectItemSlice = createSlice({
  name: "selectItem",
  initialState: selectItemInitialState,
  reducers: {
    select(state, action) {
      state.isSelected = !!action.payload.itemName;
      state.itemName = action.payload.itemName;
      state.itemPrice = action.payload.itemPrice;
      state.itemId = action.payload.itemId;
    },
    hideTimeLine(state) {
      state.showTimeLine = false;
    },
    showTimeLine(state) {
      state.showTimeLine = true;
    },
    selectDate(state, action) {
      state.date = action.payload;
    },
  },
});
export const selectItemActions = selectItemSlice.actions;
export default selectItemSlice.reducer;
