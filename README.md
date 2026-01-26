#  SignalOps Backend

SignalOps backend is a **Spring Boot (Java 17)** application providing authentication and core APIs, backed by **PostgreSQL** and built with **Maven**.

This README documents the **exact commands** required to build and run the backend locally.

---

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA (Hibernate)
- PostgreSQL 16
- Maven
- Docker

---

## Prerequisites

Ensure the following are installed:

```bash
java -version        # Java 17+
mvn -version         # Maven 3.9+
docker --version
docker compose version
psql --version


PostgreSQL (Docker Setup)
1. Create Docker volume (one-time)
docker volume create signalops_pgdata

2. Run PostgreSQL container
docker run -d \
  --name signalops-postgres \
  -e POSTGRES_USER=signalops \
  -e POSTGRES_PASSWORD=signalops \
  -e POSTGRES_DB=signalops \
  -p 5433:5432 \
  -v signalops_pgdata:/var/lib/postgresql/data \
  postgres:16


PostgreSQL is exposed on port 5433 to avoid conflicts with local installs.

3. Verify database connection
psql -h 127.0.0.1 -p 5433 -U signalops -d signalops -c "select 1;"


Build the Application

From the signalops-backend directory:

mvn clean package

Run Backend 

Use Maven for local development:

mvn spring-boot:run \
  -Dspring-boot.run.arguments="\
  --spring.datasource.url=jdbc:postgresql://127.0.0.1:5433/signalops \
  --spring.datasource.username=signalops \
  --spring.datasource.password=signalops \
  --spring.datasource.driver-class-name=org.postgresql.Driver"


On success:

Started SignalopsBackendApplication in X.XXX seconds


Backend runs at:

http://localhost:8080

Alternate: Run JAR Directly
java -jar target/signalops-backend-0.0.1-SNAPSHOT.jar \
  --spring.datasource.url=jdbc:postgresql://127.0.0.1:5433/signalops \
  --spring.datasource.username=signalops \
  --spring.datasource.password=signalops \
  --spring.datasource.driver-class-name=org.postgresql.Driver

Stop Services
Stop backend
Ctrl + C

Stop PostgreSQL container
docker stop signalops-postgres


(Optional cleanup)

docker rm signalops-postgres
docker volume rm signalops_pgdata