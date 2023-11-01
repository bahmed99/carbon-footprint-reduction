import './App.css';
import MainRouter from './MainRouter';
import { useEffect, useState } from "react";
import { AuthContext } from "./helpers/AuthContext";


function App() {
  const [ authState, setAuthState ] = useState({
    connected: false,
  });

  useEffect(() => {
    if (localStorage.getItem("accessToken")==null) {
          setAuthState({
            connected: false,
          });
        } else {
          setAuthState ({
            connected: true,
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
