import { useOrderTimer } from "../../hooks/useOrderTimer";

export default function OrderTimer({ createdAt }) {
  const { formatted, isLate } = useOrderTimer(createdAt);

  return (
    <span
      className={`font-mono text-sm font-semibold ${
        isLate ? "text-red-600 animate-pulse" : "text-gray-600"
      }`}
    >
      {formatted}
    </span>
  );
}