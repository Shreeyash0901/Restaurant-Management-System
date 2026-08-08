import {
  ShoppingCart,
  Clock3,
  CheckCircle,
  IndianRupee,
  UtensilsCrossed,
  LayoutGrid,
} from "lucide-react";

import DashboardCard from "./DashboardCard";

export default function StatsGrid({ stats }) {
  return (
    <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-6">

      <DashboardCard
        title="Total Orders"
        value={stats.totalOrders}
        icon={<ShoppingCart size={28} />}
        color="text-blue-600"
      />

      <DashboardCard
        title="Pending Orders"
        value={stats.pendingOrders}
        icon={<Clock3 size={28} />}
        color="text-yellow-500"
      />

      <DashboardCard
        title="Completed Orders"
        value={stats.completedOrders}
        icon={<CheckCircle size={28} />}
        color="text-green-600"
      />

      <DashboardCard
        title="Revenue"
        value={`₹${stats.totalRevenue}`}
        icon={<IndianRupee size={28} />}
        color="text-purple-600"
      />

      <DashboardCard
        title="Menu Items"
        value={stats.totalMenuItems}
        icon={<UtensilsCrossed size={28} />}
        color="text-orange-600"
      />

      <DashboardCard
        title="Categories"
        value={stats.totalCategories}
        icon={<LayoutGrid size={28} />}
        color="text-pink-600"
      />

    </div>
  );
}
