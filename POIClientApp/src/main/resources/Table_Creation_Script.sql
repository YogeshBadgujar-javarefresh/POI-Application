create database poiClientApp;

use poiClientApp;

-- POI table for offline data store
CREATE TABLE poi(
id bigint(20) Primary key NOT NULL AUTO_INCREMENT,
name varchar (25) NOT NULL,
latitude varchar (8) NOT NULL,
longitude varchar (8) NOT NULL,
active int(1) DEFAULT 1
);

-- category table
CREATE TABLE category(
id bigint(20) Primary key NOT NULL AUTO_INCREMENT,
name varchar (25) NOT NULL,
active int(1) DEFAULT 1
);