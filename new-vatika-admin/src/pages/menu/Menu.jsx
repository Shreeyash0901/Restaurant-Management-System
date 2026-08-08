import { useEffect, useState } from "react";
import {
  getAllMenuItems,
  getAllCategories,
  deleteMenuItem,
} from "../../services/menuService";

import MenuForm from "./MenuForm";

export default function Menu() {
  const [items, setItems] = useState([]);
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(true);

  const [search, setSearch] = useState("");
  const [categoryFilter, setCategoryFilter] = useState("ALL");

  const [openForm, setOpenForm] = useState(false);
  const [selectedItem, setSelectedItem] = useState(null);

  useEffect(() => {
    loadData();
  }, []);

  async function loadData() {
    try {
      const [menuItems, categoryList] = await Promise.all([
        getAllMenuItems(),
        getAllCategories(),
      ]);

      setItems(Array.isArray(menuItems) ? menuItems : []);
      setCategories(Array.isArray(categoryList) ? categoryList : []);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  }

  function handleAdd() {
    setSelectedItem(null);
    setOpenForm(true);
  }

  function handleEdit(item) {
    setSelectedItem(item);
    setOpenForm(true);
  }

  async function handleDelete(id) {
    const ok = window.confirm(
      "Delete this menu item?"
    );

    if (!ok) return;

    try {
      await deleteMenuItem(id);
      alert("Deleted Successfully");
      loadData();
    } catch (err) {
      console.error(err);
      alert("Delete Failed");
    }
  }

  const filteredItems = items.filter((item) => {
    const matchesSearch =
      item.nameEn?.toLowerCase().includes(search.toLowerCase()) ||
      item.nameHi?.toLowerCase().includes(search.toLowerCase());

    const matchesCategory =
      categoryFilter === "ALL" ||
      item.categoryId === Number(categoryFilter);

    return matchesSearch && matchesCategory;
  });

  if (loading) {
    return (
      <div className="text-center py-20 text-xl font-semibold">
        Loading Menu...
      </div>
    );
  }

  return (
    <>
      <div className="space-y-6">

        <div className="flex justify-between items-center">
          <h1 className="text-3xl font-bold">
            Menu Management
          </h1>

          <button
            onClick={handleAdd}
            className="bg-green-600 hover:bg-green-700 text-white px-5 py-2 rounded-lg"
          >
            + Add Item
          </button>
        </div>

        <div className="bg-white shadow rounded-xl p-5 flex gap-4">

          <input
            type="text"
            placeholder="Search..."
            value={search}
            onChange={(e) =>
              setSearch(e.target.value)
            }
            className="border rounded-lg px-4 py-2 w-80"
          />

          <select
            value={categoryFilter}
            onChange={(e) =>
              setCategoryFilter(e.target.value)
            }
            className="border rounded-lg px-4 py-2"
          >
            <option value="ALL">
              All Categories
            </option>

            {categories.map((category) => (
              <option
                key={category.id}
                value={category.id}
              >
                {category.nameEn}
              </option>
            ))}
          </select>

        </div>

        <div className="bg-white rounded-xl shadow overflow-hidden">

          <table className="w-full">

            <thead className="bg-gray-100">

              <tr>
                <th className="px-5 py-4 text-left">ID</th>
                <th className="px-5 py-4 text-left">Name</th>
                <th className="px-5 py-4 text-left">Category</th>
                <th className="px-5 py-4 text-left">Price</th>
                <th className="px-5 py-4 text-left">Availability</th>
                <th className="px-5 py-4 text-center">Actions</th>
              </tr>

            </thead>

            <tbody>

              {filteredItems.map((item) => (

                <tr
                  key={item.id}
                  className="border-t hover:bg-gray-50"
                >

                  <td className="px-5 py-4">
                    {item.id}
                  </td>

                  <td className="px-5 py-4">

                    <div className="font-semibold">
                      {item.nameEn}
                    </div>

                    <div className="text-gray-500 text-sm">
                      {item.nameHi}
                    </div>

                  </td>

                  <td className="px-5 py-4">
                    {item.categoryName}
                  </td>

                  <td className="px-5 py-4">
                    ₹{item.price}
                  </td>

                  <td className="px-5 py-4">

                    {item.isAvailable ? (
                      <span className="bg-green-100 text-green-700 px-3 py-1 rounded-full">
                        Available
                      </span>
                    ) : (
                      <span className="bg-red-100 text-red-700 px-3 py-1 rounded-full">
                        Out of Stock
                      </span>
                    )}

                  </td>

                  <td className="px-5 py-4 text-center space-x-2">

                    <button
                      onClick={() =>
                        handleEdit(item)
                      }
                      className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded"
                    >
                      Edit
                    </button>

                    <button
                      onClick={() =>
                        handleDelete(item.id)
                      }
                      className="bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded"
                    >
                      Delete
                    </button>

                  </td>

                </tr>

              ))}

            </tbody>

          </table>

        </div>

      </div>

      <MenuForm
        open={openForm}
        onClose={() => setOpenForm(false)}
        categories={categories}
        menuItem={selectedItem}
        onSuccess={loadData}
      />
    </>
  );
}