# tasklist-backend

## environment
- M1 MacBook Air
- IntelliJ IDEA 2021.3.2 (Community Edition)

## Requirement
- Kotlin 1.6.10
- Ktor 1.6.7
- JDBI 3.27.1
- JUnit 5

## note
- プロジェクトはKtor Project Generator(start.ktor.io)にて作成。(IntelliJ Community版ではKtorプラグインが利用不可のため)

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


### Dockerイメージのビルド(初期構築時のみ)
```
cd ~/develop/tasklist-backend
docker-compose build
docker images
```

### コンテナ起動、確認
```
docker-compose up -d
docker ps
```

### コンテナ停止
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
