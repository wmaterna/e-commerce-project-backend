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

INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (1, 'Warscewiczii', 'Calatheas are magnificent tropical plants with beautifully shaped leaves. This Calathea Warscewiczii baby is perhaps one of the most remarkable varieties. Its exotic velvety leaves with two colours of green and burgundy undersides really make it stand out from your other plants. Besides being super decorative, she is also very air purifying! We don''t know why you shouldn''t take this beautiful baby into your home...', 2.99, 'The Prince of Orange is relatively easy to care for. Like most Philodendrons, it prefer a bright spot with lots of indirect sunlight as well as a slightly moist soil. The Prince of Orange typically grows towards the light so make sure to turn it around a bit every now and then. This will help the plant grow evenly and produce beautiful leaves on all sides! Older leaves on the Prince can turn yellow again, it is therefore important to distinguish between a normal shift in the leaves’ colors or a shifting in color that indicates sickness or old age before taking action.', 0, 'https://images.unsplash.com/photo-1602425224901-e7949d43938e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=765&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (2, 'White Fusion', 'The baby Monstera Adansonii, sometimes known as a Swiss cheese plant or a monkey mask, is a natural climber. If you give her a bit of space in your living room you will quickly see roots emerging to try to find things to grab hold of. Offer them a wire or a post to hold on to and in no time your Monstera will be on its way upwards, and will show how happy she is by sprouting big new leaves.', 9.99, 'res',0,'https://images.unsplash.com/photo-1611068381613-29edd19f25c8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=765&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (3, 'Medallion', 'The Alocasia Frydek is also known as the Green Velvet Alocasia because of its velvety leaves. You can recognise her by her arrow-shaped foliage with beautiful bright white veins. Even as a baby it almost look like she is glowing! How cute that you can raise your own baby Alocasia Frydek? Witness every new leaf of this cute BabyPLNT and don’t forget to capture these moments, because before you know it, she’s big!', 10.99, 'res',0,'https://images.unsplash.com/photo-1602879952653-a3830f12a7cb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (4, 'Zebrina', 'The baby Alocasia Zebrina is the princess of the plant savannah. With her beautiful zebra-striped stems she is a feast for the eyes. In nature her leaves grow so big that Alocasias are often called the ‘elephant''s ear’ plant. For many animals in the South East Asian rainforest, the leaves of the Alocasia Zebrina, which can sometimes grow up to 100 centimetres in size, offer a safe hiding place. With your care she will grow into a beautiful houseplant and you’ll never get tired of the compliments!', 4.50, 'res',0,'https://images.unsplash.com/photo-1655382355596-7bf7012a92f1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (5, 'Deliciosa Variegated', 'The Monstera Deliciosa Variegated is a real show stopper! Her leaves are big, beautiful and heart-shaped, just like a regular Monstera Deliciosa. The thing that makes her special is the deviating colour on her leaves. Her white small paint-like splotches and dots on the leaves make her a stunning piece of art. Variegation is extremely rare, as it is a difficult process to create genetic mutations, so you have to be quick if you want to become her PLNTSparent!', 144.95, 'res',1,'https://images.unsplash.com/photo-1614594975525-e45190c55d0b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (6, 'Pertusum', 'The bigger the plant, the better right?! With the Monstera Pertusum in size XXL you really imagine yourself in a jungle! We don’t blame you if you’re thinking, isn’t this a Monstera Deliciosa, because they look exactly the same. The difference between the two is that the Monstera Pertusum is grown from cuttings', 64.95, 'res',1,'https://images.unsplash.com/photo-1625582598943-2445bb7b8253?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (7, 'Ava Pot Ceramic', 'The Ava potting line is made of ceramics and based on natural earth tones. These earth tones provide warmth and a relaxing atmosphere. What brings more nature into your home, next to these nature colours? Our PLNTS of course! The Ava pot is available in several sizes (S, M and L) and colours.', 5.99, 'res',4,'https://images.unsplash.com/photo-1585445490582-9872899757b7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (8, 'Lisa Pot Ceramic', 'Our Limited edition summer line will help you to add some colour and summer vibes into your home. Every plant will shine in this pot. We hope those stylish ceramics will make you and you plants as happy as it makes us! ', 6.99, 'res',4,'https://images.unsplash.com/photo-1463320898484-cdee8141c787?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (9, 'Alexis Basket Seagrass', 'The little, cute Alexis is a comfortable home to your BabyPLNTS. The basket is completely made of seagrass which creates a warm touch in your room. ', 6.99, 'res',5,'https://images.unsplash.com/photo-1530968348606-69c989d6efc9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (10, 'Grey Terracotta Pot', 'Terracotta is a real classic but can be fragile. The image pictured is indicative, but please note that the pot you receive can contain small imperfections. ', 10.99, 'res',5,'https://images.unsplash.com/photo-1620293106076-ad27d651efe3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (11, 'Toby Terrarium Large', 'Terrariums are back again! They are very decorative and especially easy to maintain. Toby Terrarium is a sealed pot made from recycled glass. ', 11.99, 'res',8,'https://images.unsplash.com/photo-1559070354-cc46ee8e0d88?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1169&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (12, 'Olly Terrarium Large', 'This product consists of only the terrarium jar!', 12.99, 'res',8,'https://images.unsplash.com/photo-1608718119004-04dcc9426902?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (13, 'Lumi Dinner Candle Smoke', 'To make your growing home even more cozier, meet our Lumi cndl! She is a diner candle that’s completely made from stearine. This is a natural material which burns down beautifully.', 3.99, 'res',9,'https://images.unsplash.com/photo-1572726729207-a78d6feb18d7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (14, 'Potting soil 1 liter', 'Give your plants a boost with our potting soil! This universal soil mixture is the ideal breeding ground that helps your BabyPLNTS grow.', 2.99, 'res',12,'https://images.unsplash.com/photo-1495881674446-33314d7fb917?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (15, 'Repotting mix kit', 'Our repotting mix kit consists of 3 different substrates. These substrates are carefully selected. Good to know is that we already have a good basic potting soil mix and based on that we have chosen the additives.', 10.99, 'res',12,'https://images.unsplash.com/photo-1637500980709-6e65a6c2418a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (16, 'Hydro Grains', 'Hydro Grains make the care of your plants much easier. The grains absorb moisture, not only from the soil but also from the roots! You can put them on the bottom or on top of the soil, so excess water is evenly distributed throughout the soil. The solution for pots without any drainage holes.', 9.99, 'res',14,'https://images.unsplash.com/photo-1637500977020-a5e9e043b285?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (17, 'Perlite Grains', 'Perlite Grains are little roasted volcanic rocks that make the potting soil airier and lighter. This results in roots that develop better and a satisfied, better growth of the plant. ', 6.99, 'res',14,'https://images.unsplash.com/photo-1581578017093-cd30fce4eeb7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80');
INSERT INTO product (id, name, description, price, recommendations, subcategor_id, url) VALUES (18, 'Orbifolia', 'Calatheas are magnificent tropical plants with beautifully shaped leaves. This Calathea Warscewiczii baby is perhaps one of the most remarkable varieties. Its exotic velvety leaves with two colours of green and burgundy undersides really make it stand out from your other plants. Besides being super decorative, she is also very air purifying! We don''t know why you shouldn''t take this beautiful baby into your home...', 10.99, 'res',0,'https://images.unsplash.com/photo-1584589167171-541ce45f1eea?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80');

INSERT INTO subcategory (id, name, category_id) VALUES (0, 'Calathea', 1);
INSERT INTO subcategory (id, name, category_id) VALUES (1, 'Monstera', 1);
INSERT INTO subcategory (id, name, category_id) VALUES (4, 'Basic', 2);
INSERT INTO subcategory (id, name, category_id) VALUES (5, 'Nature', 2);
INSERT INTO subcategory (id, name, category_id) VALUES (8, 'Terrarium', 3);
INSERT INTO subcategory (id, name, category_id) VALUES (9, 'Candles', 3);
INSERT INTO subcategory (id, name, category_id) VALUES (12, 'Potting soil', 4);
INSERT INTO subcategory (id, name, category_id) VALUES (14, 'Substrates', 4);
