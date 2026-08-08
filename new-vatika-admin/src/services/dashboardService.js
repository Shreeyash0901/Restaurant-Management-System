import { getDashboardStats } from "../api/dashboardApi";

export async function fetchDashboardStats() {
  const response = await getDashboardStats();
  return response.data;
}