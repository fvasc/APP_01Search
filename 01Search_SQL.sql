SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `01search` DEFAULT CHARACTER SET latin1 ;
USE `01search` ;

-- -----------------------------------------------------
-- Table `01search`.`Login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Login` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Login` (
  `idLogin` INT NOT NULL ,
  `Login` VARCHAR(45) NOT NULL ,
  `senha` VARCHAR(45) NOT NULL ,
  `perfil` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idLogin`) ,
  UNIQUE INDEX `Login_UNIQUE` (`Login` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `01search`.`Lista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Lista` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Lista` (
  `idLista` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(20) NOT NULL ,
  `ativo` TINYINT(1) NULL ,
  `versao` INT NULL ,
  `versao_compra` INT NULL ,
  PRIMARY KEY (`idLista`) )
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;


-- -----------------------------------------------------
-- Table `01search`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Usuario` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Usuario` (
  `idUsuario` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(60) NULL ,
  `idLogin` INT NOT NULL ,
  PRIMARY KEY (`idUsuario`) ,
  INDEX `fk_Usuario_Login` (`idLogin` ASC) ,
  CONSTRAINT `fk_Usuario_Login`
    FOREIGN KEY (`idLogin` )
    REFERENCES `01search`.`Login` (`idLogin` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;


-- -----------------------------------------------------
-- Table `01search`.`Lista_Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Lista_Usuario` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Lista_Usuario` (
  `idLista` INT UNSIGNED NOT NULL ,
  `idUsuario` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`idLista`, `idUsuario`) ,
  INDEX `fk_Lista_Usuario_Usuario` (`idUsuario` ASC) ,
  INDEX `fk_Lista_Usuario_Lista` (`idLista` ASC) ,
  CONSTRAINT `fk_Lista_Usuario_Lista`
    FOREIGN KEY (`idLista` )
    REFERENCES `01search`.`Lista` (`idLista` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Lista_Usuario_Usuario`
    FOREIGN KEY (`idUsuario` )
    REFERENCES `01search`.`Usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `01search`.`Item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Item` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Item` (
  `idItem` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(20) NULL ,
  `periodicidade` INT NULL ,
  `ativo` TINYINT(1) NULL ,
  PRIMARY KEY (`idItem`) )
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;


-- -----------------------------------------------------
-- Table `01search`.`UnidadeMedida`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`UnidadeMedida` ;

CREATE  TABLE IF NOT EXISTS `01search`.`UnidadeMedida` (
  `idUnidade` INT NOT NULL ,
  `descricao` VARCHAR(45) NULL ,
  PRIMARY KEY (`idUnidade`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `01search`.`Item_Lista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Item_Lista` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Item_Lista` (
  `idLista` INT UNSIGNED NOT NULL ,
  `idItem` INT UNSIGNED NOT NULL ,
  `quantidade` INT NOT NULL ,
  `ativo` TINYINT(1) NOT NULL ,
  `periodicidade` INT NOT NULL ,
  `idUnidade` INT NOT NULL ,
  PRIMARY KEY (`idLista`, `idItem`) ,
  INDEX `fk_Item_Lista_Item` (`idItem` ASC) ,
  INDEX `fk_Item_Lista_Lista` (`idLista` ASC) ,
  INDEX `fk_Item_Lista_UnidadeMedida` (`idUnidade` ASC) ,
  CONSTRAINT `fk_Item_Lista_Item`
    FOREIGN KEY (`idItem` )
    REFERENCES `01search`.`Item` (`idItem` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_Lista_Lista`
    FOREIGN KEY (`idLista` )
    REFERENCES `01search`.`Lista` (`idLista` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_Lista_UnidadeMedida`
    FOREIGN KEY (`idUnidade` )
    REFERENCES `01search`.`UnidadeMedida` (`idUnidade` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;


-- -----------------------------------------------------
-- Table `01search`.`Marca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Marca` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Marca` (
  `idMarca` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NULL ,
  PRIMARY KEY (`idMarca`) )
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;


-- -----------------------------------------------------
-- Table `01search`.`Departamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Departamento` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Departamento` (
  `idDepartamento` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NULL ,
  PRIMARY KEY (`idDepartamento`) )
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;


-- -----------------------------------------------------
-- Table `01search`.`Subdepartamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Subdepartamento` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Subdepartamento` (
  `idSubdepartamento` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `idDepartamento` INT UNSIGNED NOT NULL ,
  `nome` VARCHAR(45) NULL ,
  PRIMARY KEY (`idSubdepartamento`) ,
  INDEX `fk_Subdepartamento_Departamento` (`idDepartamento` ASC) ,
  CONSTRAINT `fk_Subdepartamento_Departamento`
    FOREIGN KEY (`idDepartamento` )
    REFERENCES `01search`.`Departamento` (`idDepartamento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;


-- -----------------------------------------------------
-- Table `01search`.`CodigoBarras`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`CodigoBarras` ;

CREATE  TABLE IF NOT EXISTS `01search`.`CodigoBarras` (
  `idCodigoBarras` VARCHAR(11) NOT NULL ,
  `idMarca` INT UNSIGNED NOT NULL ,
  `idSubdepartamento` INT UNSIGNED NOT NULL ,
  `nome` VARCHAR(100) NULL ,
  `imagem` VARCHAR(255) NULL ,
  `data_cadastro` DATETIME NULL ,
  `data_liberacao` DATETIME NULL ,
  `descricao` VARCHAR(255) NULL ,
  PRIMARY KEY (`idCodigoBarras`) ,
  INDEX `fk_CodigoBarras_Marca` (`idMarca` ASC) ,
  INDEX `fk_CodigoBarras_Subdepartamento` (`idSubdepartamento` ASC) ,
  CONSTRAINT `fk_CodigoBarras_Marca`
    FOREIGN KEY (`idMarca` )
    REFERENCES `01search`.`Marca` (`idMarca` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CodigoBarras_Subdepartamento`
    FOREIGN KEY (`idSubdepartamento` )
    REFERENCES `01search`.`Subdepartamento` (`idSubdepartamento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;


-- -----------------------------------------------------
-- Table `01search`.`Estabelecimento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Estabelecimento` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Estabelecimento` (
  `idEstabelecimento` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `cnpj` VARCHAR(45) NOT NULL ,
  `descricao` VARCHAR(45) NULL ,
  `logo` VARCHAR(255) NULL ,
  `nome` VARCHAR(45) NOT NULL ,
  `idLogin` INT NOT NULL ,
  PRIMARY KEY (`idEstabelecimento`) ,
  UNIQUE INDEX `cnpj_UNIQUE` (`cnpj` ASC) ,
  INDEX `fk_Estabelecimento_Login` (`idLogin` ASC) ,
  UNIQUE INDEX `idLogin_UNIQUE` (`idLogin` ASC) ,
  CONSTRAINT `fk_Estabelecimento_Login`
    FOREIGN KEY (`idLogin` )
    REFERENCES `01search`.`Login` (`idLogin` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;


-- -----------------------------------------------------
-- Table `01search`.`Produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Produto` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Produto` (
  `idEstabelecimento` INT UNSIGNED NOT NULL ,
  `idCodigoBarras` VARCHAR(11) NULL ,
  `preco` DECIMAL(7,2) NULL ,
  `descricao` VARCHAR(255) NULL ,
  `localizacao` VARCHAR(100) NULL ,
  `destaque` INT NULL ,
  `idUnidade` INT NOT NULL ,
  `Produtocol` VARCHAR(45) NULL ,
  PRIMARY KEY (`idEstabelecimento`, `idCodigoBarras`) ,
  INDEX `fk_Produto_CodigoBarras` (`idCodigoBarras` ASC) ,
  INDEX `fk_Produto_Estabelecimento` (`idEstabelecimento` ASC) ,
  INDEX `fk_Produto_UnidadeMedida` (`idUnidade` ASC) ,
  CONSTRAINT `fk_Produto_CodigoBarras`
    FOREIGN KEY (`idCodigoBarras` )
    REFERENCES `01search`.`CodigoBarras` (`idCodigoBarras` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Estabelecimento`
    FOREIGN KEY (`idEstabelecimento` )
    REFERENCES `01search`.`Estabelecimento` (`idEstabelecimento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_UnidadeMedida`
    FOREIGN KEY (`idUnidade` )
    REFERENCES `01search`.`UnidadeMedida` (`idUnidade` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;


-- -----------------------------------------------------
-- Table `01search`.`Historico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Historico` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Historico` (
  `idLista` INT UNSIGNED NOT NULL ,
  `idItem` INT UNSIGNED NOT NULL ,
  `idCodigoBarras` VARCHAR(11) NULL ,
  `idEstabelecimento` INT UNSIGNED NOT NULL ,
  `versao_compra` INT NOT NULL ,
  `compraData` DATE NOT NULL ,
  `quantidade` INT NULL ,
  `preco` DECIMAL(7,2) NULL ,
  `idUnidade` INT NOT NULL ,
  PRIMARY KEY (`idLista`, `idItem`, `idCodigoBarras`, `idEstabelecimento`, `versao_compra`, `compraData`) ,
  INDEX `fk_Historico_Produto` (`idCodigoBarras` ASC, `idEstabelecimento` ASC) ,
  INDEX `fk_Historico_Item_Lista` (`idLista` ASC, `idItem` ASC) ,
  INDEX `fk_Historico_UnidadeMedida` (`idUnidade` ASC) ,
  CONSTRAINT `fk_Historico_Item_Lista`
    FOREIGN KEY (`idLista` , `idItem` )
    REFERENCES `01search`.`Item_Lista` (`idLista` , `idItem` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historico_Produto`
    FOREIGN KEY (`idCodigoBarras` , `idEstabelecimento` )
    REFERENCES `01search`.`Produto` (`idCodigoBarras` , `idEstabelecimento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historico_UnidadeMedida`
    FOREIGN KEY (`idUnidade` )
    REFERENCES `01search`.`UnidadeMedida` (`idUnidade` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `01search`.`Conversao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Conversao` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Conversao` (
  `idConversao` INT NOT NULL ,
  `fator` DECIMAL(8,4) NULL ,
  `idUnidadePrincipal` INT NOT NULL ,
  `idUnidadeSecundaria` INT NOT NULL ,
  PRIMARY KEY (`idConversao`) ,
  INDEX `fk_Conversao_UnidadeMedida` (`idUnidadePrincipal` ASC) ,
  INDEX `fk_Conversao_UnidadeMedida2` (`idUnidadeSecundaria` ASC) ,
  CONSTRAINT `fk_Conversao_UnidadeMedida`
    FOREIGN KEY (`idUnidadePrincipal` )
    REFERENCES `01search`.`UnidadeMedida` (`idUnidade` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Conversao_UnidadeMedida2`
    FOREIGN KEY (`idUnidadeSecundaria` )
    REFERENCES `01search`.`UnidadeMedida` (`idUnidade` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `01search`.`Usuario_Item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Usuario_Item` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Usuario_Item` (
  `idUsuario` INT UNSIGNED NOT NULL ,
  `idItem` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`idUsuario`, `idItem`) ,
  INDEX `fk_Usuario_Item_Item` (`idItem` ASC) ,
  INDEX `fk_Usuario_Item_Usuario` (`idUsuario` ASC) ,
  CONSTRAINT `fk_Usuario_Item_Usuario`
    FOREIGN KEY (`idUsuario` )
    REFERENCES `01search`.`Usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_Item_Item`
    FOREIGN KEY (`idItem` )
    REFERENCES `01search`.`Item` (`idItem` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `01search`.`CodigoBarras_Item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`CodigoBarras_Item` ;

CREATE  TABLE IF NOT EXISTS `01search`.`CodigoBarras_Item` (
  `idItem` INT UNSIGNED NOT NULL ,
  `idCodigoBarras` VARCHAR(11) NOT NULL ,
  PRIMARY KEY (`idItem`, `idCodigoBarras`) ,
  INDEX `fk_CodigoBarras_Item_Item` (`idItem` ASC) ,
  INDEX `fk_CodigoBarras_Item_CodigoBarras` (`idCodigoBarras` ASC) ,
  CONSTRAINT `fk_CodigoBarras_Item_CodigoBarras`
    FOREIGN KEY (`idCodigoBarras` )
    REFERENCES `01search`.`CodigoBarras` (`idCodigoBarras` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CodigoBarras_Item_Item`
    FOREIGN KEY (`idItem` )
    REFERENCES `01search`.`Item` (`idItem` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `01search`.`Historico_Item_Lista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Historico_Item_Lista` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Historico_Item_Lista` (
  `idLista` INT UNSIGNED NOT NULL ,
  `idItem` INT UNSIGNED NOT NULL ,
  `idEstabelecimento` INT UNSIGNED NOT NULL ,
  `versao_compra` INT NOT NULL ,
  `compraData` DATE NOT NULL ,
  `quantidade` INT NULL ,
  PRIMARY KEY (`idLista`, `idItem`, `idEstabelecimento`, `versao_compra`, `compraData`) ,
  INDEX `fk_Historico_Item_Lista_Estabelecimento1_idx` (`idEstabelecimento` ASC) ,
  CONSTRAINT `fk_Historico_Item_Lista_Item_Lista1`
    FOREIGN KEY (`idLista` , `idItem` )
    REFERENCES `01search`.`Item_Lista` (`idLista` , `idItem` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historico_Item_Lista_Estabelecimento1`
    FOREIGN KEY (`idEstabelecimento` )
    REFERENCES `01search`.`Estabelecimento` (`idEstabelecimento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `01search`.`comentario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`comentario` ;

CREATE  TABLE IF NOT EXISTS `01search`.`comentario` (
  `idcomentario` INT NOT NULL ,
  `texto` VARCHAR(255) NULL ,
  `idUsuario` INT UNSIGNED NOT NULL ,
  `idCodigoBarras` VARCHAR(11) NOT NULL ,
  PRIMARY KEY (`idcomentario`) ,
  INDEX `fk_comentario_Usuario1_idx` (`idUsuario` ASC) ,
  INDEX `fk_comentario_CodigoBarras1_idx` (`idCodigoBarras` ASC) ,
  CONSTRAINT `fk_comentario_Usuario1`
    FOREIGN KEY (`idUsuario` )
    REFERENCES `01search`.`Usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comentario_CodigoBarras1`
    FOREIGN KEY (`idCodigoBarras` )
    REFERENCES `01search`.`CodigoBarras` (`idCodigoBarras` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `01search`.`historicoPreco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`historicoPreco` ;

CREATE  TABLE IF NOT EXISTS `01search`.`historicoPreco` (
  `idCodigoBarras` VARCHAR(11) NOT NULL ,
  `idEstabelecimento` INT UNSIGNED NOT NULL ,
  `precoData` DATETIME NOT NULL ,
  `preco` DECIMAL(7,2) NULL ,
  `idUnidade` INT NOT NULL ,
  PRIMARY KEY (`idCodigoBarras`, `idEstabelecimento`) ,
  INDEX `fk_historicoPreco_Estabelecimento1_idx` (`idEstabelecimento` ASC) ,
  INDEX `fk_historicoPreco_UnidadeMedida1_idx` (`idUnidade` ASC) ,
  CONSTRAINT `fk_historicoPreco_CodigoBarras1`
    FOREIGN KEY (`idCodigoBarras` )
    REFERENCES `01search`.`CodigoBarras` (`idCodigoBarras` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_historicoPreco_Estabelecimento1`
    FOREIGN KEY (`idEstabelecimento` )
    REFERENCES `01search`.`Estabelecimento` (`idEstabelecimento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_historicoPreco_UnidadeMedida1`
    FOREIGN KEY (`idUnidade` )
    REFERENCES `01search`.`UnidadeMedida` (`idUnidade` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `01search`.`marca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`marca` ;

CREATE  TABLE IF NOT EXISTS `01search`.`marca` (
  `idMarca` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`idMarca`) )
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = latin1
PACK_KEYS = 0;


-- -----------------------------------------------------
-- Table `01search`.`departamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`departamento` ;

CREATE  TABLE IF NOT EXISTS `01search`.`departamento` (
  `idDepartamento` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`idDepartamento`) )
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1
PACK_KEYS = 0;


-- -----------------------------------------------------
-- Table `01search`.`subdepartamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`subdepartamento` ;

CREATE  TABLE IF NOT EXISTS `01search`.`subdepartamento` (
  `idSubdepartamento` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `idDepartamento` INT(10) UNSIGNED NOT NULL ,
  `nome` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`idSubdepartamento`) ,
  INDEX `fk_Subdepartamento_Departamento` (`idDepartamento` ASC) ,
  CONSTRAINT `fk_Subdepartamento_Departamento`
    FOREIGN KEY (`idDepartamento` )
    REFERENCES `01search`.`departamento` (`idDepartamento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = latin1
PACK_KEYS = 0;


-- -----------------------------------------------------
-- Table `01search`.`codigobarras`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`codigobarras` ;

CREATE  TABLE IF NOT EXISTS `01search`.`codigobarras` (
  `idCodigoBarras` VARCHAR(13) NOT NULL ,
  `idMarca` INT(10) UNSIGNED NOT NULL ,
  `idSubdepartamento` INT(10) UNSIGNED NOT NULL ,
  `nome` VARCHAR(100) NULL DEFAULT NULL ,
  `imagem` VARCHAR(255) NULL DEFAULT NULL ,
  `data_cadastro` DATETIME NULL DEFAULT NULL ,
  `data_liberacao` DATETIME NULL DEFAULT NULL ,
  `descricao` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`idCodigoBarras`) ,
  INDEX `fk_CodigoBarras_Marca` (`idMarca` ASC) ,
  INDEX `fk_CodigoBarras_Subdepartamento` (`idSubdepartamento` ASC) ,
  CONSTRAINT `fk_CodigoBarras_Marca`
    FOREIGN KEY (`idMarca` )
    REFERENCES `01search`.`marca` (`idMarca` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CodigoBarras_Subdepartamento`
    FOREIGN KEY (`idSubdepartamento` )
    REFERENCES `01search`.`subdepartamento` (`idSubdepartamento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
PACK_KEYS = 0;


-- -----------------------------------------------------
-- Table `01search`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`item` ;

CREATE  TABLE IF NOT EXISTS `01search`.`item` (
  `idItem` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(20) NULL DEFAULT NULL ,
  `periodicidade` INT(11) NULL DEFAULT NULL ,
  `ativo` TINYINT(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`idItem`) )
ENGINE = InnoDB
AUTO_INCREMENT = 41
DEFAULT CHARACTER SET = latin1
PACK_KEYS = 0;


-- -----------------------------------------------------
-- Table `01search`.`codigobarras_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`codigobarras_item` ;

CREATE  TABLE IF NOT EXISTS `01search`.`codigobarras_item` (
  `idItem` INT(10) UNSIGNED NOT NULL ,
  `idCodigoBarras` VARCHAR(13) NOT NULL ,
  PRIMARY KEY (`idItem`, `idCodigoBarras`) ,
  INDEX `fk_CodigoBarras_Item_Item` (`idItem` ASC) ,
  INDEX `fk_CodigoBarras_Item_CodigoBarras` (`idCodigoBarras` ASC) ,
  CONSTRAINT `fk_CodigoBarras_Item_CodigoBarras`
    FOREIGN KEY (`idCodigoBarras` )
    REFERENCES `01search`.`codigobarras` (`idCodigoBarras` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CodigoBarras_Item_Item`
    FOREIGN KEY (`idItem` )
    REFERENCES `01search`.`item` (`idItem` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `01search`.`unidademedida`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`unidademedida` ;

CREATE  TABLE IF NOT EXISTS `01search`.`unidademedida` (
  `idUnidade` INT(11) NOT NULL ,
  `descricao` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`idUnidade`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `01search`.`conversao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`conversao` ;

CREATE  TABLE IF NOT EXISTS `01search`.`conversao` (
  `idConversao` INT(13) NOT NULL ,
  `fator` DECIMAL(8,4) NULL DEFAULT NULL ,
  `idUnidadePrincipal` INT(11) NOT NULL ,
  `idUnidadeSecundaria` INT(11) NOT NULL ,
  PRIMARY KEY (`idConversao`) ,
  INDEX `fk_Conversao_UnidadeMedida` (`idUnidadePrincipal` ASC) ,
  INDEX `fk_Conversao_UnidadeMedida2` (`idUnidadeSecundaria` ASC) ,
  CONSTRAINT `fk_Conversao_UnidadeMedida`
    FOREIGN KEY (`idUnidadePrincipal` )
    REFERENCES `01search`.`unidademedida` (`idUnidade` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Conversao_UnidadeMedida2`
    FOREIGN KEY (`idUnidadeSecundaria` )
    REFERENCES `01search`.`unidademedida` (`idUnidade` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `01search`.`login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`login` ;

CREATE  TABLE IF NOT EXISTS `01search`.`login` (
  `idLogin` INT(11) NOT NULL ,
  `Login` VARCHAR(45) NOT NULL ,
  `senha` VARCHAR(45) NOT NULL ,
  `perfil` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idLogin`) ,
  UNIQUE INDEX `Login_UNIQUE` (`Login` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `01search`.`estabelecimento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`estabelecimento` ;

CREATE  TABLE IF NOT EXISTS `01search`.`estabelecimento` (
  `idEstabelecimento` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `cnpj` VARCHAR(45) NOT NULL ,
  `descricao` VARCHAR(45) NULL DEFAULT NULL ,
  `logo` VARCHAR(255) NULL DEFAULT NULL ,
  `nome` VARCHAR(45) NOT NULL ,
  `idLogin` INT(11) NOT NULL ,
  PRIMARY KEY (`idEstabelecimento`) ,
  UNIQUE INDEX `cnpj_UNIQUE` (`cnpj` ASC) ,
  UNIQUE INDEX `idLogin_UNIQUE` (`idLogin` ASC) ,
  INDEX `fk_Estabelecimento_Login` (`idLogin` ASC) ,
  CONSTRAINT `fk_Estabelecimento_Login`
    FOREIGN KEY (`idLogin` )
    REFERENCES `01search`.`login` (`idLogin` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1
PACK_KEYS = 0;


-- -----------------------------------------------------
-- Table `01search`.`lista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`lista` ;

CREATE  TABLE IF NOT EXISTS `01search`.`lista` (
  `idLista` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(20) NOT NULL ,
  `ativo` TINYINT(1) NULL DEFAULT NULL ,
  `versao` INT(11) NULL DEFAULT NULL ,
  `versaoCompra` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`idLista`) )
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = latin1
PACK_KEYS = 0;


-- -----------------------------------------------------
-- Table `01search`.`item_lista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`item_lista` ;

CREATE  TABLE IF NOT EXISTS `01search`.`item_lista` (
  `idLista` INT(10) UNSIGNED NOT NULL ,
  `idItem` INT(10) UNSIGNED NOT NULL ,
  `quantidade` INT(11) NOT NULL ,
  `ativo` TINYINT(1) NOT NULL ,
  `periodicidade` INT(11) NOT NULL ,
  `idUnidade` INT(11) NOT NULL ,
  PRIMARY KEY (`idLista`, `idItem`) ,
  INDEX `fk_Item_Lista_Item` (`idItem` ASC) ,
  INDEX `fk_Item_Lista_Lista` (`idLista` ASC) ,
  INDEX `fk_Item_Lista_UnidadeMedida` (`idUnidade` ASC) ,
  CONSTRAINT `fk_Item_Lista_Item`
    FOREIGN KEY (`idItem` )
    REFERENCES `01search`.`item` (`idItem` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_Lista_Lista`
    FOREIGN KEY (`idLista` )
    REFERENCES `01search`.`lista` (`idLista` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_Lista_UnidadeMedida`
    FOREIGN KEY (`idUnidade` )
    REFERENCES `01search`.`unidademedida` (`idUnidade` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
PACK_KEYS = 0;


-- -----------------------------------------------------
-- Table `01search`.`produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`produto` ;

CREATE  TABLE IF NOT EXISTS `01search`.`produto` (
  `idEstabelecimento` INT(10) UNSIGNED NOT NULL ,
  `idCodigoBarras` VARCHAR(13) NOT NULL DEFAULT '' ,
  `preco` DECIMAL(7,2) NULL DEFAULT NULL ,
  `descricao` VARCHAR(255) NULL DEFAULT NULL ,
  `localizacao` VARCHAR(100) NULL DEFAULT NULL ,
  `destaque` INT(11) NULL DEFAULT NULL ,
  `idUnidade` INT(11) NOT NULL ,
  PRIMARY KEY (`idEstabelecimento`, `idCodigoBarras`) ,
  INDEX `fk_Produto_CodigoBarras` (`idCodigoBarras` ASC) ,
  INDEX `fk_Produto_Estabelecimento` (`idEstabelecimento` ASC) ,
  INDEX `fk_Produto_UnidadeMedida` (`idUnidade` ASC) ,
  CONSTRAINT `fk_Produto_CodigoBarras`
    FOREIGN KEY (`idCodigoBarras` )
    REFERENCES `01search`.`codigobarras` (`idCodigoBarras` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Estabelecimento`
    FOREIGN KEY (`idEstabelecimento` )
    REFERENCES `01search`.`estabelecimento` (`idEstabelecimento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_UnidadeMedida`
    FOREIGN KEY (`idUnidade` )
    REFERENCES `01search`.`unidademedida` (`idUnidade` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
PACK_KEYS = 0;


-- -----------------------------------------------------
-- Table `01search`.`historico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`historico` ;

CREATE  TABLE IF NOT EXISTS `01search`.`historico` (
  `idLista` INT(10) UNSIGNED NOT NULL ,
  `idItem` INT(10) UNSIGNED NOT NULL ,
  `idCodigoBarras` VARCHAR(13) NOT NULL DEFAULT '' ,
  `idEstabelecimento` INT(10) UNSIGNED NOT NULL ,
  `versaoCompra` INT(11) NOT NULL ,
  `compraData` DATE NOT NULL ,
  `quantidade` INT(11) NULL DEFAULT NULL ,
  `preco` DECIMAL(7,2) NULL DEFAULT NULL ,
  `idUnidade` INT(11) NOT NULL ,
  PRIMARY KEY (`idLista`, `idItem`, `idCodigoBarras`, `idEstabelecimento`, `versaoCompra`, `compraData`) ,
  INDEX `fk_Historico_Produto` (`idCodigoBarras` ASC, `idEstabelecimento` ASC) ,
  INDEX `fk_Historico_Item_Lista` (`idLista` ASC, `idItem` ASC) ,
  INDEX `fk_Historico_UnidadeMedida` (`idUnidade` ASC) ,
  CONSTRAINT `fk_Historico_Item_Lista`
    FOREIGN KEY (`idLista` , `idItem` )
    REFERENCES `01search`.`item_lista` (`idLista` , `idItem` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historico_Produto`
    FOREIGN KEY (`idCodigoBarras` , `idEstabelecimento` )
    REFERENCES `01search`.`produto` (`idCodigoBarras` , `idEstabelecimento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historico_UnidadeMedida`
    FOREIGN KEY (`idUnidade` )
    REFERENCES `01search`.`unidademedida` (`idUnidade` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `01search`.`historico_item_lista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`historico_item_lista` ;

CREATE  TABLE IF NOT EXISTS `01search`.`historico_item_lista` (
  `idLista` INT(10) UNSIGNED NOT NULL ,
  `idItem` INT(10) UNSIGNED NOT NULL ,
  `idEstabelecimento` INT(10) UNSIGNED NOT NULL ,
  `compraData` DATE NOT NULL ,
  `quantidade` INT(11) NULL DEFAULT NULL ,
  `versaoCompra` INT(11) NOT NULL ,
  PRIMARY KEY (`idLista`, `idItem`, `idEstabelecimento`, `compraData`, `versaoCompra`) ,
  INDEX `fk_Historico_Item_Lista_Estabelecimento1_idx` (`idEstabelecimento` ASC) ,
  CONSTRAINT `fk_Historico_Item_Lista_Estabelecimento1`
    FOREIGN KEY (`idEstabelecimento` )
    REFERENCES `01search`.`estabelecimento` (`idEstabelecimento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historico_Item_Lista_Item_Lista1`
    FOREIGN KEY (`idLista` , `idItem` )
    REFERENCES `01search`.`item_lista` (`idLista` , `idItem` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `01search`.`historicopreco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`historicopreco` ;

CREATE  TABLE IF NOT EXISTS `01search`.`historicopreco` (
  `idCodigoBarras` VARCHAR(13) NOT NULL ,
  `idEstabelecimento` INT UNSIGNED NOT NULL ,
  `precoData` DATETIME NOT NULL ,
  `preco` DECIMAL(7,2) NULL DEFAULT NULL ,
  `idUnidade` INT(11) NOT NULL ,
  PRIMARY KEY (`idCodigoBarras`, `idEstabelecimento`, `precoData`) ,
  INDEX `FK4702483F61B9F53A` (`idUnidade` ASC) ,
  INDEX `fk_historicopreco_codigobarras1_idx` (`idCodigoBarras` ASC) ,
  INDEX `fk_historicopreco_estabelecimento1_idx` (`idEstabelecimento` ASC) ,
  CONSTRAINT `FK4702483F61B9F53A`
    FOREIGN KEY (`idUnidade` )
    REFERENCES `01search`.`unidademedida` (`idUnidade` ),
  CONSTRAINT `fk_historicopreco_codigobarras1`
    FOREIGN KEY (`idCodigoBarras` )
    REFERENCES `01search`.`codigobarras` (`idCodigoBarras` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_historicopreco_estabelecimento1`
    FOREIGN KEY (`idEstabelecimento` )
    REFERENCES `01search`.`estabelecimento` (`idEstabelecimento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `01search`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`usuario` ;

CREATE  TABLE IF NOT EXISTS `01search`.`usuario` (
  `idUsuario` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(60) NULL DEFAULT NULL ,
  `idLogin` INT(11) NOT NULL ,
  PRIMARY KEY (`idUsuario`) ,
  INDEX `fk_Usuario_Login` (`idLogin` ASC) ,
  CONSTRAINT `fk_Usuario_Login`
    FOREIGN KEY (`idLogin` )
    REFERENCES `01search`.`login` (`idLogin` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1
PACK_KEYS = 0;


-- -----------------------------------------------------
-- Table `01search`.`lista_usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`lista_usuario` ;

CREATE  TABLE IF NOT EXISTS `01search`.`lista_usuario` (
  `idLista` INT(10) UNSIGNED NOT NULL ,
  `idUsuario` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`idLista`, `idUsuario`) ,
  INDEX `fk_Lista_Usuario_Usuario` (`idUsuario` ASC) ,
  INDEX `fk_Lista_Usuario_Lista` (`idLista` ASC) ,
  CONSTRAINT `fk_Lista_Usuario_Lista`
    FOREIGN KEY (`idLista` )
    REFERENCES `01search`.`lista` (`idLista` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Lista_Usuario_Usuario`
    FOREIGN KEY (`idUsuario` )
    REFERENCES `01search`.`usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `01search`.`usuario_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`usuario_item` ;

CREATE  TABLE IF NOT EXISTS `01search`.`usuario_item` (
  `idUsuario` INT(10) UNSIGNED NOT NULL ,
  `idItem` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`idUsuario`, `idItem`) ,
  INDEX `fk_Usuario_Item_Item` (`idItem` ASC) ,
  INDEX `fk_Usuario_Item_Usuario` (`idUsuario` ASC) ,
  CONSTRAINT `fk_Usuario_Item_Item`
    FOREIGN KEY (`idItem` )
    REFERENCES `01search`.`item` (`idItem` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_Item_Usuario`
    FOREIGN KEY (`idUsuario` )
    REFERENCES `01search`.`usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `01search`.`Comentario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Comentario` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Comentario` (
  `idComentario` INT NOT NULL ,
  `texto` VARCHAR(225) NULL ,
  `idUsuario` INT(10) UNSIGNED NOT NULL ,
  `idCodigoBarras` VARCHAR(13) NOT NULL ,
  PRIMARY KEY (`idComentario`) ,
  INDEX `fk_Comentario_usuario1_idx` (`idUsuario` ASC) ,
  INDEX `fk_Comentario_codigobarras1_idx` (`idCodigoBarras` ASC) ,
  CONSTRAINT `fk_Comentario_usuario1`
    FOREIGN KEY (`idUsuario` )
    REFERENCES `01search`.`usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comentario_codigobarras1`
    FOREIGN KEY (`idCodigoBarras` )
    REFERENCES `01search`.`codigobarras` (`idCodigoBarras` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- function getItensHistorico
-- -----------------------------------------------------

USE `01search`;
DROP function IF EXISTS `01search`.`getItensHistorico`;

DELIMITER $$
USE `01search`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `getItensHistorico`(lista int, item int) RETURNS varchar(50) CHARSET latin1
    DETERMINISTIC
BEGIN
  DECLARE done INT DEFAULT 0;
  
  DECLARE periodicidade INT(11);
  
  DECLARE qtdCorrente int DEFAULT 0;
  DECLARE dtCorrente date;
  
  DECLARE qtdTotal int DEFAULT 0;
  DECLARE dtPrazo date;
  DECLARE dtInicial date;

  DECLARE cur1 CURSOR FOR 
    SELECT a.*,  b.periodicidade
    FROM (SELECT h.quantidade quantidade, h.compraData compraData          
          FROM  historico h
          WHERE h.idLista = lista
          AND   h.idItem = item 
          AND   h.quantidade IS NOT NULL
          AND   h.compraData IS NOT NULL
          
          UNION
          
          SELECT hil.quantidade quantidade, hil.compraData compraData
          FROM  historico_item_lista hil
          WHERE hil.idLista = lista
          AND   hil.idItem = item
          AND   hil.quantidade IS NOT NULL
          AND   hil.compraData IS NOT NULL) a,
          
          (select *
           from item_lista
           where idLista = lista
           and idItem = item
           ) b
    ORDER BY compraData ASC;
                         
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
  
  OPEN cur1;

  REPEAT
    FETCH cur1 INTO qtdCorrente, dtCorrente, periodicidade;
    IF NOT done THEN
     if (dtPrazo - dtCorrente) > 0 then
        set dtPrazo = dtPrazo + interval (periodicidade * qtdCorrente) day;
        set qtdTotal = qtdTotal + qtdCorrente;
      else
         set dtInicial = dtCorrente;
         set dtPrazo = dtInicial + interval (periodicidade * qtdCorrente) day;
         set qtdTotal = qtdCorrente;
      end if;
       
    END IF;
  UNTIL done END REPEAT;
  CLOSE cur1;
  
  if (CURDATE() - dtPrazo) > 0 then
    set dtPrazo = '';
    set qtdTotal = 0;
  else    
	set qtdTotal = qtdTotal - ((TO_DAYS(CURDATE() - interval 1 day) - TO_DAYS(dtInicial))/ periodicidade);
  end if;
  
   RETURN concat(lpad(dtPrazo, 10, '0'),"-",qtdTotal);
END$$

DELIMITER ;
USE `01search`;

DELIMITER $$

USE `01search`$$
DROP TRIGGER IF EXISTS `01search`.`insertHistorico` $$
USE `01search`$$


CREATE DEFINER=`root`@`localhost` 
TRIGGER `01search`.`insertHistorico` 
AFTER INSERT ON `01search`.`Produto` 
FOR EACH ROW BEGIN IF new.preco 
IS NOT NULL THEN INSERT INTO `01search`.historicoPreco 
SET idCodigoBarras = NEW.idCodigoBarras, 
idEstabelecimento = NEW.idEstabelecimento, 
precoData = NOW(), preco = NEW.preco, 
idUnidade = NEW.idUnidade; END IF; 
END$$


USE `01search`$$
DROP TRIGGER IF EXISTS `01search`.`updateHistorico` $$
USE `01search`$$


CREATE DEFINER=`root`@`localhost` 
TRIGGER `01search`.`updateHistorico` 
AFTER UPDATE ON `01search`.`Produto` 
FOR EACH ROW BEGIN IF (NEW.preco IS NOT NULL) 
AND (NEW.preco <> OLD.preco) THEN INSERT INTO historicoPreco 
SET idCodigoBarras = NEW.idCodigoBarras, 
idEstabelecimento = NEW.idEstabelecimento, 
precoData = NOW(), preco = NEW.preco, 
idUnidade = NEW.idUnidade; END IF; 
END$$


DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
