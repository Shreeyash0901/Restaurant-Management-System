import { nextStatusMap, actionLabel } from "../../utils/orderColors";

export default function OrderQuickActions({ order, onStatusChange }) {
  const next = nextStatusMap[order.status];
  const label = actionLabel[order.status];
  const isTerminal = order.status === "COMPLETED" || order.status === "CANCELLED";

  return (
    <div className="flex gap-2">
      {next && (
        <button
          onClick={() => onStatusChange(order.id, next)}
          className="flex-1 bg-green-600 hover:bg-green-700 text-white text-sm font-semibold py-2 rounded-lg transition-colors"
        >
          {label}
        </button>
      )}

      {!isTerminal && (
        <button
          onClick={() => onStatusChange(order.id, "CANCELLED")}
          className="px-3 py-2 text-red-600 hover:bg-red-50 text-sm font-semibold rounded-lg transition-colors"
          title="Cancel order"
        >
          ✕
        </button>
      )}
    </div>
  );
}