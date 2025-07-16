import { useState } from "react";
import LoginForm from "./components/LoginForm";
import GirisSecimi from "./components/GirisSecimi";

function App() {
  const [rol, setRol] = useState(null); // Henüz giriş tipi seçilmedi

  return (
    <>
      {rol ? (
        <LoginForm rol={rol} />
      ) : (
        <GirisSecimi onSelect={(secilenRol) => setRol(secilenRol)} />
      )}
    </>
  );
}

export default App;
