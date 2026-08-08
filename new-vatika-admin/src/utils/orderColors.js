export const orderColors = {
  PENDING: {
    bg: "bg-orange-50",
    border: "border-orange-300",
    text: "text-orange-700",
  },
  PREPARING: {
    bg: "bg-yellow-50",
    border: "border-yellow-300",
    text: "text-yellow-700",
  },
  READY: {
    bg: "bg-blue-50",
    border: "border-blue-300",
    text: "text-blue-700",
  },
  COMPLETED: {
    bg: "bg-green-50",
    border: "border-green-300",
    text: "text-green-700",
  },
  CANCELLED: {
    bg: "bg-red-50",
    border: "border-red-300",
    text: "text-red-700",
  },
};

// what the primary quick-action button does next
export const nextStatusMap = {
  PENDING: "PREPARING",
  PREPARING: "READY",
  READY: "COMPLETED",
};

export const actionLabel = {
  PENDING: "Accept Order",
  PREPARING: "Mark Ready",
  READY: "Complete",
};