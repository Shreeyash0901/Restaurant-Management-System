import { useEffect, useState } from "react";
import RecentOrders from "../../components/dashboard/RecentOrders";
import StatsGrid from "../../components/dashboard/StatsGrid";

import { fetchDashboardStats } from "../../services/dashboardService";

export default function Dashboard() {
  const [stats, setStats] = useState(null);

  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function loadDashboard() {
      try {
        const data = await fetchDashboardStats();
        setStats(data);
      } catch (err) {
        console.error(err);
      } finally {
        setLoading(false);
      }
    }

    loadDashboard();
  }, []);

  if (loading) {
    return <h2>Loading Dashboard...</h2>;
  }

  return (
  <div>
    <h1 className="text-3xl font-bold mb-8">
      Dashboard
    </h1>

    <StatsGrid stats={stats} />

    <RecentOrders />
  </div>
);
}