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
```

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
