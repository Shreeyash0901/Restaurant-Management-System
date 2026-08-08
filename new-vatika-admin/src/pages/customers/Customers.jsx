import { useEffect, useMemo, useState } from "react";
import { getCustomers } from "../../services/customerService";

export default function Customers() {

  const [customers, setCustomers] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadCustomers();
  }, []);

  async function loadCustomers() {
    try {
      const data = await getCustomers();
      setCustomers(data);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  }

  const filtered = useMemo(() => {

    return customers.filter(c =>

      c.customerName
        ?.toLowerCase()
        .includes(search.toLowerCase())

      ||

      c.customerPhone
        ?.includes(search)

    );

  }, [customers, search]);

  if (loading) {
    return (
      <div className="text-center py-20 text-xl">
        Loading Customers...
      </div>
    );
  }

  return (

    <div className="space-y-6">

      <div className="flex justify-between items-center">

        <h1 className="text-3xl font-bold">
          Customers
        </h1>

        <div className="text-gray-500">
          Total Customers : {customers.length}
        </div>

      </div>

      <input
        placeholder="Search customer..."
        value={search}
        onChange={(e)=>setSearch(e.target.value)}
        className="border rounded-lg px-4 py-2 w-96"
      />

      <div className="bg-white rounded-xl shadow overflow-hidden">

        <table className="w-full">

          <thead className="bg-gray-100">

            <tr>

              <th className="text-left p-4">
                Name
              </th>

              <th className="text-left p-4">
                Phone
              </th>

              <th className="text-left p-4">
                Orders
              </th>

              <th className="text-left p-4">
                Total Spent
              </th>

              <th className="text-left p-4">
                Last Order
              </th>

            </tr>

          </thead>

          <tbody>

            {filtered.map(customer => (

              <tr
                key={customer.customerPhone}
                className="border-t hover:bg-gray-50"
              >

                <td className="p-4">
                  {customer.customerName}
                </td>

                <td className="p-4">
                  {customer.customerPhone}
                </td>

                <td className="p-4">
                  {customer.totalOrders}
                </td>

                <td className="p-4">
                  ₹{customer.totalSpent}
                </td>

                <td className="p-4">
                  {new Date(customer.lastOrderDate).toLocaleString()}
                </td>

              </tr>

            ))}

          </tbody>

        </table>

      </div>

    </div>

  );

}