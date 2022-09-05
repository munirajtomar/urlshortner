# URL Shortener

This project exposes two API's, first one POST takes a long URL and returns a shortened URL key and the second one takes key and returns full original url.This project uses inbuilt H2 Database to persist data.

# Getting Started

## Dependencies

All dependencies can be found in pom.xml , some of them are :
* spring-boot-starter-web (Spring boot framework)
* spring-boot-starter-data-jpa (for data persistence)
* spring-boot-starter-test (for tests)
* springfox-swagger-ui (for api docs)

## Project Build

To build this project, run

```shell script
git clone https://github.com/munirajtomar/urlshortner.git
cd urlshortner
mvn clean install
```

## To run this project, run

```shell script
mvn spring-boot:run
```

**The application will be accessible on http://localhost:8080**

## API Endpoints

You can access following API endpoints at http://localhost:8080/swagger-ui/

### POST `/url`
It takes a JSON object in the following format as payload

```json
{
  "originalUrl":"<The full URL which is to be shortened>"
}
```

#### cURL

```shell script
curl -X POST \
  http://localhost:8080/api/v1/url \
  -H 'Content-Type: application/json' \
  -d '{"originalUrl":"https://example.com"}'
```

Response:

```json
{
  "originalUrl": "abcd@example.com",
  "urlKey": "973347"
}
```

### GET `/<urlKey>`

This endpoint returns back the corresponding fullUrl.

#### cURL

```shell script
curl -X GET   http://localhost:8080/api/v1/url/abcdef
```

# Contributors
email: muniraj.tomar91@gmail.com