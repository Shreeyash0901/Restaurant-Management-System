import { useEffect, useState } from "react";
import {
  createMenuItem,
  updateMenuItem,
} from "../../services/menuService";

const initialState = {
  nameEn: "",
  nameHi: "",
  descriptionEn: "",
  descriptionHi: "",
  price: "",
  categoryId: "",
  imageUrl: "",
  isVeg: true,
  isAvailable: true,
  displayOrder: 0,
};

export default function MenuForm({
  open,
  onClose,
  categories,
  menuItem,
  onSuccess,
}) {
  const [form, setForm] = useState(initialState);
  const [saving, setSaving] = useState(false);

  useEffect(() => {
    if (menuItem) {
      setForm({
        nameEn: menuItem.nameEn || "",
        nameHi: menuItem.nameHi || "",
        descriptionEn: menuItem.descriptionEn || "",
        descriptionHi: menuItem.descriptionHi || "",
        price: menuItem.price || "",
        categoryId: menuItem.categoryId || "",
        imageUrl: menuItem.imageUrl || "",
        isVeg: menuItem.isVeg,
        isAvailable: menuItem.isAvailable,
        displayOrder: menuItem.displayOrder || 0,
      });
    } else {
      setForm(initialState);
    }
  }, [menuItem, open]);

  function handleChange(e) {
    const { name, value, type, checked } = e.target;

    setForm((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  }

  async function handleSubmit(e) {
    e.preventDefault();

    setSaving(true);

    try {
      const payload = {
        ...form,
        price: Number(form.price),
        categoryId: Number(form.categoryId),
        displayOrder: Number(form.displayOrder),
      };

      if (menuItem) {
        await updateMenuItem(menuItem.id, payload);
      } else {
        await createMenuItem(payload);
      }

      alert(
        menuItem
          ? "Menu Item Updated Successfully"
          : "Menu Item Added Successfully"
      );

      onSuccess();
      onClose();
    } catch (err) {
      console.error(err);
      alert("Something went wrong");
    } finally {
      setSaving(false);
    }
  }

  if (!open) return null;

  return (
    <div className="fixed inset-0 bg-black/40 flex justify-center items-center z-50">

      <div className="bg-white rounded-xl shadow-xl w-[700px] p-8">

        <h2 className="text-2xl font-bold mb-6">
          {menuItem ? "Edit Menu Item" : "Add Menu Item"}
        </h2>

        <form
          onSubmit={handleSubmit}
          className="grid grid-cols-2 gap-4"
        >

          <input
            name="nameEn"
            value={form.nameEn}
            onChange={handleChange}
            placeholder="English Name"
            className="border p-3 rounded"
            required
          />

          <input
            name="nameHi"
            value={form.nameHi}
            onChange={handleChange}
            placeholder="Hindi Name"
            className="border p-3 rounded"
            required
          />

          <input
            name="descriptionEn"
            value={form.descriptionEn}
            onChange={handleChange}
            placeholder="English Description"
            className="border p-3 rounded"
          />

          <input
            name="descriptionHi"
            value={form.descriptionHi}
            onChange={handleChange}
            placeholder="Hindi Description"
            className="border p-3 rounded"
          />

          <input
            type="number"
            name="price"
            value={form.price}
            onChange={handleChange}
            placeholder="Price"
            className="border p-3 rounded"
            required
          />

          <select
            name="categoryId"
            value={form.categoryId}
            onChange={handleChange}
            className="border p-3 rounded"
            required
          >
            <option value="">Select Category</option>

            {categories.map((cat) => (
              <option
                key={cat.id}
                value={cat.id}
              >
                {cat.nameEn}
              </option>
            ))}
          </select>

          <input
            type="number"
            name="displayOrder"
            value={form.displayOrder}
            onChange={handleChange}
            placeholder="Display Order"
            className="border p-3 rounded"
          />

          <input
            name="imageUrl"
            value={form.imageUrl}
            onChange={handleChange}
            placeholder="Image URL"
            className="border p-3 rounded"
          />

          <label className="flex items-center gap-2">
            <input
              type="checkbox"
              name="isVeg"
              checked={form.isVeg}
              onChange={handleChange}
            />
            Veg
          </label>

          <label className="flex items-center gap-2">
            <input
              type="checkbox"
              name="isAvailable"
              checked={form.isAvailable}
              onChange={handleChange}
            />
            Available
          </label>

          <div className="col-span-2 flex justify-end gap-4 mt-4">

            <button
              type="button"
              onClick={onClose}
              className="bg-gray-300 px-5 py-2 rounded"
            >
              Cancel
            </button>

            <button
              type="submit"
              disabled={saving}
              className="bg-green-600 text-white px-5 py-2 rounded"
            >
              {saving ? "Saving..." : "Save"}
            </button>

          </div>

        </form>

      </div>

    </div>
  );
}
