# Fashion Gallery API  
 
> REST API for uploading, storing, and managing fashion images using Supabase Storage.  
 
**Live API:** [https://fashion-gallery-api-1.onrender.com](https://fashion-gallery-api-1.onrender.com)  
**Swagger UI:** [https://fashion-gallery-api-1.onrender.com/swagger-ui.html](https://fashion-gallery-api-1.onrender.com/swagger-ui.html)  

## Endpoints
 
| Method | Path | Description |
|--------|------|-------------|
| `GET` | `/api/images` | Get all images sorted by date |
| `GET` | `/api/images/{id}` | Get image by ID |
| `POST` | `/api/images/upload` | Upload one or more images |
| `DELETE` | `/api/images` | Delete images by IDs |

  
### Environment Variables  
```env
DB_URL=jdbc:postgresql://db.YOUR_PROJECT_REF.supabase.co:5432/postgres?sslmode=require
DB_USERNAME=postgres
DB_PASSWORD=your-database-password
SUPABASE_URL=https://YOUR_PROJECT_REF.supabase.co
SUPABASE_KEY=your-service-role-key
SUPABASE_BUCKET=fashion-images
CORS_ALLOWED_ORIGINS=http://localhost:PORT,https://your-frontend-url
```
## Next Steps

- - [x] **CORS** — replace the current `@CrossOrigin("*")` with a proper configuration restricting allowed origins to known domains only 
- **User management** — implement authentication and associate images to users for referential integrity, so each user can only access and manage their own uploads
- **Sign in / Sign out** — integrate Supabase Auth or Spring Security with JWT to support user sessions

---

# Fashion Gallery API *(Español)*  
 
> API REST para subir, almacenar y gestionar imágenes de moda usando Supabase Storage.  
 
**API en producción:** [https://fashion-gallery-api-1.onrender.com](https://fashion-gallery-api-1.onrender.com)  
**Swagger UI:** [https://fashion-gallery-api-1.onrender.com/swagger-ui.html](https://fashion-gallery-api-1.onrender.com/swagger-ui.html)  

## Endpoints  
 
| Método | Path | Descripción |
|--------|------|-------------|
| `GET` | `/api/images` | Obtener todas las imágenes ordenadas por fecha |
| `GET` | `/api/images/{id}` | Obtener imagen por ID |
| `POST` | `/api/images/upload` | Subir una o más imágenes |
| `DELETE` | `/api/images` | Eliminar imágenes por IDs |  

## Próximos pasos  

- - [x] **CORS** — reemplazar el `@CrossOrigin("*")` actual por una configuración que restrinja los orígenes permitidos a dominios conocidos
- **Gestión de usuarios** — implementar autenticación y asociar las imágenes a usuarios para integridad referencial, de modo que cada usuario solo pueda acceder y gestionar sus propias imágenes
- **Sign in / Sign out** — integrar Supabase Auth o Spring Security con JWT para manejar sesiones de usuario
