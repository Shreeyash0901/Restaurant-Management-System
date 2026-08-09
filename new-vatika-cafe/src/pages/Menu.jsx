import { useEffect, useMemo, useState } from "react";
import { getCategories, getMenuItems } from "../services/menuService";
import { getIcon } from "../utils/menuIcons";

import MenuCategoryCard from "../components/MenuCategoryCard";
import GoldDivider from "../components/GoldDivider";

import styles from "./Menu.module.css";

const menuTabs = [
  {
    key: "chinese",
    label: { en: "Chinese", hi: "चाइनीज़" },
    cats: [
      "noodles",
      "chinese-starter",
      "chinese-rice",
      "momos",
      "soups",
    ],
  },
  {
    key: "fastfood",
    label: { en: "Fast Food", hi: "फास्ट फूड" },
    cats: [
      "pizza",
      "sandwich",
      "burger",
      "fries",
      "pasta",
      "maggi",
    ],
  },
  {
    key: "drinks",
    label: { en: "Drinks", hi: "ड्रिंक्स" },
    cats: [
      "cold-beverages",
      "mocktails",
      "shakes",
    ],
  },
];

export default function Menu() {
  const [categories, setCategories] = useState([]);
  const [items, setItems] = useState([]);

  const [loading, setLoading] = useState(true);

  const [activeTab, setActiveTab] = useState("all");
  const [activeCat, setActiveCat] = useState(null);

  useEffect(() => {
    loadMenu();
  }, []);

  async function loadMenu() {
    try {
      const [cats, menu] = await Promise.all([
        getCategories(),
        getMenuItems(),
      ]);

      setCategories(cats);
      setItems(menu);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  }

  const menuData = useMemo(() => {
    const data = {};

    categories.forEach((cat) => {
      data[cat.slug] = {
        icon: getIcon(cat.slug),

        label: {
          en: cat.nameEn,
          hi: cat.nameHi,
        },

        items: items
          .filter((item) => item.categoryId === cat.id)
          .map((item) => ({
            name: {
              en: item.nameEn,
              hi: item.nameHi,
            },

            price: `₹${item.price}`,
          })),
      };
    });

    return data;
  }, [categories, items]);

  const categoryKeys = Object.keys(menuData);

  const tabCategories =
    activeTab === "all"
      ? categoryKeys
      : menuTabs.find((t) => t.key === activeTab)?.cats || [];

  const visibleCategories = activeCat
    ? [activeCat]
    : tabCategories;

  if (loading) {
    return (
      <div
        style={{
          padding: 80,
          textAlign: "center",
          fontSize: 22,
        }}
      >
        Loading Menu...
      </div>
    );
  }

  return (
    <section className={styles.menuSection}>
      <div className={styles.heading}>
        <p className={styles.eyebrow}>
          Our Culinary Collection
        </p>

        <h2 className={styles.title}>
          The Menu
        </h2>

        <GoldDivider />
      </div>

      <div className={styles.tabBar}>
        <button
          className={`${styles.tabButton} ${
            activeTab === "all" && !activeCat
              ? styles.active
              : ""
          }`}
          onClick={() => {
            setActiveTab("all");
            setActiveCat(null);
          }}
        >
          ALL ITEMS

          <span className={styles.hi}>
            सभी आइटम
          </span>
        </button>

        {menuTabs.map((tab) => (
          <button
            key={tab.key}
            className={`${styles.tabButton} ${
              activeTab === tab.key &&
              !activeCat
                ? styles.active
                : ""
            }`}
            onClick={() => {
              setActiveTab(tab.key);
              setActiveCat(null);
            }}
          >
            {tab.label.en.toUpperCase()}

            <span className={styles.hi}>
              {tab.label.hi}
            </span>
          </button>
        ))}
      </div>

      <div className={styles.pillRow}>
        {tabCategories.map((slug) => {
          const cat = menuData[slug];

          if (!cat) return null;

          return (
            <button
              key={slug}
              className={`${styles.pill} ${
                activeCat === slug
                  ? styles.active
                  : ""
              }`}
              onClick={() =>
                setActiveCat(
                  activeCat === slug
                    ? null
                    : slug
                )
              }
            >
              <span className={styles.pillIconWrap}>
                {cat.icon}
              </span>

              <span>
                {cat.label.en}
              </span>
            </button>
          );
        })}
      </div>

      <div className={styles.menuGrid}>
        {visibleCategories.map((slug) => {
          const cat = menuData[slug];

          if (!cat) return null;

          return (
            <MenuCategoryCard
              key={slug}
              category={cat}
            />
          );
        })}
      </div>
    </section>
  );
}