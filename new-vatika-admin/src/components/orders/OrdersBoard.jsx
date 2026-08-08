import { useEffect, useState } from "react";
import toast from "react-hot-toast";
import StatusColumn from "./StatusColumn";
import OrderDetailsModal from "./OrderDetailsModal";
import { getAllOrders, updateOrderStatus, deleteOrder } from "../../services/orderService";

const COLUMNS = [
  { key: "PENDING", title: "Pending" },
  { key: "PREPARING", title: "Preparing" },
  { key: "READY", title: "Ready" },
  { key: "COMPLETED", title: "Completed" },
];

export default function OrdersBoard() {
  const [orders, setOrders] = useState([]);
  const [selectedOrder, setSelectedOrder] = useState(null);
  const [loading, setLoading] = useState(true);

  const fetchOrders = async () => {
    try {
      const data = await getAllOrders();
      setOrders(data);
    } catch (err) {
      toast.error("Failed to load orders");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchOrders();
    const interval = setInterval(fetchOrders, 5000);
    return () => clearInterval(interval);
  }, []);

  const handleStatusChange = async (orderId, newStatus) => {
    const prevOrders = orders;

    // optimistic update so the tile jumps columns instantly
    setOrders((prev) =>
      prev.map((o) => (o.id === orderId ? { ...o, status: newStatus } : o))
    );

    try {
      await updateOrderStatus(orderId, newStatus);
      toast.success(`Order #${orderId} → ${newStatus}`);
    } catch (err) {
      toast.error("Failed to update status");
      setOrders(prevOrders); // revert
    }
  };

  const handleDelete = async (orderId) => {
    try {
      await deleteOrder(orderId);
      setOrders((prev) => prev.filter((o) => o.id !== orderId));
      setSelectedOrder(null);
      toast.success("Order deleted");
    } catch (err) {
      toast.error("Failed to delete order");
    }
  };

  if (loading) return <p className="p-6 text-gray-500">Loading orders...</p>;

  return (
    <>
      <div className="flex gap-4 overflow-x-auto pb-4">
        {COLUMNS.map((col) => (
          <StatusColumn
            key={col.key}
            title={col.title}
            orders={orders.filter((o) => o.status === col.key)}
            onStatusChange={handleStatusChange}
            onViewDetails={setSelectedOrder}
          />
        ))}
      </div>

      <OrderDetailsModal
        order={selectedOrder}
        onClose={() => setSelectedOrder(null)}
        onDelete={handleDelete}
      />
    </>
  );
}