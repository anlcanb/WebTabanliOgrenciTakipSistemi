import { useState, useEffect } from "react";
import axios from "axios";

const initialForm = { ad: "", soyad: "", email: "" };

const OgrenciForm = ({ selected, onSave }) => {
  const [form, setForm] = useState(initialForm);

  useEffect(() => {
    if (selected) setForm(selected);
    else setForm(initialForm);
  }, [selected]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const isUpdate = !!selected;

    const req = isUpdate
      ? axios.put(`http://localhost:8080/api/ogrenciler/${selected.id}`, form)
      : axios.post("http://localhost:8080/api/ogrenciler", form);

    req.then(() => {
      setForm(initialForm);
      onSave();
    });
  };

  return (
    <form onSubmit={handleSubmit} className="mb-4 flex flex-wrap gap-2">
      <input
        name="ad"
        value={form.ad}
        onChange={handleChange}
        placeholder="Ad"
        className="border p-2 rounded bg-gray-800 text-white"
        required
      />
      <input
        name="soyad"
        value={form.soyad}
        onChange={handleChange}
        placeholder="Soyad"
        className="border p-2 rounded bg-gray-800 text-white"
        required
      />
      <input
        name="email"
        value={form.email}
        onChange={handleChange}
        placeholder="Email"
        className="border p-2 rounded bg-gray-800 text-white"
        required
      />
      <button type="submit" className="bg-blue-600 px-4 py-2 rounded text-white">
        {selected ? "Güncelle" : "Ekle"}
      </button>
    </form>
  );
};

export default OgrenciForm;
