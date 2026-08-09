# New Vatika Café

A React 18 + Vite 5 frontend for New Vatika Café, a fine vegetarian restaurant in Hingoli, Maharashtra.

## Stack

- **React 18** with functional components and hooks
- **Vite 5** for dev server and bundling
- **React Router v6** for client-side routing
- **Plain CSS modules** per component (no Tailwind — matches the project's existing colour system)

## Getting Started

```bash
npm install
npm run dev
```

## Project Structure

```
src/
├── components/      # UI building blocks, each with co-located .css
├── data/            # All static content (nav links, craft items, testimonials…)
├── hooks/           # useFadeIn — IntersectionObserver scroll animation
├── layouts/         # MainLayout (Navbar + Outlet + Footer)
├── pages/           # One file per route
├── routes/          # AppRoutes.jsx — central route config
└── styles/          # Global CSS variables, resets, shared utility classes
```

## Customisation

- **Content** — edit `src/data/index.js`; all text, links, and map URL live there.
- **Colours** — CSS variables are defined in `src/styles/styles.css` under `:root`.
- **Fonts** — loaded via Google Fonts in `src/index.css`; swap families there.
- **Pages** — stub pages (`About`, `Menu`, `GalleryPage`, `Contact`) are ready to extend.
