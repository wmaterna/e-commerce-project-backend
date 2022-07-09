create table category
(
    id   integer
        constraint category_pk
            primary key autoincrement,
    name text
);


create table subcategory
(
    id          INTEGER
        primary key autoincrement,
    name        TEXT,
    category_id integer
        references category
);

create table product
(
    id              INTEGER
        primary key autoincrement,
    name            TEXT,
    description     INTEGER,
    price           REAL,
    recommendations TEXT,
    subcategor_id   INTEGER
        references subcategory,
    url             TEXT
);

create table user
(
    user_id      integer not null
        constraint user_pk
            primary key autoincrement,
    name         text,
    city         text,
    street       text,
    apartment_no text,
    post_code    text,
    oauth_id     text
);

create table opinion
(
    id         integer
        constraint opinion_pk
            primary key autoincrement,
    user       integer
        references user,
    product_id integer
        references product,
    content    text
);

create table "order"
(
    id      integer not null
        constraint order_pk
            primary key autoincrement,
    user_id integer
        references user,
    date    integer,
    address text,
    price   real
);

create table order_detail
(
    id         integer not null
        constraint order_detail_pk
            primary key autoincrement,
    order_id   integer
        references "order",
    product_id integer
        references product,
    quantity   integer
);

INSERT INTO category (id, name) VALUES(1, 'Houseplant');
INSERT INTO category (id, name) VALUES(2, 'Pots');
INSERT INTO category (id, name) VALUES(3, 'Accessories');
INSERT INTO category (id, name) VALUES(4, 'Care');

INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (1, 'Warscewiczii', 'Calatheas are magnificent tropical plants with beautifully shaped leaves. This Calathea Warscewiczii baby is perhaps one of the most remarkable varieties. Its exotic velvety leaves with two colours of green and burgundy undersides really make it stand out from your other plants. Besides being super decorative, she is also very air purifying! We don''t know why you shouldn''t take this beautiful baby into your home...', 2.99, 'The Prince of Orange is relatively easy to care for. Like most Philodendrons, it prefer a bright spot with lots of indirect sunlight as well as a slightly moist soil. The Prince of Orange typically grows towards the light so make sure to turn it around a bit every now and then. This will help the plant grow evenly and produce beautiful leaves on all sides! Older leaves on the Prince can turn yellow again, it is therefore important to distinguish between a normal shift in the leaves’ colors or a shifting in color that indicates sickness or old age before taking action.', 0, 'https://images.unsplash.com/photo-1602425224901-e7949d43938e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=765&q=80')

