# Restaurant Management System

## Deployment options

### 1. Frontend deployment
- Admin app: build with `npm run build` inside `new-vatika-admin`
- Cafe app: build with `npm run build` inside `new-vatika-cafe`
- Set the environment variable `VITE_API_URL` to your deployed backend URL before building

Example:
```bash
cd new-vatika-admin
VITE_API_URL=https://your-backend-domain.com/api npm run build
```

### 2. Backend deployment
Build the Spring Boot jar:
```bash
cd new-vatika-backend
./mvnw clean package -DskipTests
```

Run with production properties:
```bash
java -jar target/backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

Required environment variables:
- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `JWT_SECRET`

### 3. Suggested hosting
- Frontends: Vercel / Netlify / GitHub Pages
- Backend: Render / Railway / Fly.io / Azure App Service
- Database: PlanetScale / Neon / Azure Database for MySQL
