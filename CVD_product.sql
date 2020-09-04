--  Sample coronakitdb database 


DROP DATABASE IF EXISTS coronakitdb ;
CREATE DATABASE IF NOT EXISTS coronakitdb ;
USE coronakitdb ;

SELECT 'CREATING DATABASE STRUCTURE' as 'INFO';

DROP TABLE IF EXISTS kit;

/*!50503 set default_storage_engine = InnoDB */;
/*!50503 select CONCAT('storage engine: ', @@default_storage_engine) as INFO */;

CREATE TABLE product (
    kit_id    INT               NOT NULL auto_increment,
    /*coronakitid  VARCHAR(100)      NOT NULL,*/
    productid  INT     NOT NULL,
	productquantity  VARCHAR(100)      NOT NULL,
    productamount DECIMAL(1000,2)      NOT NULL,
    PRIMARY KEY (kit_id), 
    FOREIGN KEY (productid) REFERENCES product(product_id)
)engine=InnoDB auto_increment=1;

INSERT INTO `product` VALUES 
(001,'Masks N95','160.00','N95 masks with filter'),
(002,'PPE Kit ','360.00','PPE Kit'),
(003,'Sanitizer','180.00','Hand Sanitizer');
