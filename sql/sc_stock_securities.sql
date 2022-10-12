CREATE TABLE sc_stock_securities (
    s_id NUMBER(6,0) PRIMARY KEY,
    s_name  VARCHAR2(200) NOT NULL,
    s_code VARCHAR2(200) NOT NULL,
    s_ltp DECIMAL(10,2) NOT NULL,
    s_asset_class VARCHAR2(200)
);

INSERT INTO sc_stock_securities (s_id, s_name, s_code, s_ltp, s_asset_class) VALUES (1, 'Apple Inc', 'AAPL',154.29,'Main Index');
INSERT INTO sc_stock_securities (s_id, s_name, s_code, s_ltp, s_asset_class) VALUES (2, 'Tesla Inc', 'TSLA',733.8,'Main Index');
INSERT INTO sc_stock_securities (s_id, s_name, s_code, s_ltp, s_asset_class) VALUES (3, 'Amazon.com', 'AMZN',170,'Main Index');
INSERT INTO sc_stock_securities (s_id, s_name, s_code, s_ltp, s_asset_class) VALUES (4, 'Vinco Ventures', 'BBIG',7.70,'Main Index');
INSERT INTO sc_stock_securities (s_id, s_name, s_code, s_ltp, s_asset_class) VALUES (5, 'Microsoft', 'MSFT',300.88,'Main Index');
INSERT INTO sc_stock_securities (s_id, s_name, s_code, s_ltp, s_asset_class) VALUES (6, 'Facebook', 'FB',386.71,'Main Index');
INSERT INTO sc_stock_securities (s_id, s_name, s_code, s_ltp, s_asset_class) VALUES (7, 'Walmart Inc', 'WMT',145.70,'Main Index');
INSERT INTO sc_stock_securities (s_id, s_name, s_code, s_ltp, s_asset_class) VALUES (8, 'Aterian', 'ATER',17.89,'Main Index');
INSERT INTO sc_stock_securities (s_id, s_name, s_code, s_ltp, s_asset_class) VALUES (9, 'BHL Group', 'BHP',61.71,'Main Index');
INSERT INTO sc_stock_securities (s_id, s_name, s_code, s_ltp, s_asset_class) VALUES (10, 'Sphere 3D Corp', 'ANY',6.71,'Main Index');

COMMIT;