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

### 全タスク取得
```
curl localhost:8081/tasks
```

### タスク取得
```
curl localhost:8081/task/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11
```

### タスク作成
```
curl -X POST -H "Content-Type: application/json" -d '{"name":"タスク３", "duedate":"2100-03-03"}' localhost:8081/task
```

### タスク更新
```
curl -X PUT -H "Content-Type: application/json" -d '{"name":"タスク４"}' localhost:8081/task/5a9e1468-2dc5-4aae-9503-3f3edaa86391
```

### タスク削除
```
curl -X DELETE localhost:8081/task/5a9e1468-2dc5-4aae-9503-3f3edaa86391
```
