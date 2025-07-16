const GirisSecimi = ({ onSelect }) => {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-sky-100 to-sky-300">
      <div className="bg-white p-8 rounded-lg shadow-lg text-center space-y-4">
        <h2 className="text-2xl font-bold text-gray-800">Giriş Tipi Seçin</h2>
        <button
          onClick={() => onSelect("OGRENCI")}
          className="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 rounded-lg transition"
        >
          👨‍🎓 Öğrenci Girişi
        </button>
        <button
          onClick={() => onSelect("OGRETMEN")}
          className="w-full bg-green-600 hover:bg-green-700 text-white py-2 rounded-lg transition"
        >
          👨‍🏫 Akademisyen Girişi
        </button>
      </div>
    </div>
  );
};

export default GirisSecimi;
