import MainHeader from "../components/Layout/MainHeader";
import Wrapper from "../components/Layout/Wrapper";
import Map from "../components/Maps/Map";
import OfficeList from "../components/officeList/OfficeList";
import { Provider } from "react-redux";
import store from "../store";
import { Route } from "react-router-dom";
import OfficeDetail from "../components/officeList/OfficeDetail";
function Main() {
  return (
    <Provider store={store}>
      <MainHeader />
      <Wrapper>
        <Route path="/main" exact>
          <OfficeList />
        </Route>
        <Route path="/main/:officeId">
          <OfficeDetail />
        </Route>
        <Map />
      </Wrapper>
    </Provider>
  );
}

export default Main;
