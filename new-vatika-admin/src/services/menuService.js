import api from "../api/api";

export async function getAllMenuItems() {
  const response = await api.get("/menu-items");
  return response.data;
}

export async function createMenuItem(menuItem) {
  const response = await api.post("/menu-items", menuItem);
  return response.data;
}

export async function updateMenuItem(id, menuItem) {
  const response = await api.put(`/menu-items/${id}`, menuItem);
  return response.data;
}

export async function deleteMenuItem(id) {
  await api.delete(`/menu-items/${id}`);
}

export async function getAllCategories() {
  const response = await api.get("/categories");
  return response.data;
}