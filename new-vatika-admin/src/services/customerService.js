import api from "../api/api";

export async function getCustomers() {
  const res = await api.get("/customers");
  return res.data;
}