
up:
	docker compose down && mvnw clean package  &&docker compose up -d
down:
	docker compose down


