import { useEffect, useState } from "react";
import { getAllOrders } from "../../services/orderService";
import StatusBadge from "../ui/StatusBadge";

export default function RecentOrders() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    loadOrders();
  }, []);

  async function loadOrders() {
    try {
      const data = await getAllOrders();

      console.log("Data:", data);
      console.log("Is Array:", Array.isArray(data));

      setOrders(Array.isArray(data) ? data.slice(0, 5) : []);
    } catch (err) {
      console.error(err);
      setOrders([]);
    }
  }

  console.log("Orders State:", orders);
  console.log("Orders State Is Array:", Array.isArray(orders));

  return (
    <div className="bg-white rounded-2xl shadow-md p-6 mt-8">
      <h2 className="text-xl font-semibold mb-4">Recent Orders</h2>

      <table className="w-full">
        <thead>
          <tr className="border-b text-left">
            <th className="py-2">#</th>
            <th>Customer</th>
            <th>Total</th>
            <th>Status</th>
          </tr>
        </thead>

        <tbody>
          {orders.map((order) => (
            <tr key={order.id} className="border-b">
              <td className="py-3">{order.id}</td>
              <td>{order.customerName}</td>
              <td>₹{order.total}</td>
              <td>
                <StatusBadge status={order.status} />
              </td>
            </tr>
          ))}

          {orders.length === 0 && (
            <tr>
              <td colSpan={4} className="text-center py-6 text-gray-500">
                No orders yet.
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}