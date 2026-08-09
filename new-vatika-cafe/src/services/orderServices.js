import api from "../api/api";

export const placeOrder = async (order) => {
  const response = await api.post("/orders", order);
  return response.data;
};