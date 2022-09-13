# Backend application KnowBase for saving knowledge locally

###
- JAVA 17
- SPRING WEB
- SPRING JPA
- SPRING SECURITY

For application need EnvFile by Borys Pierov plugin and .env file which contains:
```dotenv
IP_ADDRESS=host.docker.internal

SERVICE_PORT=8081

API_PATH=/

GATEWAY_PORT=8080

SERVICE_URI=http://${IP_ADDRESS}:8081
STORAGE_SERVICE_URI=http://${IP_ADDRESS}:8082

POSTGRES_VERSION=14
POSTGRES_PORT=[your postgres port here]
POSTGRES_SCHEMA=kbase
POSTGRES_DB=kbase
POSTGRES_URL=jdbc:postgresql://${IP_ADDRESS}:${POSTGRES_PORT}/${POSTGRES_DB}
POSTGRES_USER=[your postgres user here]
POSTGRES_PASSWORD=[your postgres password here]

LIQUIBASE_VERSION=4.11

GRAFANA_VERSION=9.0.2
GRAFANA_USER=[your grafana user here]
GRAFANA_PASSWORD=[your grafana password here]
GRAFANA_PORT=[your grafana port here]

PROMETHEUS_VERSION=v2.36.2
PROMETHEUS_PORT=[your prometheus port here]

MINIO_PORT=[your minio port here]
MINIO_PORT_1=[your minio another port here]
MINIO_ENDPOINT=http://${IP_ADDRESS}:${MINIO_PORT}
MINIO_ACCESS_KEY=[your minio access key here]
MINIO_SECRET_KEY=[your minio secret key here]

PASSWORD_SALT=[your salt here]
JWT_SIGNING_KEY=[your signing key here]
```
For successfully running liquibase need to append in db/liquibase.properties:
```dotenv
username: [your postgres user here]
password: [your postgres password here]
```
For building application need to:
```dotenv
mvn clean package -DskipTests
```
For running application in Docker need to build application and:
```dotenv
docker compose up
```
Swagger documentation
```dotenv
http://localhost:8081/swagger-ui/index.html
```
Grafana metrics
```dotenv
http://localhost:3000/
```