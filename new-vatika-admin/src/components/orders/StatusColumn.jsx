import OrderTile from "./OrderTile";

export default function StatusColumn({ title, orders, onStatusChange, onViewDetails }) {
  return (
    <div className="flex-1 min-w-[280px] bg-gray-50 rounded-xl p-3">
      <div className="flex justify-between items-center mb-3 px-1">
        <h3 className="font-semibold text-gray-700">{title}</h3>
        <span className="bg-gray-200 text-gray-700 text-xs font-bold px-2 py-1 rounded-full">
          {orders.length}
        </span>
      </div>

      <div className="flex flex-col gap-3 max-h-[75vh] overflow-y-auto pr-1">
        {orders.length === 0 && (
          <p className="text-sm text-gray-400 text-center py-6">No orders</p>
        )}

        {orders.map((order) => (
          <OrderTile
            key={order.id}
            order={order}
            onStatusChange={onStatusChange}
            onViewDetails={onViewDetails}
          />
        ))}
      </div>
    </div>
  );
}