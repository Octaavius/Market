FROM mysql:latest

ENV MYSQL_DATABASE market_db
ENV MYSQL_ROOT_PASSWORD root
ENV MYSQL_USER admin
ENV MYSQL_PASSWORD admin

COPY market.sql /docker-entrypoint-initdb.d/