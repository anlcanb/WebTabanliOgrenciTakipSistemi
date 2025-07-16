import { useState } from "react";
import axios from "axios";

const LoginForm = ({ rol }) => {
  const [email, setEmail] = useState("");
  const [sifre, setSifre] = useState("");
  const [mesaj, setMesaj] = useState("");
  const [hata, setHata] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    setHata("");
    setMesaj("");

    try {
      const response = await axios.post("http://localhost:8080/api/login", {
        email,
        sifre,
        rol,
      });

      setMesaj(`🎉 ${rol === "OGRENCI" ? "Öğrenci" : "Akademisyen"} girişi başarılı!`);
      setEmail("");
      setSifre("");

      // Token varsa kaydedilebilir
      // localStorage.setItem("token", response.data.token);

    } catch (err) {
      setHata("❌ Giriş başarısız. Bilgileri kontrol edin.");
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-100 to-blue-300 flex items-center justify-center">
      <div className="bg-white p-8 rounded-xl shadow-xl w-full max-w-md">
        <h2 className="text-3xl font-bold text-center text-blue-800 mb-6">
          {rol === "OGRENCI" ? "👨‍🎓 Öğrenci Girişi" : "👨‍🏫 Akademisyen Girişi"}
        </h2>
        <form onSubmit={handleLogin} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700">Email</label>
            <input
              type="email"
              className="mt-1 w-full px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-400"
              placeholder="ornek@mail.com"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700">Şifre</label>
            <input
              type="password"
              className="mt-1 w-full px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-400"
              placeholder="••••••"
              value={sifre}
              onChange={(e) => setSifre(e.target.value)}
              required
            />
          </div>
          <button
            type="submit"
            className="w-full py-2 px-4 bg-blue-600 hover:bg-blue-700 text-white font-semibold rounded-lg shadow-md transition duration-300"
          >
            Giriş Yap
          </button>
        </form>

        {mesaj && (
          <div className="mt-4 text-green-600 font-semibold text-center">
            {mesaj}
          </div>
        )}
        {hata && (
          <div className="mt-4 text-red-600 font-semibold text-center">
            {hata}
          </div>
        )}
      </div>
    </div>
  );
};

export default LoginForm;
