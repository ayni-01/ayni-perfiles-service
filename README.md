# 👤 Ayni Perfiles Service

Microservicio responsable del currículum digital y la presentación pública de talentos y empresas dentro de la plataforma **Somos Ayni**. Existe como dominio separado porque separa el concepto de "quién es" una persona (su perfil y presentación) del de "quién puede acceder" (su identidad y credenciales), siguiendo el principio de separación de contextos.

## Responsabilidad del Bounded Context

**Maneja:**
- Creación y edición de perfiles de talento (datos personales, carrera, habilidades, CV)
- Creación y validación de perfiles de empresa (razón social, RUC, sector)
- Generación del CV en formato estructurado a partir del perfil del talento

**NO maneja:**
- Autenticación ni gestión de credenciales (eso es responsabilidad de `identidad-service`)
- Publicación ni evaluación de retos (`retos-service`)
- Postulaciones ni evaluaciones (`postulaciones-service`)

---

## Endpoints REST

| Método | Ruta | Descripción | Auth |
|--------|------|-------------|------|
| `POST` | `/api/v1/perfiles/talento` | Crear perfil de talento | JWT (TALENTO) |
| `PUT` | `/api/v1/perfiles/talento/{id}` | Editar perfil de talento | JWT (TALENTO, dueño) |
| `POST` | `/api/v1/perfiles/talento/{id}/cv` | Generar / actualizar contenido del CV | JWT (TALENTO, dueño) |
| `GET` | `/api/v1/perfiles/talento/{id}` | Obtener perfil de talento por ID | JWT |
| `POST` | `/api/v1/perfiles/empresa` | Crear perfil de empresa | JWT (EMPRESA) |
| `PUT` | `/api/v1/perfiles/empresa/{id}/validar` | Validar empresa (solo administradores) | JWT (ADMIN) |
| `GET` | `/api/v1/perfiles/empresa/{id}` | Obtener perfil de empresa por ID | JWT |

---

## Arquitectura (Hexagonal)

```
src/main/java/com/somosayni/perfiles/
├── domain/
│   ├── model/          # Agregados: PerfilTalento, PerfilEmpresa
│   ├── port/
│   │   ├── in/         # Casos de uso (interfaces de entrada)
│   │   └── out/        # Repositorios (interfaces de salida)
│   └── exception/      # Excepciones de dominio
├── application/
│   └── service/        # Implementaciones de los casos de uso
└── infrastructure/
    ├── adapter/
    │   ├── in/rest/    # Controladores REST, DTOs de entrada/salida
    │   └── out/jpa/    # Repositorios JPA, entidades de BD, mappers
    └── config/         # Spring Security, Swagger, beans de configuración
```

- **Domain:** Contiene la lógica de negocio pura, sin dependencias de frameworks. Define qué es un perfil y qué reglas debe cumplir.
- **Application:** Orquesta los casos de uso invocando los puertos de entrada y salida. Aquí vive la lógica de aplicación (transacciones, validaciones de negocio).
- **Infrastructure:** Todo lo que depende de tecnología concreta: controladores HTTP, persistencia JPA, seguridad, configuración de Spring.

---

## Modelos de dominio principales

### PerfilTalento
Representa la identidad pública de un candidato en la plataforma.

| Campo | Tipo | Descripción |
|-------|------|-------------|
| `id` | `UUID` | Identificador único |
| `usuarioId` | `UUID` | Referencia al usuario en `identidad-service` |
| `nombre` | `String` | Nombre del talento |
| `apellido` | `String` | Apellido del talento |
| `carrera` | `String` | Carrera universitaria o técnica |
| `universidad` | `String` | Institución educativa |
| `habilidades` | `List<String>` | Lista de habilidades técnicas y blandas |
| `cv` | `String` | Contenido del CV en formato estructurado |

### PerfilEmpresa
Representa la identidad pública de una empresa empleadora.

| Campo | Tipo | Descripción |
|-------|------|-------------|
| `id` | `UUID` | Identificador único |
| `usuarioId` | `UUID` | Referencia al usuario en `identidad-service` |
| `nombre` | `String` | Razón social de la empresa |
| `ruc` | `String` | RUC (11 dígitos) |
| `sector` | `String` | Sector económico |
| `validada` | `Boolean` | Indica si el ADMIN aprobó la empresa |

---

## Cómo ejecutar

### Local

```bash
# Requisitos: Java 21, Maven 3.9+, PostgreSQL 15+
# La base de datos 'somosayni' debe existir antes de iniciar

mvn clean package -DskipTests
java -jar target/*.jar
```

### Docker

```bash
cp .env.example .env
# Editar .env con tus valores reales
docker-compose up --build
```

---

## Variables de entorno

| Variable | Descripción | Valor por defecto |
|----------|-------------|-------------------|
| `SPRING_DATASOURCE_URL` | URL JDBC de la base de datos | `jdbc:postgresql://localhost:5432/somosayni` |
| `SPRING_DATASOURCE_USERNAME` | Usuario de PostgreSQL | `postgres` |
| `SPRING_DATASOURCE_PASSWORD` | Contraseña de PostgreSQL | *(obligatorio)* |
| `JWT_SECRET` | Clave secreta compartida para validar JWT | *(obligatorio)* |
| `SERVER_PORT` | Puerto en que levanta el servicio | `8082` |

---

## Swagger / OpenAPI

| | Link |
|---|---|
| **Swagger UI (local)** | [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html) |
| **OpenAPI JSON (local)** | [http://localhost:8082/api-docs](http://localhost:8082/api-docs) |
| **swagger.json (repo)** | [ver en GitHub](https://github.com/ayni-01/ayni-perfiles-service/blob/main/swagger.json) |
| **Swagger Editor (online)** | [abrir en Swagger Editor](https://editor.swagger.io/?url=https://raw.githubusercontent.com/ayni-01/ayni-perfiles-service/main/swagger.json) |

> Para probar los endpoints protegidos: copia el JWT del login → clic en **Authorize** → pega `Bearer <tu-token>`.

---

## Dependencias

Este servicio forma parte del ecosistema **Somos Ayni** y comparte las siguientes convenciones:

- **Base de datos compartida:** Usa el esquema PostgreSQL `somosayni`. Cada microservicio opera sobre sus propias tablas dentro de la misma instancia.
- **Autenticación JWT:** Valida tokens firmados por `identidad-service` usando la misma clave `JWT_SECRET`. No genera tokens propios.
- **Comunicación entre servicios:** Los microservicios son independientes entre sí. Se referencian únicamente por ID (p. ej. `usuarioId`), nunca por llamadas directas entre servicios en el flujo normal.
