Link docker hub: https://hub.docker.com/repository/docker/jsalomia/purchase-order-service/general

## Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/tu-repositorio.git
cd tu-repositorio
```

---

## Configuración del proyecto

### Variables de entorno (opcional)

Si usas base de datos externa o Docker, puedes definir variables de entorno:

```bash
DB_HOST=localhost
DB_PORT=3306
DB_NAME=purchase_orders
DB_USER=root
DB_PASSWORD=secret
```

También puedes configurarlas directamente en `application.yml` o `application.properties`.

---

## Ejecutar la aplicación SIN Docker (modo local)

### Compilar el proyecto

```bash
mvn clean package
```

### Ejecutar la aplicación

```bash
mvn spring-boot:run
```

O usando el JAR generado:

```bash
java -jar target/purchase-orders-api.jar
```

### URL base

```text
http://localhost:8080/api/v1/purchase-orders
```

---

## Ejecutar la aplicación CON Docker

### Construir la imagen Docker

```bash
docker build -t tu-usuario/purchase-orders-api:1.0.0 .
```

### Ejecutar el contenedor

```bash
docker run -p 8080:8080 \
  -e DB_HOST=host.docker.internal \
  -e DB_PORT=3306 \
  -e DB_NAME=purchase_orders \
  -e DB_USER=root \
  -e DB_PASSWORD=secret \
  tu-usuario/purchase-orders-api:1.0.0
```

---

## Ejecutar con Docker Compose (recomendado)

### docker-compose.yml (ejemplo)

```yaml
version: "3.9"

services:
  mysql:
    image: mysql:8
    container_name: mysql-po
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: secret
      MYSQL_DATABASE: purchase_orders
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

  api:
    image: tu-usuario/purchase-orders-api:1.0.0
    container_name: purchase-orders-api
    ports:
      - "8080:8080"
    depends_on:
      - mysql
    environment:
      DB_HOST: mysql
      DB_PORT: 3306
      DB_NAME: purchase_orders
      DB_USER: root
      DB_PASSWORD: secret

volumes:
  mysql_data:
```

### Levantar los servicios

```bash
docker-compose up -d
```

---

## Usar la imagen desde Docker Hub

### Descargar la imagen

```bash
docker pull tu-usuario/purchase-orders-api:1.0.0
```

### Ejecutar la imagen

```bash
docker run -p 8080:8080 tu-usuario/purchase-orders-api:1.0.0
```

---

## Endpoints disponibles

### Obtener órdenes con filtros

```http
GET /api/v1/purchase-orders
```

### Filtros soportados

| Parámetro | Tipo          | Descripción        |
| --------- | ------------- | ------------------ |
| q         | String        | Búsqueda por texto |
| status    | Enum          | Estado de la orden |
| currency  | Enum          | USD, EUR           |
| minTotal  | BigDecimal    | Monto mínimo       |
| maxTotal  | BigDecimal    | Monto máximo       |
| from      | LocalDateTime | Fecha inicio       |
| to        | LocalDateTime | Fecha fin          |

### Ejemplo de uso

```http
GET /api/v1/purchase-orders?q=acme&currency=USD&minTotal=100&from=2025-01-01T00:00:00&to=2025-06-30T23:59:59
```

---

## Validaciones implementadas

* currency: solo valores permitidos (USD, EUR) → 400 si es inválido
* minTotal >= 0
* maxTotal >= 0
* from <= to → 400 Bad Request

---

## Manejo de errores

La API retorna errores con estructura clara:

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "from must be before or equal to to"
}
```

---

## Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Docker
* Maven

---

## Autor

Proyecto académico desarrollado para prácticas de arquitectura backend con Spring Boot y Docker.

---

Si quieres, en el siguiente mensaje puedo:

* Adaptar el README a **formato académico**
* Ajustarlo a **microservicios**
* Añadir sección de **arquitectura**
* Hacer un README **más corto** para entrega universitaria
