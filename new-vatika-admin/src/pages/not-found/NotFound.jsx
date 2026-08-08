import { Link } from "react-router-dom";

export default function NotFound() {
  return (
    <div className="h-screen flex flex-col items-center justify-center">

      <h1 className="text-6xl font-bold">
        404
      </h1>

      <p className="mb-6">
        Page Not Found
      </p>

      <Link
        to="/dashboard"
        className="bg-orange-500 text-white px-5 py-2 rounded"
      >
        Go Home
      </Link>

    </div>
  );
}