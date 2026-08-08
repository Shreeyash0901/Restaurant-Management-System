import {
  LayoutDashboard,
  ClipboardList,
  UtensilsCrossed,
  Users,
  BarChart3,
  Settings,
  LogOut,
} from "lucide-react";

import { NavLink } from "react-router-dom";
import { useAuth } from "../../auth/AuthContext";

const menuItems = [
  {
    title: "Dashboard",
    icon: LayoutDashboard,
    path: "/dashboard",
  },
  {
    title: "Orders",
    icon: ClipboardList,
    path: "/orders",
  },
  {
    title: "Menu",
    icon: UtensilsCrossed,
    path: "/menu",
  },
  {
    title: "Customers",
    icon: Users,
    path: "/customers",
  },
  {
    title: "Reports",
    icon: BarChart3,
    path: "/reports",
  },
  {
    title: "Settings",
    icon: Settings,
    path: "/settings",
  },
];

export default function Sidebar() {
  const { logout } = useAuth();

  return (
    <aside className="w-64 bg-zinc-900 text-white flex flex-col">

      <div className="p-6 text-2xl font-bold border-b border-zinc-700">
        Restaurant Admin
      </div>

      <nav className="flex-1 py-4">

        {menuItems.map((item) => {
          const Icon = item.icon;

          return (
            <NavLink
              key={item.path}
              to={item.path}
              className={({ isActive }) =>
                `flex items-center gap-3 px-6 py-3 transition ${
                  isActive
                    ? "bg-orange-500"
                    : "hover:bg-zinc-800"
                }`
              }
            >
              <Icon size={20} />
              {item.title}
            </NavLink>
          );
        })}

      </nav>

      <button
        onClick={logout}
        className="flex items-center gap-3 px-6 py-4 hover:bg-red-500 transition"
      >
        <LogOut size={20} />
        Logout
      </button>

    </aside>
  );
}