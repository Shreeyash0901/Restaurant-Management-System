import api from "../api/api";

export async function getDashboardReport() {
  const res = await api.get("/reports/dashboard");
  return res.data;
}