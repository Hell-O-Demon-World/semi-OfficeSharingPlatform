import { Fragment } from "react";
import MainHeader from "./components/Layout/MainHeader";
import Wrapper from "./components/Layout/Wrapper";
import Map from "./components/Maps/Map";
import OfficeList from "./components/officeList/OfficeList";
function App() {
  return (
    <Fragment>
      <MainHeader />
      <Wrapper>
        <OfficeList />
        <Map />
      </Wrapper>
    </Fragment>
  );
}

export default App;
