import './App.css';
import MainRouter from './MainRouter';
import { useEffect, useState } from "react";
import { AuthContext } from "./helpers/AuthContext";
import axios from "axios";

export default function App() {
  const [authState, setAuthState] = useState({
    connected: false,
    username: "",
    role: "",
  });

  useEffect(() => {
    const token = localStorage.getItem("accessToken");
    const Authorization = 'Bearer ' + token;

    const fetchDataAndCache = async () => {
      try {
        const response = await axios.get(process.env.REACT_APP_API_URL + 'colors');
        const data = response.data;

        localStorage.setItem('colors', JSON.stringify(data));
      } catch (error) {
        console.error('Erreur lors de la récupération des données depuis l\'API :', error);
      }
    };

    const checkTokenValidity = () => {
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
              localStorage.removeItem("colors");

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
            localStorage.removeItem("colors");

            setAuthState({
              connected: false,
              username: "",
              role: "",
            });
          });
      }
    };

    const updateAtMidnight = () => {
      const now = new Date();
      const hours = now.getHours();
      const minutes = now.getMinutes();

      if (hours === 0 && minutes === 0) {
        fetchDataAndCache(); // Mettre à jour à minuit
      }
    };

    checkTokenValidity(); // Vérifier le token au chargement de l'application

    const intervalId = setInterval(() => {
      checkTokenValidity(); // Vérifier le token toutes les deux heures
      updateAtMidnight(); // Vérifier si c'est minuit et mettre à jour si nécessaire
    }, 60 * 1000); // Vérifier toutes les minutes

    // Nettoyer l'intervalle lors du démontage du composant
    return () => clearInterval(intervalId);
  }, []);

  return (
    <div className="App">
      <AuthContext.Provider value={{ authState, setAuthState }}>
        <MainRouter />
      </AuthContext.Provider>
    </div>
  );
}
