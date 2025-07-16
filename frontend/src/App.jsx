import { useState } from "react";
import OgrenciForm from "./components/OgrenciForm";
import OgrenciListesi from "./components/OgrenciListesi";
import "./index.css";

function App() {
  const [selected, setSelected] = useState(null);
  const [refresh, setRefresh] = useState(false);

  const handleRefresh = () => {
    setSelected(null);
    setRefresh(!refresh);
  };

  return (
    <div className="p-6 max-w-3xl mx-auto text-white">
      <h1 className="text-3xl font-bold mb-6 flex items-center gap-2">
        🎓 Öğrenci Takip Sistemi
      </h1>
      <OgrenciForm selected={selected} onSave={handleRefresh} />
      <hr className="my-6 border-gray-600" />
      <OgrenciListesi onEdit={setSelected} key={refresh} />
    </div>
  );
}

export default App;
