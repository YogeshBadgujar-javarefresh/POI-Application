-- poi
INSERT INTO poi (id, latitude, longitude, name, active) 
VALUES ('1', '41.1027', '90.2029', 'Petrol Pump', '1');

INSERT INTO poi (id, latitude, longitude, name, active) 
VALUES ('2', '18.5987', '73.7978', 'Pimple Saudagar', '1');

-- category
INSERT INTO category (id, name, active) 
VALUES ('1', 'Tree', '1');
INSERT INTO category (id, name, active) 
VALUES ('2', 'Building', '1');
INSERT INTO category (id, name, active) 
VALUES ('3', 'Board', '1');


-- poi_details
INSERT INTO poi_details(id, version, file_name, upload_dir, poi_id, category_id, created_by, created_date, modified_by, modified_date, active) 
VALUES ('1', '1', '18.5987_73.7978_1.jpg', '\home\xyx\\', '2','1', 'Yogesh', '08-08-2020', 'Yogesh', '08-08-2020', '1');

-- user_details
INSERT INTO user_details (id, user_id, machine_id, bucket_details, active) 
VALUES ('1', 'YogeshB', 'GKSF1234GTS', '\S3\home\xyx\\', '1');