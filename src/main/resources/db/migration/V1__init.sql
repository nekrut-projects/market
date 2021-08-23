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
('test', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'test@gmail.com'),
('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob@gmail.com'),
('nick', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'nick@gmail.com'),
('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john@gmail.com');

INSERT INTO roles (title)
VALUES
('ROLE_CUSTOMER'),
('ROLE_ADMIN');

INSERT INTO authorities (title)
VALUES
('BUY_PRODUCTS'),
('ADD_COMMENTS'),
('ADD_PRODUCT_ON_STORAGE'),
('ADMIN');

INSERT INTO users_roles (user_id, role_id)
VALUES
(1,1),
(2,2);

INSERT INTO roles_authorities (role_id, authority_id)
VALUES
(1,1),
(1,2),
(2,1),
(2,2),
(2,3),
(2,4);

INSERT INTO categories (title)
VALUES
('Categories_1');

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
