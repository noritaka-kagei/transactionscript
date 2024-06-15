-- How to setup database
-- 1. launch postgreSQL container
--  $ docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -p 55432:5432 -d postgres
-- 2. copy this script to the container
--  $ docker cp init.sql some-postgres:/init.sql
-- 3. execute the script
--  $ docker exec -it some-postgres psql -U postgres -f /init.sql

CREATE DATABASE mydatabase;

\c mydatabase

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    postal_code VARCHAR(10),
    address TEXT,
    birth_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (name, email, phone, postal_code, address, birth_date) VALUES 
('山田 太郎', 'taro.yamada@example.com', '080-1234-5678', '123-4567', '東京都千代田区千代田1-1', '1980-01-01'),
('鈴木 花子', 'hanako.suzuki@example.com', '090-9876-5432', '987-6543', '大阪府大阪市北区梅田2-4-9', '1990-02-15');
