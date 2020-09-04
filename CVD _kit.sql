USE coronakitdb ;
DROP TABLE IF EXISTS kit;

/*!50503 set default_storage_engine = InnoDB */;
/*!50503 select CONCAT('storage engine: ', @@default_storage_engine) as INFO */;

CREATE TABLE kit (
    kit_id    INT               NOT NULL,
    /*coronakitid  VARCHAR(100)      NOT NULL,*/
    productid  INT     NOT NULL,
	productquantity  VARCHAR(100)      NOT NULL,
    productamount DECIMAL(10,2)      NOT NULL,
    PRIMARY KEY (kit_id,productid), 
    FOREIGN KEY (productid) REFERENCES product(product_id)
)