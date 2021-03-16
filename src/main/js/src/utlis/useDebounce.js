import { useEffect, useState } from "react";

export const useDebounce = (value, delay = 500) => {
  const [debouncedValue, setDebouncedValue] = useState(value);

  useEffect(() => {
    const handleDebounce = setTimeout(() => setDebouncedValue(value), delay);
    return () => clearTimeout(handleDebounce);
  }, [value, delay]);

  return debouncedValue;
};
