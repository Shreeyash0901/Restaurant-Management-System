import { Bell, Search } from "lucide-react";
import { useAuth } from "../../auth/AuthContext";

export default function Topbar() {
  const { user } = useAuth();

  return (
    <header className="h-16 bg-white border-b flex items-center justify-between px-6">

      <div className="relative">

        <Search
          className="absolute left-3 top-3 text-gray-400"
          size={18}
        />

        <input
          type="text"
          placeholder="Search..."
          className="pl-10 pr-4 py-2 rounded-lg border outline-none"
        />

      </div>

      <div className="flex items-center gap-6">

        <Bell size={22} className="cursor-pointer" />

        <div className="text-right">
          <p className="font-semibold">
            {user?.username || "Admin"}
          </p>

          <p className="text-sm text-gray-500">
            {user?.role || ""}
          </p>
        </div>

      </div>

    </header>
  );
}