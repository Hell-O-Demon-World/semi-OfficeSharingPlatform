import { createSlice } from "@reduxjs/toolkit";

const selectItemInitialState = {
  isSelected: false,
  selectItem: null,
};

const selectItemSlice = createSlice({
  name: "selectItem",
  initialState: selectItemInitialState,
  reducers: {
    select(state, action) {
      state.isSelected = true;
      state.selectItem = action.payload;
    },
  },
});
export const selectItemActions = selectItemSlice.actions;
export default selectItemSlice.reducer;
