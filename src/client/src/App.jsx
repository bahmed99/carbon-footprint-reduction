import './App.css';
import MainRouter from './MainRouter';
import { useEffect, useState } from "react";
import { AuthContext } from "./helpers/AuthContext";
import axios from "axios";

function App() {
  const [authState, setAuthState] = useState({
    connected: false,
    username: "",
    role: "",
  });

  useEffect(() => {
    const token = localStorage.getItem("accessToken");
    const Authorization = 'Bearer ' + token;

    
    if (!token) {
      setAuthState({
        connected: false,
        username: "",
        role: "",
      });
    } else {
      axios
        .get(process.env.REACT_APP_API_URL + "auth/checkToken?token=" + token, {
          headers: {
            Authorization: Authorization,
          },
        })
        .then((response) => {
          if (response.data.error) {
            console.log(response.data.error, "error: checkToken failed!");
            localStorage.removeItem("accessToken");
            localStorage.removeItem("role");

            setAuthState({
              connected: false,
              username: "",
              role: "",
            });
          } else {

            setAuthState({
              connected: true,
              username: response.data.username,
              role: response.data.role,
            });
          }
        })
        .catch((error) => {
          console.log(error, "error: checkToken failed!");
          localStorage.removeItem("accessToken");
          localStorage.removeItem("role");

          setAuthState({
            connected: false,
            username: "",
            role: "",
          });
        });
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
