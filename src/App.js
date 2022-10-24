import { Fragment } from "react";
import MainHeader from "./components/Layout/MainHeader";
import Map from "./components/Maps/Map";
import OfficeList from "./components/officeList/OfficeList";

function App() {
  return (
    <Fragment>
      <MainHeader />
      <Map />
      <OfficeList />
    </Fragment>
  );
}

export default App;
