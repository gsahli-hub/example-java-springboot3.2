# Example Java Spring Boot 3.2 API

> ⚠️ **This repo contains intentional vulnerabilities and fake secrets for security scanner testing. DO NOT use in production.**

## Stack
- **Framework:** Spring Boot 3.2.0
- **Java:** 17
- **Build:** Maven

## Endpoints
| Method | Path | Response |
|--------|------|----------|
| GET | `/` | `{"message":"hello","framework":"Spring Boot","version":"3.2.0","java":"17","greeting":"hi"}` |
| GET | `/health` | `{"status":"UP"}` |

## Run
```bash
mvn spring-boot:run
```

## Docker
```bash
docker build -t example-java-springboot3.2 .
docker run -p 8080:8080 example-java-springboot3.2
```

## Intentional Vulnerabilities
- Guava 31.0.1 (CVE-2023-2976)
- Netty 4.1.86 (multiple CVEs)
- Bouncy Castle 1.72 (multiple CVEs)
- Spring Security 6.1.0 (multiple CVEs)

## Intentional Secrets
- GitHub PAT in source code and properties
- Database password in application.properties
- Slack webhook URL
- SendGrid API key
