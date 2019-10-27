CREATE DATABASE IF NOT exists `blz`;

CREATE TABLE `blz`.`product` (
  `sku` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(400) NOT NULL,
  PRIMARY KEY (`sku`)
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
;


CREATE TABLE `blz`.`warehouses` (
  `id_warehouse` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `locality` VARCHAR(100) NOT NULL,
  `quantity` integer NOT NULL,
  `type` VARCHAR(200) NOT NULL,
  `sku` BIGINT(11) NOT NULL,
  PRIMARY KEY (`id_warehouse`),
 -- INDEX `fk_product_sku` (`sku` ASC),
  CONSTRAINT `fk_sku`
    FOREIGN KEY (`sku`)
    REFERENCES `blz`.`product`(`sku`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
;

INSERT INTO `blz`.`product` (`sku`, `name`) 
VALUES (
'43264',
 'L\'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g'
 );

INSERT INTO `blz`.`warehouses` (`id_warehouse`, `locality`, `quantity`, `type`, `sku`)
 VALUES 
 (1, 'SP', 12, 'ECOMMERCE', 43264);
 
INSERT INTO `blz`.`warehouses` (`id_warehouse`, `locality`, `quantity`, `type`, `sku`)
 VALUES 
 (2, 'MOEMA', 3, 'PHYSICAL_STORE', 43264);

