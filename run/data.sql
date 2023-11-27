INSERT INTO brand (name, created_at, updated_at)
VALUES ('Toyota', '2023-11-01', '2023-11-01');

INSERT INTO brand (name, created_at, updated_at)
VALUES ('Ford', '2023-11-02', '2023-11-02');

INSERT INTO brand (name, created_at, updated_at)
VALUES ('Honda', '2023-11-03', '2023-11-03');

INSERT INTO brand (name, created_at, updated_at)
VALUES ('Chevrolet', '2023-11-04', '2023-11-04');

-- Insertion des couleurs
INSERT INTO color (name)
VALUES ('Red');

INSERT INTO color (name)
VALUES ('Blue');

INSERT INTO color (name)
VALUES ('Green');

INSERT INTO color (name)
VALUES ('Yellow');

INSERT INTO color (name)
VALUES ('Black');

INSERT INTO color (name)
VALUES ('White');

INSERT INTO color (name)
VALUES ('Silver');

INSERT INTO color (name)
VALUES ('Gray');

INSERT INTO color (name)
VALUES ('Orange');

INSERT INTO color (name)
VALUES ('Purple');

INSERT INTO color (name)
VALUES ('Brown');

INSERT INTO color (name)
VALUES ('Pink');

INSERT INTO color (name)
VALUES ('Gold');

INSERT INTO color (name)
VALUES ('Turquoise');

-- Insertion des configurations
INSERT INTO configuration (name)
VALUES ('Airbag');

INSERT INTO configuration (name)
VALUES ('Vitre électrique');

INSERT INTO configuration (name)
VALUES ('Boîte automatique');


INSERT INTO configuration (name)
VALUES ('Climatisation');


INSERT INTO configuration (name)
VALUES ('Toit ouvrant');

INSERT INTO configuration (name)
VALUES ('Sièges en cuir');

INSERT INTO configuration (name)
VALUES ('Système de navigation');

INSERT INTO configuration (name)
VALUES ('Caméra de recul');



-- Insertion des modèles
INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Camry', '2023-11-01', '2023-11-01', 1);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Corolla', '2023-11-02', '2023-11-02', 1);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Rav4', '2023-11-03', '2023-11-03', 1);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Prius', '2023-11-04', '2023-11-04', 1);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Highlander', '2023-11-05', '2023-11-05', 1);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Tundra', '2023-11-06', '2023-11-06', 1);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Sienna', '2023-11-07', '2023-11-07', 1);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('F-150', '2023-11-01', '2023-11-01', 2);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Escape', '2023-11-02', '2023-11-02', 2);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Explorer', '2023-11-03', '2023-11-03', 2);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Mustang', '2023-11-04', '2023-11-04', 2);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Fusion', '2023-11-05', '2023-11-05', 2);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Ranger', '2023-11-06', '2023-11-06', 2);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Edge', '2023-11-07', '2023-11-07', 2);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Civic', '2023-11-01', '2023-11-01', 3);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Accord', '2023-11-02', '2023-11-02', 3);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('CR-V', '2023-11-03', '2023-11-03', 3);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Pilot', '2023-11-04', '2023-11-04', 3);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('HR-V', '2023-11-05', '2023-11-05', 3);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Odyssey', '2023-11-06', '2023-11-06', 3);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Fit', '2023-11-07', '2023-11-07', 3);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Silverado', '2023-11-01', '2023-11-01', 4);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Equinox', '2023-11-02', '2023-11-02', 4);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Malibu', '2023-11-03', '2023-11-03', 4);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Camaro', '2023-11-04', '2023-11-04', 4);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Traverse', '2023-11-05', '2023-11-05', 4);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Suburban', '2023-11-06', '2023-11-06', 4);

INSERT INTO model (name, created_at, updated_at, brand_id)
VALUES ('Impala', '2023-11-07', '2023-11-07', 4);

-- Insertion des véhicules
-- Véhicule 1
INSERT INTO vehicle (year_of_creation, price_without_configuration, created_at, updated_at, model_id, color_id)
VALUES (2023, 25000.0, '2023-11-01', '2023-11-01', 1, 1);

-- Véhicule 2
INSERT INTO vehicle (year_of_creation, price_without_configuration, created_at, updated_at, model_id, color_id)
VALUES (2023, 28000.0, '2023-11-02', '2023-11-02', 2, 2);

-- Véhicule 3
INSERT INTO vehicle (year_of_creation, price_without_configuration, created_at, updated_at, model_id, color_id)
VALUES (2023, 26000.0, '2023-11-03', '2023-11-03', 3, 3);

-- Véhicule 4
INSERT INTO vehicle (year_of_creation, price_without_configuration, created_at, updated_at, model_id, color_id)
VALUES (2023, 30000.0, '2023-11-04', '2023-11-04', 4, 4);



INSERT INTO `vehicle_configuration` (`vehicle_id`, `configuration_id`) VALUES ('1', '1'), ('1', '4'), ('2', '2'), ('2', '3'), ('3', '4'), ('3', '5'), ('4', '4'), ('4', '5'), ('1', '6'), ('2', '7');


-- Sale 1
INSERT INTO Sale (price, date_sale, date_delivery, date_expiration_insurance, created_at, updated_at, vehicle_id)
VALUES (25000.0, '2023-11-01', '2023-11-01', '2023-11-30', '2023-11-01', '2023-11-01', 1);

-- Sale 2
INSERT INTO Sale (price, date_sale, date_delivery, date_expiration_insurance, created_at, updated_at, vehicle_id)
VALUES (28000.0, '2023-11-02', '2023-11-02', '2023-12-01', '2023-11-02', '2023-11-02', 2);

-- Sale 3
INSERT INTO Sale (price, date_sale, date_delivery, date_expiration_insurance, created_at, updated_at, vehicle_id)
VALUES (26000.0, '2023-11-03', '2023-11-03', '2023-12-02', '2023-11-03', '2023-11-03', 3);

-- Sale 4
INSERT INTO Sale (price, date_sale, date_delivery, date_expiration_insurance, created_at, updated_at, vehicle_id)
VALUES (30000.0, '2023-11-04', '2023-11-04', '2023-12-03', '2023-11-04', '2023-11-04', 4);
