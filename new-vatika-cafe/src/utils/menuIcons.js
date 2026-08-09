export function getIcon(slug) {
  const icons = {
    noodles: "🍜",
    "chinese-starter": "🥢",
    "chinese-rice": "🍚",
    momos: "🥟",
    soups: "🍲",
    pizza: "🍕",
    sandwich: "🥪",
    burger: "🍔",
    fries: "🍟",
    pasta: "🍝",
    maggi: "🍜",
    "cold-beverages": "🧋",
    mocktails: "🍹",
    shakes: "🥤",
  };

  return icons[slug] || "🍽️";
}