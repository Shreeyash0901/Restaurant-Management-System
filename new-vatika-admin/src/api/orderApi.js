import api from "./api";

export const getOrders = () => {
  return api.get("/orders");
};