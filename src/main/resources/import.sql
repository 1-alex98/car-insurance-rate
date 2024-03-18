-- This file allow to write SQL commands that will be emitted in test and dev.
INSERT INTO cartype(id, multiplier, brand, name) VALUES (1, 0.8, 'Telsa', 'Model 3');
INSERT INTO cartype(id, multiplier, brand, name) VALUES (2, 0.7 ,'Telsa', 'Model 2');
INSERT INTO cartype(id, multiplier, brand, name) VALUES (3, 1.2, 'Ford', 'B-Max');
INSERT INTO kilometers(id, multiplier, yearlykilometersdrivenlower, yearlykilometersdrivenupper) VALUES (1, 0.2, null, 999);
INSERT INTO kilometers(id, multiplier, yearlykilometersdrivenlower, yearlykilometersdrivenupper) VALUES (2, 0.8, 1000, null);
INSERT INTO location(id, multiplier, location, plz, region1, region3, region4) VALUES (1, 100.2, 'location', 12345, 'region 1', 'region 3', 'region 4');
INSERT INTO location(id, multiplier, location, plz, region1, region3, region4) VALUES (2, 106.2, 'location45', 12345, 'region 3451', 'region 334', 'region 445');
INSERT INTO location(id, multiplier, location, plz, region1, region3, region4) VALUES (3, 100.2, 'location', 1234, 'region 1', 'region 3', 'region 4');