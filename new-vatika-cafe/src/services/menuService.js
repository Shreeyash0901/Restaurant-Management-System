import axios from "axios";

const baseURL = import.meta.env.VITE_API_URL || "http://localhost:8080/api";

const api = axios.create({
  baseURL,
});

export async function getMenuItems() {
  const res = await api.get("/menu-items");
  return res.data;
}

export async function getCategories() {
  const res = await api.get("/categories");
  return res.data;
}