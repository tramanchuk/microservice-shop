docker run --name pg-shop-db -e POSTGRES_USER=upostgres -e POSTGRES_PASSWORD=ppostgres -e POSTGRES_DB=shop-db -d -p 5432:5432 -e PGDATA=/var/lib/postgresql/data1/pgdata -v .:/var/lib/postgresql/data1 postgres

docker ps

docker exec -it pg-shop-db bash

psql -h localhost -U upostgres -d shop-db -f /var/lib/postgresql/data1/schema.sql
psql -h localhost -U upostgres -d shop-db -f /var/lib/postgresql/data1/data.sql
