-- 기존 테이블 삭제
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;

-- Product 테이블 생성
CREATE TABLE products
(
    product_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description VARCHAR(255),
    price       DECIMAL(10, 2) NOT NULL
);

-- Order 테이블 생성
CREATE TABLE orders
(
    order_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity   INT  NOT NULL,
    status     VARCHAR(20) DEFAULT 'PENDING',
    order_date DATE NOT NULL
);

-- OrderItem 테이블 생성
CREATE TABLE order_items
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id   BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity   INT    NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (order_id),
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);
