
````md
# Purchase Order Service

Microservicio backend desarrollado con **Spring Boot** para la gestión y consulta de órdenes de compra, desplegado mediante **Docker y Docker Compose**.

---

## 🐳 Docker Hub

**Imagen disponible en Docker Hub:**  
👉 https://hub.docker.com/repository/docker/jsalomia/purchase-order-service/general

---

## ⚙️ 1. Requisitos Previos

El único requisito para ejecutar este proyecto es tener instalado y en ejecución:

- **Docker Desktop** (o Docker CLI)

---

## 🚀 2. Ejecución del Proyecto

El entorno completo (aplicación + base de datos) se levanta con un solo comando usando Docker Compose.

### 2.1 Clonar el Repositorio

Obtenga el código fuente del proyecto:

```bash
git clone https://github.com/3788-jsalomia/alomia_leccion2.git
cd alomia_leccion2
````

### 2.2 Iniciar el Stack de Contenedores

La aplicación (`jsalomia/purchase-order-service`) y la base de datos (`mysql:8.0`) se descargarán automáticamente desde Docker Hub.

Ejecute el siguiente comando en el directorio donde se encuentra el archivo `docker-compose.yml`:

```bash
docker compose up -d
```

> **Nota:**
> La configuración incluye un **healthcheck**, el cual asegura que la aplicación Spring Boot espere a que el servidor MySQL esté completamente operativo antes de iniciar, evitando errores de conexión durante el arranque.

### 2.3 Verificación de Inicio

Para verificar que la aplicación se haya iniciado correctamente, revise los logs del contenedor:

```bash
docker compose logs -f spring-app
```

Busque el mensaje final donde Spring indica que **Tomcat ha iniciado correctamente**.

---

## 🌐 3. Endpoints Disponibles

El microservicio estará disponible en:

```
http://localhost:8001
```

### Obtener órdenes con filtros

```http
GET http://localhost:8001/api/v1/purchase-orders
```

### Filtros soportados

| Parámetro  | Tipo          | Descripción                                  |
| ---------- | ------------- | -------------------------------------------- |
| `q`        | String        | Búsqueda por texto (ej. nombre de proveedor) |
| `status`   | Enum          | Estado de la orden                           |
| `currency` | Enum          | Valores permitidos: **USD, EUR**             |
| `minTotal` | BigDecimal    | Monto total mínimo                           |
| `maxTotal` | BigDecimal    | Monto total máximo                           |
| `from`     | LocalDateTime | Fecha de inicio del rango                    |
| `to`       | LocalDateTime | Fecha de fin del rango                       |

### Ejemplo de uso

```http
GET http://localhost:8001/api/v1/purchase-orders?q=acme&currency=USD&minTotal=100&from=2025-01-01T00:00:00&to=2025-06-30T23:59:59
```

---

## 🛡️ 4. Validaciones y Manejo de Errores

### Validaciones Implementadas

* `currency`: solo acepta los valores permitidos (`USD`, `EUR`).
* `minTotal` y `maxTotal` deben ser mayores o iguales a cero.
* `from` debe ser menor o igual a `to` (`from <= to`).

### Estructura de Error

La API retorna errores con una estructura JSON clara y descriptiva:

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "from must be before or equal to to"
}
```

---

## 📚 5. Tecnologías Utilizadas

| Componente    | Versión / Tipo              |
| ------------- | --------------------------- |
| Lenguaje      | Java 17                     |
| Framework     | Spring Boot                 |
| Persistencia  | Spring Data JPA / Hibernate |
| Base de Datos | MySQL 8                     |
| Orquestación  | Docker Compose              |

### Enlaces de Interés

* **Docker Hub:** [https://hub.docker.com/repository/docker/jsalomia/purchase-order-service/general](https://hub.docker.com/repository/docker/jsalomia/purchase-order-service/general)

---

## ❌ 6. Detener y Limpiar el Entorno

Para detener y eliminar los contenedores:

```bash
docker compose down
```

Para eliminar también el volumen de datos persistentes de MySQL:

```bash
docker compose down -v
```

---

## 👨‍💻 Autor

Proyecto académico desarrollado para prácticas de **arquitectura backend** utilizando **Spring Boot y Docker**.

```

Si quieres, también puedo:
- Ajustarlo a **estándar profesional (empresa)**  
- Simplificar el README para **entrega académica**  
- Traducirlo al **inglés**
```
