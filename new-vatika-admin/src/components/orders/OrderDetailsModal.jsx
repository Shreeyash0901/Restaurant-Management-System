import StatusBadge from "../ui/StatusBadge";

export default function OrderDetailsModal({ order, onClose, onDelete }) {
  if (!order) return null;

  return (
    <div className="fixed inset-0 bg-black/40 flex items-center justify-center z-50">
      <div className="bg-white rounded-xl w-[720px] p-8 max-h-[90vh] overflow-y-auto">

        <div className="flex justify-between items-center mb-6">
          <h2 className="text-2xl font-bold">Order #{order.id}</h2>
          <button onClick={onClose} className="text-red-600 font-semibold hover:text-red-800">
            ✕ Close
          </button>
        </div>

        <div className="grid grid-cols-2 gap-5 mb-8">
          <div>
            <p className="text-gray-500 text-sm">Customer</p>
            <p className="font-semibold">{order.customerName}</p>
          </div>
          <div>
            <p className="text-gray-500 text-sm">Phone</p>
            <p className="font-semibold">{order.customerPhone}</p>
          </div>
          <div>
            <p className="text-gray-500 text-sm">Order Type</p>
            <p className="font-semibold">{order.orderType}</p>
          </div>
          <div>
            <p className="text-gray-500 text-sm">Payment</p>
            <p className="font-semibold">{order.paymentMethod}</p>
          </div>
          <div>
            <p className="text-gray-500 text-sm">Status</p>
            <StatusBadge status={order.status} />
          </div>
          <div>
            <p className="text-gray-500 text-sm">Total</p>
            <p className="font-bold text-lg">₹{order.total}</p>
          </div>
        </div>

        {order.orderType === "DELIVERY" && (
          <div className="mb-8">
            <h3 className="text-lg font-semibold mb-2">Delivery Address</h3>
            <div className="bg-gray-100 rounded-lg p-4">
              <p>{order.houseNo}</p>
              <p>{order.street}</p>
              <p>{order.landmark}</p>
              <p>{order.pincode}</p>
            </div>
          </div>
        )}

        <h3 className="text-lg font-semibold mb-3">Ordered Items</h3>
        <table className="w-full border rounded-lg overflow-hidden mb-8">
          <thead className="bg-gray-100">
            <tr>
              <th className="p-3 text-left">Item</th>
              <th className="p-3 text-center">Qty</th>
              <th className="p-3 text-center">Price</th>
            </tr>
          </thead>
          <tbody>
            {order.items.map((item) => (
              <tr key={item.id} className="border-t">
                <td className="p-3">{item.nameEn}</td>
                <td className="text-center">{item.qty}</td>
                <td className="text-center">₹{item.price}</td>
              </tr>
            ))}
          </tbody>
        </table>

        <div className="flex justify-between">
          <button
            onClick={() => onDelete(order.id)}
            className="bg-red-600 hover:bg-red-700 text-white px-5 py-2 rounded-lg"
          >
            Delete Order
          </button>
          <button
            onClick={onClose}
            className="bg-gray-700 hover:bg-gray-800 text-white px-5 py-2 rounded-lg"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  );
}