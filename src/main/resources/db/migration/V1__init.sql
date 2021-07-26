
CREATE TABLE IF NOT EXISTS
categories (
    id bigserial,
    title bigint,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS
products (
    id bigserial,
    title VARCHAR(255),
    price numeric(8, 2) not null,
    category_id bigint references categories (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id)
);

INSERT INTO products (title, price, category_id)
VALUES
('Product_1', 100, 1),
('Product_2', 20, 1),
('Product_3', 440, 1),
('Product_4', 78, 1),
('Product_5', 12, 1),
('Product_6', 1500, 1),
('Product_7', 205, 1),
('Product_8', 40, 1),
('Product_9', 8, 1),
('Product_10', 712, 1);

CREATE TABLE IF NOT EXISTS
orders (
    id bigserial,
    user_id bigint,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS
order_items (
    id bigserial,
    order_id bigint references orders (id),
    product_id bigint references products(id),
    price numeric(8, 2) not null,
    quantity int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id)
);

