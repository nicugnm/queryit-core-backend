CREATE TABLE IF NOT EXISTS
    categories (
                   category_id SERIAL,
                   name VARCHAR,

                   PRIMARY KEY (category_id)
    );

CREATE TABLE IF NOT EXISTS
    promotions (
                   promotion_id SERIAL,
                   name VARCHAR,
                   description VARCHAR,
                   expire_date BIGINT,
                   quantity_needed INTEGER,

                   PRIMARY KEY (promotion_id)
    );

CREATE TABLE IF NOT EXISTS
    minimarkets (
                    minimarket_id SERIAL,
                    name VARCHAR,

                    PRIMARY KEY (minimarket_id)
    );

CREATE TABLE IF NOT EXISTS
    deposits (
                 deposit_id SERIAL,
                 name VARCHAR,

                 PRIMARY KEY (deposit_id)
    );

CREATE TABLE IF NOT EXISTS
    trucks (
               truck_id SERIAL,
               serial_number VARCHAR,

               PRIMARY KEY (truck_id)
    );

CREATE TABLE IF NOT EXISTS
    manufacturers (
                manufacturer_id SERIAL,
                deposit_id INTEGER,
                truck_id INTEGER,
                name VARCHAR,

                PRIMARY KEY (manufacturer_id),

        CONSTRAINT fk_deposit_id FOREIGN KEY (deposit_id) REFERENCES deposits (deposit_id),
        CONSTRAINT fk_truck_id FOREIGN KEY (truck_id) REFERENCES trucks (truck_id)
);

CREATE TABLE IF NOT EXISTS
    products (
                 product_id SERIAL,
                 promotion_id INTEGER,
                 category_id INTEGER,
                 minimarket_id INTEGER,
                 manufacturer_id INTEGER,
                 name VARCHAR,
                 price DECIMAL,
                 quantity INTEGER,
                 icon_path VARCHAR,

                 PRIMARY KEY (product_id),

    CONSTRAINT fk_promotion_id FOREIGN KEY (promotion_id) REFERENCES promotions (promotion_id),
    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES categories (category_id),
    CONSTRAINT fk_minimarket_id FOREIGN KEY (minimarket_id) REFERENCES minimarkets (minimarket_id),
    CONSTRAINT fk_manufacturer_id FOREIGN KEY (manufacturer_id) REFERENCES manufacturers (manufacturer_id)
    );
