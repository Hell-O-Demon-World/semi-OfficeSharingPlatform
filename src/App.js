import MainHeader from "./components/Layout/MainHeader";
import Wrapper from "./components/Layout/Wrapper";
import Map from "./components/Maps/Map";
import OfficeList from "./components/officeList/OfficeList";
import { Provider } from "react-redux";
import store from "./store";
function App() {
  return (
    <Provider store={store}>
      <MainHeader />
      <Wrapper>
        <OfficeList />
        <Map />
      </Wrapper>
    </Provider>
  );
}

export default App;
