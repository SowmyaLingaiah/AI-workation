# WorkFlex Workation Platform

A full-stack workation management application built with **Spring Boot 4** and **Angular 17**.

---

# Architecture

```
workflex-workation/
├── backend/    # Spring Boot 4 REST API
└── frontend/   # Angular 17 SPA
```

---

# Start

### Backend
Open `backend/` in IntelliJ and run `WorkationApplication.java`

Or via Maven:
```bash
cd backend
mvn spring-boot:run
```

API runs on: `http://localhost:8080`

### Frontend
```bash
cd frontend
npm install
npx ng serve
```

App runs on: `http://localhost:4200`

---

## API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /workflex/workation | Get all workations (sortable) |

**Query Parameters:**
- `sortBy` — column to sort by (workationId, employee, origin, destination, start, end, workingDays, risk)
- `sortDir` — sort direction (asc, desc)

**Example:**
```
GET /wor/workation?sortBy=employee&sortDir=asc
```

---

# Requirement Features

- Workations loaded from CSV on startup
- Stored in H2 in-memory database
- REST API at `/work/workation`
- Sortable by every column
- Date format: dd/MM/yyyy
- Country flags (emoji)
- Risk level icons (NO=green, LOW=yellow, HIGH=red)
- Row hover highlight
- Unit + Integration tests

---

#Tests

Run via IntelliJ Maven panel → Lifecycle → test

---

#Screenshot

[Workation Table](screenshot.png)

---

#Tech Stack

**Backend:** Java 21, Spring Boot 4.1, Spring Data JPA, H2, OpenCSV, Springdoc OpenAPI

**Frontend:** Angular 17, TypeScript, SCSS


**Sowmya Lingaiah** — sowmya.lingaiah  
Senior Full-Stack Software Engineer
