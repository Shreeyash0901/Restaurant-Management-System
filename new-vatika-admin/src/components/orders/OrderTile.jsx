import OrderTimer from "./OrderTimer";
import OrderQuickActions from "./OrderQuickActions";
import { orderColors } from "../../utils/orderColors";

export default function OrderTile({ order, onStatusChange, onViewDetails }) {
  const colors = orderColors[order.status] || orderColors.PENDING;

  return (
    <div
      className={`rounded-xl border-2 ${colors.border} ${colors.bg} p-4 flex flex-col gap-3 shadow-sm hover:shadow-md transition-shadow`}
    >
      {/* Top row: ID + Timer */}
      <div className="flex justify-between items-start">
        <span className={`font-bold text-lg ${colors.text}`}>#{order.id}</span>
        <OrderTimer createdAt={order.createdAt} />
      </div>

      {/* Customer */}
      <div>
        <p className="font-semibold truncate">{order.customerName}</p>
        <p className="text-xs text-gray-500">{order.orderType}</p>
      </div>

      {/* Items + total */}
      <div className="flex justify-between items-center text-sm text-gray-700">
        <span>{order.items?.length || 0} items</span>
        <span className="font-bold">₹{order.total}</span>
      </div>

      {/* Quick actions */}
      <OrderQuickActions order={order} onStatusChange={onStatusChange} />

      <button
        onClick={() => onViewDetails(order)}
        className="text-xs text-gray-500 underline self-center"
      >
        View Details
      </button>
    </div>
  );
}