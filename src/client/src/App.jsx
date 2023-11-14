import './App.css';
import MainRouter from './MainRouter';
import { useEffect, useState } from "react";
import { AuthContext } from "./helpers/AuthContext";
import axios from "axios";


function App() {
  const [ authState, setAuthState ] = useState({
    connected: false,
  });
  const token = localStorage.getItem("accessToken");
  const Authorization = 'Bearer' + token;

  useEffect(() => {
    if (token==null) {
          setAuthState({
            connected: false,
          });
        } else {
          axios
        .get(process.env.REACT_APP_API_URL + "auth/checkToken?token="+token, {
          headers: {
            Authorization: Authorization,
          },
        })
        .then((response) => {
          if (response.data.error) {
            console.log(response.data.error, "error : checkToken failed !")
            localStorage.removeItem("accessToken");
            setAuthState({
              connected: false,
            });
          } else {
            setAuthState({
              connected: true,
            });
          }
        })
        .catch((error) => {
          console.log(error, "error : checkToken failed !")
          localStorage.removeItem("accessToken");
          setAuthState({
            connected: false,
          });
        })
        }
  }, []);


  return (
    <div className="App">
      
      <AuthContext.Provider value={{ authState, setAuthState }}>
        
      <MainRouter />
      </AuthContext.Provider>
    </div>
  );
}

export default App;
