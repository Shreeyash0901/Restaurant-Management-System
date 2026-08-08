import { useEffect, useState } from "react";

export function useOrderTimer(createdAt) {
  const [seconds, setSeconds] = useState(0);

  useEffect(() => {
    if (!createdAt) return;

    const tick = () => {
      setSeconds(Math.floor((Date.now() - new Date(createdAt)) / 1000));
    };

    tick();
    const interval = setInterval(tick, 1000);
    return () => clearInterval(interval);
  }, [createdAt]);

  const minutes = Math.floor(seconds / 60);
  const remSeconds = seconds % 60;
  const isLate = minutes >= 30;

  const formatted = `${String(minutes).padStart(2, "0")}:${String(remSeconds).padStart(2, "0")}`;

  return { minutes, seconds: remSeconds, isLate, formatted };
}