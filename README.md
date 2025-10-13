# Spring + React JWT CRUD (Monorepo)

Projeto real de autenticação **JWT** + **CRUD de Produtos** com **Spring Boot 3** (Java 21) e **React + Vite**.

## Estrutura
```
spring-react-jwt-crud/
  backend/        # API Spring Boot (JWT, JPA, H2/MariaDB)
  frontend/       # App React + Vite (axios, rotas protegidas)
  docker-compose.yml
  README.md
```

## Features
- Login/Registro com JWT (HS256) e senha **BCrypt**
- Rotas protegidas na API e no Front
- CRUD de **Produtos** (listar, criar, editar, excluir)
- H2 em dev; **MariaDB** opcional via Docker
- Build e deploy com **Docker Compose** (API + Front)

## Como rodar (sem Docker)
- **API**: `cd backend && mvn spring-boot:run` → http://localhost:8080
- **Front**: `cd frontend && npm i && npm run dev` → http://localhost:5173 (proxy para `/api`)

## Como rodar com Docker (produção)
> Sobe **API** (porta 8080) e **Front** servido por **Nginx** (porta 8081).

```bash
# na raiz
docker compose up -d --build

# logs
docker compose logs -f api
```

- Acesse **Front**: http://localhost:8081
- Acesse **API**: http://localhost:8080
- Usuário seed: `admin` / `admin123`

### Variáveis úteis
- `JWT_SECRET` (obrigatória em prod) → string >= 32 chars.
- `SPRING_PROFILES_ACTIVE`: `prod` para usar `application-prod.yml`. 
- `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASS` (se habilitar MariaDB).

## Como rodar com DB MariaDB (opcional)
Edite `docker-compose.yml` e descomente o serviço `db` e as variáveis de ambiente do `api`.
Depois:
```bash
docker compose up -d --build
```

## Endpoints principais
- `POST /api/auth/register` → { username, password }
- `POST /api/auth/login` → { username, password } → **token JWT**
- `GET /api/products` (autenticado)
- `POST /api/products` (autenticado)
- `PUT /api/products/{id}` (autenticado)
- `DELETE /api/products/{id}` (autenticado)

## Deploy
- Imagens geradas por `docker compose build`.
- Front servido por **Nginx** (estático) e API em **JRE** leve (multi-stage build).

## Próximos passos
- Refresh token, roles (ADMIN/USER), paginação de produtos, upload de imagem, testes.
