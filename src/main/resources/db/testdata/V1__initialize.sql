CREATE TABLE IF NOT EXISTS
categories (
    id bigserial,
    title VARCHAR(255),
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

CREATE TABLE IF NOT EXISTS
users (
    id bigserial,
    name VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS
roles (
    id serial,
    title VARCHAR(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS
users_roles (
    user_id bigint not null references users (id),
    role_id int not null references roles (id),
    primary key (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS
authorities (
    id serial,
    title VARCHAR(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS
roles_authorities (
    role_id int not null references roles (id),
    authority_id int not null references authorities (id),
    primary key (role_id, authority_id)
);

CREATE TABLE IF NOT EXISTS
orders (
    id bigserial,
    user_id bigint references users(id),
    price numeric(8, 2) not null,
    address VARCHAR(255),
    phone VARCHAR(12),
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

INSERT INTO users (name, password, email)
VALUES
('test', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'test@gmail.com');

INSERT INTO roles (title)
VALUES
('ROLE_TEST');

INSERT INTO authorities (title)
VALUES
('TEST');

INSERT INTO users_roles (user_id, role_id)
VALUES
(1,1);

INSERT INTO roles_authorities (role_id, authority_id)
VALUES
(1,1);

INSERT INTO categories (title)
VALUES
('Test_category');

INSERT INTO products (title, price, category_id)
VALUES
('Test_product', 111, 1),
('Test_product', 222, 1),
('Test_product', 333, 1),
('Test_product', 444, 1);

INSERT INTO orders (user_id, price, address, phone)
VALUES
(1, 333, 'Address_1', 'Telephone_1'),
(1, 666, 'Address_2', 'Telephone_2'),
(1, 999, 'Address_3', 'Telephone_3');

INSERT INTO order_items (order_id, product_id, price, quantity)
VALUES
(1, 1, 500, 3);
