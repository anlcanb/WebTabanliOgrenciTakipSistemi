import { useEffect, useState } from "react";
import axios from "axios";

const OgrenciListesi = ({ onEdit }) => {
  const [ogrenciler, setOgrenciler] = useState([]);

  const getOgrenciler = () => {
    axios.get("http://localhost:8080/api/ogrenciler")
      .then(res => setOgrenciler(res.data))
      .catch(err => console.error("API hatası:", err));
  };

  useEffect(() => {
    getOgrenciler();
  }, []);

  const handleDelete = (id) => {
    axios.delete(`http://localhost:8080/api/ogrenciler/${id}`)
      .then(() => getOgrenciler());
  };

  return (
    <div>
      <h2 className="text-xl font-semibold mb-2">📋 Öğrenci Listesi</h2>
      <table className="w-full border text-sm">
        <thead>
          <tr className="bg-gray-700">
            <th className="border p-2">ID</th>
            <th className="border p-2">Ad</th>
            <th className="border p-2">Soyad</th>
            <th className="border p-2">Email</th>
            <th className="border p-2">İşlem</th>
          </tr>
        </thead>
        <tbody>
          {ogrenciler.map((o) => (
            <tr key={o.id} className="border-t border-gray-700">
              <td className="p-2">{o.id}</td>
              <td className="p-2">{o.ad}</td>
              <td className="p-2">{o.soyad}</td>
              <td className="p-2">{o.email}</td>
              <td className="p-2 space-x-2">
                <button onClick={() => onEdit(o)} className="text-yellow-400">✏️</button>
                <button onClick={() => handleDelete(o.id)} className="text-red-400">🗑️</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default OgrenciListesi;
