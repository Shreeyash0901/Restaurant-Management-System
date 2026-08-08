import api from "../api/api";

export async function getAllOrders() {
  const response = await api.get("/orders");
  return response.data;
}

export async function updateOrderStatus(id, status) {
  const response = await api.patch(
    `/orders/${id}/status?status=${status}`
  );

  return response.data;
}

export async function deleteOrder(id) {
  await api.delete(`/orders/${id}`);
}