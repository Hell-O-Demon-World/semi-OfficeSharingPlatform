import { createStore } from "redux";

const officeLocation = (state = { officeList: [] }, action) => {
  if (action.type === "add_officeList") {
    return { officeList: state.officeList.concat(...action.list) };
  }
  return state;
};

const store = createStore(officeLocation);

export default store;
