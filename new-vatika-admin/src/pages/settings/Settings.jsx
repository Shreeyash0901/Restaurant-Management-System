import { useState, useEffect, useCallback } from "react";
import {
  getSettings,
  updateSettings,
  changePassword,
} from "../../services/settingsService";

export default function Settings() {
  const [settings, setSettings] = useState({
    restaurantName: "",
    ownerName: "",
    phone: "",
    email: "",
    gst: "",
    address: "",
    openingTime: "",
    closingTime: "",

    cashEnabled: true,
    upiEnabled: true,
    cardEnabled: false,

    autoAcceptOrders: false,
    allowCustomerNotes: true,
    pickupEnabled: true,
    deliveryEnabled: true,
  });

  const [password, setPassword] = useState({
    currentPassword: "",
    newPassword: "",
    confirmPassword: "",
  });

  const loadSettings = useCallback(async () => {
    try {
      const data = await getSettings();
      setSettings(data);
    } catch (err) {
      console.error(err);
      alert("Failed to load settings");
    }
  }, []);

  useEffect(() => {
    loadSettings();
  }, [loadSettings]);

  function handleChange(e) {
    const { name, value, type, checked } = e.target;

    setSettings((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  }

  function handlePasswordChange(e) {
    const { name, value } = e.target;

    setPassword((prev) => ({
      ...prev,
      [name]: value,
    }));
  }

  async function saveSettings() {
    try {
      await updateSettings(settings);
      alert("Settings Saved Successfully");
    } catch (err) {
      console.error(err);
      alert("Failed to save settings");
    }
  }

  async function updateUserPassword() {
    try {
      const message = await changePassword(password);

      alert(message);

      setPassword({
        currentPassword: "",
        newPassword: "",
        confirmPassword: "",
      });
    } catch (err) {
      console.error(err);
      alert(err.response?.data || "Password Change Failed");
    }
  }

  return (
    <div className="space-y-8">

      <h1 className="text-3xl font-bold">
        Settings
      </h1>

      {/* Restaurant Information */}

      <div className="bg-white rounded-xl shadow p-6">

        <h2 className="text-xl font-semibold mb-6">
          Restaurant Information
        </h2>

        <div className="grid grid-cols-2 gap-5">

          <input
            className="border rounded p-3"
            placeholder="Restaurant Name"
            name="restaurantName"
            value={settings.restaurantName}
            onChange={handleChange}
          />

          <input
            className="border rounded p-3"
            placeholder="Owner Name"
            name="ownerName"
            value={settings.ownerName}
            onChange={handleChange}
          />

          <input
            className="border rounded p-3"
            placeholder="Phone"
            name="phone"
            value={settings.phone}
            onChange={handleChange}
          />

          <input
            className="border rounded p-3"
            placeholder="Email"
            name="email"
            value={settings.email}
            onChange={handleChange}
          />

          <input
            className="border rounded p-3"
            placeholder="GST Number"
            name="gst"
            value={settings.gst}
            onChange={handleChange}
          />

          <input
            className="border rounded p-3"
            placeholder="Address"
            name="address"
            value={settings.address}
            onChange={handleChange}
          />

          <input
            type="time"
            className="border rounded p-3"
            name="openingTime"
            value={settings.openingTime}
            onChange={handleChange}
          />

          <input
            type="time"
            className="border rounded p-3"
            name="closingTime"
            value={settings.closingTime}
            onChange={handleChange}
          />

        </div>

      </div>

      {/* Payment Methods */}

      <div className="bg-white rounded-xl shadow p-6">

        <h2 className="text-xl font-semibold mb-4">
          Payment Methods
        </h2>

        <div className="space-y-3">

          <label className="flex items-center gap-3">
            <input
              type="checkbox"
              name="cashEnabled"
              checked={settings.cashEnabled}
              onChange={handleChange}
            />
            Cash
          </label>

          <label className="flex items-center gap-3">
            <input
              type="checkbox"
              name="upiEnabled"
              checked={settings.upiEnabled}
              onChange={handleChange}
            />
            UPI
          </label>

          <label className="flex items-center gap-3">
            <input
              type="checkbox"
              name="cardEnabled"
              checked={settings.cardEnabled}
              onChange={handleChange}
            />
            Card
          </label>

        </div>

      </div>

      {/* Order Preferences */}

      <div className="bg-white rounded-xl shadow p-6">

        <h2 className="text-xl font-semibold mb-4">
          Order Preferences
        </h2>

        <div className="space-y-3">

          <label className="flex items-center gap-3">
            <input
              type="checkbox"
              name="autoAcceptOrders"
              checked={settings.autoAcceptOrders}
              onChange={handleChange}
            />
            Auto Accept Orders
          </label>

          <label className="flex items-center gap-3">
            <input
              type="checkbox"
              name="allowCustomerNotes"
              checked={settings.allowCustomerNotes}
              onChange={handleChange}
            />
            Allow Customer Notes
          </label>

          <label className="flex items-center gap-3">
            <input
              type="checkbox"
              name="pickupEnabled"
              checked={settings.pickupEnabled}
              onChange={handleChange}
            />
            Pickup Enabled
          </label>

          <label className="flex items-center gap-3">
            <input
              type="checkbox"
              name="deliveryEnabled"
              checked={settings.deliveryEnabled}
              onChange={handleChange}
            />
            Delivery Enabled
          </label>

        </div>

      </div>

      {/* Change Password */}

      <div className="bg-white rounded-xl shadow p-6">

        <h2 className="text-xl font-semibold mb-5">
          Change Password
        </h2>

        <div className="grid grid-cols-3 gap-4">

          <input
            type="password"
            placeholder="Current Password"
            name="currentPassword"
            value={password.currentPassword}
            onChange={handlePasswordChange}
            className="border rounded p-3"
          />

          <input
            type="password"
            placeholder="New Password"
            name="newPassword"
            value={password.newPassword}
            onChange={handlePasswordChange}
            className="border rounded p-3"
          />

          <input
            type="password"
            placeholder="Confirm Password"
            name="confirmPassword"
            value={password.confirmPassword}
            onChange={handlePasswordChange}
            className="border rounded p-3"
          />

        </div>

        <button
          onClick={updateUserPassword}
          className="mt-6 bg-blue-600 hover:bg-blue-700 text-white px-6 py-3 rounded-lg"
        >
          Update Password
        </button>

      </div>

      <div>
        <button
          onClick={saveSettings}
          className="bg-green-600 hover:bg-green-700 text-white px-8 py-3 rounded-lg"
        >
          Save Settings
        </button>
      </div>

    </div>
  );
}