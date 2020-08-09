create database poiApp;

use poiApp;

-- POI table
CREATE TABLE poi(
id bigint(20) Primary key NOT NULL AUTO_INCREMENT,
name varchar (25) NOT NULL,
latitude varchar (8) NOT NULL,
longitude varchar (8) NOT NULL,
active int(1) DEFAULT 1
);

-- POI Details
CREATE TABLE poi_details(
id bigint(20) Primary key NOT NULL AUTO_INCREMENT,
version int(10) NOT NULL,

file_name varchar (100) NOT NULL,
upload_dir varchar (250) NOT NULL,
poi_id bigint(20) NOT NULL,
category_id bigint(20) NOT NULL,

created_by varchar(10) NOT NULL,
created_date date NOT NULL,
modified_by varchar(10) NOT NULL,
modified_date date NOT NULL,
active int(1) DEFAULT 1,
foreign key(poi_id) references poi(id),
foreign key(category_id) references category(id)
);

-- category table
CREATE TABLE category(
id bigint(20) Primary key NOT NULL AUTO_INCREMENT,
name varchar (25) NOT NULL,
active int(1) DEFAULT 1
);

-- POI table
CREATE TABLE user_details(
id bigint(20) Primary key NOT NULL AUTO_INCREMENT,
user_id varchar (256) NOT NULL,
machine_id varchar (256) NOT NULL,
bucket_details varchar (150) NOT NULL,
active int(1) DEFAULT 1
);