import { createSlice } from "@reduxjs/toolkit";

const selectItemInitialState = {
  isSelected: false,
  itemName: null,
  itemPrice: null,
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
  },
});
export const selectItemActions = selectItemSlice.actions;
export default selectItemSlice.reducer;
