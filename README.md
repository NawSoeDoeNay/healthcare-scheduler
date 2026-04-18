# healthcare-scheduler
Healthcare Appointment Scheduler: A production-ready CRUD API utilizing Spring Boot, and JPA. Implements complex doctor-patient scheduling logic.
# Healthcare Appointment Scheduler

A backend system for managing patient appointments with doctors across different time zones.  
Built with **Java Spring Boot** for the backend and optionally **Angular** for frontend UI.

---

## 🏷️ Project Overview

- Users can book, view, and manage appointments.
- Handles overlapping bookings and validates appointment times.
- Uses **Clean Architecture**: separates Domain, Service, Repository, and Controller layers.

---

## 💻 Tech Stack

- **Backend**: Java 21, Spring Boot, Spring Data JPA
- **Database**: PostgreSQL
- **Frontend (optional)**: React
- **Testing**: JUnit, Mockito
- **Build Tool**: Gradle

---

## ⚙️ Features

### Patients
- Book new appointments
- View upcoming appointments
- Update or cancel existing appointments

### Doctors
- View all appointments
- Confirm or cancel appointments
- Filter by date, patient, or status

### Appointments
- Prevent overlapping appointments
- Time-zone aware scheduling
- Status: Scheduled, Confirmed, Cancelled

---

## 🔗 API Endpoints (Sample)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /availability | Search available slots for a doctor |
| POST | /appointments | Book a new appointment |
| PATCH | /appointments/{id}/status | Update appointment status (Confirmed / Cancelled) |
| GET | /appointments | View all appointments |

---

## 🏗️ Project Structure (Backend)
````
src/main/java/com/sdn/health/
├── controller/ ← REST API layer
├── service/ ← Business logic
├── repository/ ← Database layer (JPA)
├── model/ ← Entities (Patient, Doctor, Appointment)
├── dto/ ← Request/Response objects
└── exception/ ← Custom exceptions
