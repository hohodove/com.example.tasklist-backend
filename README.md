# tasklist-backend

## environment
+ M1 MacBook Air
+ IntelliJ IDEA 2021.3.2 (Community Edition)

## Requirement
+ Kotlin 1.6.10
+ Ktor 1.6.7
+ JDBI 3.27.1
+ Koin 3.1.5
+ JUnit 5
+ docker
+ PostgreSQL
+ H2

## note
+ プロジェクトはKtor Project Generator(start.ktor.io)にて作成。(IntelliJ Community版ではKtorプラグインが利用不可のため)
+ Databaseは通常動作時はPostgreSQL、Unit test時はH2で稼働。

## URL
```
http://localhost:8081/
```

## コマンド

### サービス起動
```
./gradlew run
```

### テスト実施
```
./gradlew test
```

### Dockerコンテナ起動（image pull含む）、確認
```
docker-compose up -d
docker images
docker ps
```

### Dockerコンテナ停止
```
docker-compose stop
```

### PostgreSQL接続
```
docker exec -it postgresql_tasklist_app /bin/bash
psql -U admin -d test
--TABLE一覧
\dt
select * from tasks;
```

### Flyway
```
./gradlew flywayInfo
./gradlew flywayClean
./gradlew flywayMigrate -i
./gradlew flywayInfo
```
