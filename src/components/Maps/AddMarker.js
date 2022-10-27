import { useHistory } from "react-router-dom";

const AddMarker = (props) => {
  const history = useHistory();
  const showDetail = () => {
    history.push("/main/detail");
  };
};

export default AddMarker;
