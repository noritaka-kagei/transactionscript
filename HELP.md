# Requirement

- Java 22
- Build Tool: Maven
- Database: PostgreSQL container [https://hub.docker.com/_/postgres]

## Build Database

Launch postgreSQL container on port 55432

```bash
docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -p 55432:5432 -d postgres
```

Copy this script to the container

```bash
docker cp init.sql some-postgres:/init.sql
```

Execute the script

```bash
docker exec -it some-postgres psql -U postgres -f /init.sql
```

### Table Schema

|ID|Name|E-mail|Phone Number|Postal Code|Address|Birth Date|Created at|
|:--|:--|:--|:--|:--|:--|:--|:--|
|1|山田 太郎|taro.yamada@example.com|080-1234-5678|123-4567|東京都千代田区千代田1-1|1980-01-01|timestamp|
|2|鈴木 花子|hanako.suzuki@example.com|090-9876-5432|987-6543|大阪府大阪市北区梅田2-4-9|1990-02-15|timestamp|

## Run Server (Application)

Run application via Maven command

```bash
.mvnw spring-boot:run
or
mvn spring:boot:run
```

## Request for getting 

Get all users to `/users` endpoint

```bash
curl http://localhost:8080/users
```

<details><summary>出力結果</summary>
[
    {
        "id": 1,
        "name": "山田 太郎",
        "email": "taro.yamada@example.com",
        "phone": "080-1234-5678",
        "postalCode": "123-4567",
        "address": "東京都千代田区千代田1-1",
        "birthDate": "1980-01-01",
        "createdAt": "2024-06-15"
    },
    {
        "id": 2,
        "name": "鈴木 花子",
        "email": "hanako.suzuki@example.com",
        "phone": "090-9876-5432",
        "postalCode": "987-6543",
        "address": "大阪府大阪市北区梅田2-4-9",
        "birthDate": "1990-02-15",
        "createdAt": "2024-06-15"
    }
]
</details>

Post a user to `/users` endpoint

```bash
curl -X POST http://localhost:8080/users \
    -H "Content-Type: application/json" \
    -d '{
    "name": "むきむき太郎",
    "email": "java-training@example.com",
    "phone": "080-1234-1234",
    "postal_code": "111-1111",
    "address": "神奈川県川崎市中原区XX町NN-N",
    "birth_date": "2000-01-01"
    }'
```

<details><summary>出力結果</summary>
{
    "id": 3,
    "name": "むきむき太郎",
    "email": "java-training@example.com",
    "phone": "080-1234-1234",
    "postalCode": null,
    "address": "神奈川県川崎市中原区XX町NN-N",
    "birthDate": null,
    "createdAt": null
}
</details>