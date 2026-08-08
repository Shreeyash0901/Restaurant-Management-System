import PageHeader from "../../components/common/PageHeader";
import OrdersBoard from "../../components/orders/OrdersBoard";

export default function Orders() {
  return (
    <div className="p-6">
      <PageHeader title="Orders" subtitle="Live kitchen order board" />
      <OrdersBoard />
    </div>
  );
}