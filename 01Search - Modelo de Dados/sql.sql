SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `01search` ;
CREATE SCHEMA IF NOT EXISTS `01search` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
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


INSERT INTO `login` (`idLogin`, `Login`, `perfil`, `senha`) VALUES
(1, 'shaulin', '1', 'querodoboldo'),
(2, 'macarrao', '2', 'gostomaisquelasanha');


-- -----------------------------------------------------
-- Table `01search`.`Lista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Lista` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Lista` (
  `idLista` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(20) NOT NULL ,
  `ativo` TINYINT(1) NULL ,
  `versao` INT NULL ,
  PRIMARY KEY (`idLista`) )
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

INSERT INTO `Lista` (`idLista`, `nome`, `ativo`, `versao`) VALUES
(1, 'lista', 1, 0),
(2, 'lista2', 1, 0),
(3, 'lsita3', 1, 0);

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


INSERT INTO `usuario` (`idUsuario`, `nome`, `idLogin`) VALUES
(1, 'fe', 1),
(2, 'fernand', 2),
(3, 'furles', 1);


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

	
INSERT INTO `Lista_Usuario` (`idLista`, `idUsuario`) VALUES
(1, 1),
(2, 1),
(3, 1);
	

-- -----------------------------------------------------
-- Table `01search`.`Item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Item` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Item` (
  `idItem` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(20) NULL ,
  `periodicidade` INT NULL ,
  `validade` DATE NULL ,
  PRIMARY KEY (`idItem`) )
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;


INSERT INTO `item` (`idItem`, `nome`, `periodicidade`, `validade`) VALUES
(1, 'item', 0, '2012-07-08'),
(2, 'itensss', NULL, NULL),
(3, 'itensss', NULL, NULL),
(4, 'ds', NULL, NULL),
(5, 'fhl', NULL, NULL),
(6, 'fhl', NULL, NULL),
(7, 'ddg', NULL, NULL),
(8, 'swsa', NULL, NULL);


-- -----------------------------------------------------
-- Table `01search`.`UnidadeMedida`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`UnidadeMedida` ;

CREATE  TABLE IF NOT EXISTS `01search`.`UnidadeMedida` (
  `idUnidade` INT NOT NULL ,
  `descricao` VARCHAR(45) NULL ,
  PRIMARY KEY (`idUnidade`) )
ENGINE = InnoDB;


INSERT INTO `unidademedida` (`idUnidade`, `descricao`) VALUES
(1, 'primeira medida');


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


INSERT INTO `marca` (`idMarca`, `nome`) VALUES
(1, 'Nissin'),
(2, 'Assolan'),
(3, 'Nutry'),
(4, 'Quaker'),
(5, 'Amacoco'),
(6, 'Minalice'),
(7, 'Minalba'),
(8, 'Nestle'),
(9, 'Unilever'),
(10, 'Kellogg''s'),
(11, 'Gillette');


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


INSERT INTO `departamento` (`idDepartamento`, `nome`) VALUES
(1, 'Limpeza'),
(2, 'Higiene'),
(3, 'Mercearia'),
(4, 'Bebidas');


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


INSERT INTO `subdepartamento` (`idSubdepartamento`, `nome`, `idDepartamento`) VALUES
(1, 'Esponja de Aço', 1),
(2, 'Aparelho de Barbear', 2),
(3, 'Shampoo', 2),
(6, 'Macarrão Instantâneo', 3),
(7, 'Aveia', 3),
(8, 'Biscoito', 3),
(9, 'Cereal', 3),
(10, 'Barra de Cereal', 3),
(11, 'Água Mineral', 4),
(12, 'Refresco', 4),
(13, 'Água de Coco', 4),
(15, 'Aparelho de Barbear', 2);


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


INSERT INTO `codigobarras` (`idCodigoBarras`, `data_cadastro`, `data_liberacao`, `descricao`, `imagem`, `nome`, `idMarca`, `idSubdepartamento`) VALUES
('75010092240', '2012-04-26 22:00:49', NULL, 'Aparelho de Barbear. 2 unidades.', '7501009224082.jpg', 'Prestobarba', 11, 2),
('78910000219', '2012-04-26 22:00:49', NULL, 'Biscoito Recheado Sabor Chocolate Alpino.', '7891000021934.jpg', 'Passatempo', 8, 8),
('78910000233', '2012-04-26 22:00:49', NULL, 'Biscoito Sabor Chocolate com Recheio Baunilha.', '78910000233808.jpg', 'Negresco', 8, 8),
('78910000862', '2012-04-26 22:00:49', NULL, 'O Verdadeiro Biscoito do Programador!', '7891000086216.jpg', 'Negresco Eclipse', 8, 8),
('78910790010', '2012-04-26 22:00:49', NULL, 'Macarrão Instantâneo Turma da Mônica Sabor Galinha.', '7891079001011.jpg', 'T. da Mônica Sabor Galinha', 1, 6),
('78911500042', '2012-04-26 22:00:49', NULL, 'Shampoo Anticaspa Men Queda Control.', '7891150004207.jpg', 'Clear Anticaspa', 9, 3),
('78913310026', '2012-04-26 22:00:49', NULL, 'Barra de Cereais Nutry, Sabor Banana, Leve 4 pague menos.', '7891331002664.jpg', 'Barra de Cereais Banana', 3, 10),
('78943216111', '2012-04-26 22:00:49', NULL, 'Aveia em Flocos Quaker, Ideal para vitaminas, frutas e iorgutes.', '7894321611136.jpg', 'Aveia em Flocos', 4, 7),
('78960040025', '2012-04-26 22:00:49', NULL, 'Flocos de Milho com Açúcar. 300g.', '7896004002538.jpg', 'Sucrilhos', 10, 9),
('78960658800', '2012-04-26 22:00:49', NULL, 'Água Mineral Natural Minalba, Fonte Água Santa.', '7896065880069.jpg', 'Água Mineral Minalba', 7, 11),
('78960901227', '2012-04-26 22:00:49', NULL, 'Esponja de Lã de Aço Assolan, Mais Macia e Eficiente.', '7896090122707.jpg', 'Esponja de Lã de Aço', 2, 1),
('78963261002', '2012-04-26 22:00:49', NULL, 'Refresco Misto de Guaraná e Açaí com Aroma de Açaí Adoçado.', '7896326100219.jpg', 'Guara Viton', 6, 12),
('78968280000', '2012-04-26 22:00:49', NULL, 'Água de Coco Esterilizada. 200ml.', '7896828000017.jpg', 'Kero Coco', 5, 13);


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


INSERT INTO `estabelecimento` (`idEstabelecimento`, `cnpj`, `descricao`, `logo`, `nome`, `idLogin`) VALUES
(1, '587385928478390', 'A Cottons Buddy é uma empresa destinada a ofe', 'http://www.grupopaodeacucar.com.br/data/files/8A1784E92915323C01291E4BE295260E/Extra_Hiper_gd.gif', 'Cottons Buddy Higiene Pessoal', 1),
(2, '76849304612954', 'Fundada em 1999, a Miojos Foods tem produtos ', 'http://a0.twimg.com/profile_images/1554830272/300x250_Walmart_reasonably_small.jpg', 'Miojos Foods', 2);


-- -----------------------------------------------------
-- Table `01search`.`Produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Produto` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Produto` (
  `idEstabelecimento` INT UNSIGNED NOT NULL ,
  `idCodigoBarras` VARCHAR(11) NOT NULL ,
  `preco` DECIMAL(7,2) NULL ,
  `descricao` VARCHAR(255) NULL ,
  `localizacao` VARCHAR(100) NULL ,
  `destaque` INT NULL ,
  `idUnidade` INT NOT NULL ,
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


INSERT INTO `produto` (`idCodigoBarras`, `idEstabelecimento`, `descricao`, `destaque`, `localizacao`, `preco`, `idUnidade`) VALUES
('75010092240', 2, 'Descricao do produto 9', 4, 'corredor 9', '9.00', 1),
('78910000233', 2, 'Descricao do produto 7', 1, 'corredor 7', '7.00', 1),
('78910000862', 2, 'Descricao do produto 6', 1, 'corredor 6', '6.00', 1),
('78910790010', 2, 'Descricao do produto 10', 5, 'corredor 10', '10.00', 1),
('78911500042', 2, 'Descricao do produto 8', 3, 'corredor 8', '8.00', 1),
('78913310026', 1, 'Descricao do produto 1', 1, 'corredor 1', '1.00', 1),
('78960040025', 1, 'Descricao do produto 2', 2, 'corredor 2', '2.00', 1),
('78960658800', 1, 'Descricao do produto 3', 3, 'corredor 3', '3.00', 1),
('78963261002', 1, 'Descricao do produto 4', 4, 'corredor 4', '4.00', 1),
('78968280000', 1, 'Descricao do produto 5', 5, 'corredor 5', '5.00', 1);


-- -----------------------------------------------------
-- Table `01search`.`Historico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Historico` ;

CREATE  TABLE IF NOT EXISTS `01search`.`Historico` (
  `idLista` INT UNSIGNED NOT NULL ,
  `idItem` INT UNSIGNED NOT NULL ,
  `idCodigoBarras` VARCHAR(11) NOT NULL ,
  `idEstabelecimento` INT UNSIGNED NOT NULL ,
  `compraData` DATETIME NOT NULL ,
  `quantidade` INT NULL ,
  `preco` DECIMAL(7,2) NULL ,
  `idUnidade` INT NOT NULL ,
  PRIMARY KEY (`idLista`, `idItem`, `idCodigoBarras`, `idEstabelecimento`, `compraData`) ,
  INDEX `fk_Historico_Produto` (`idCodigoBarras` ASC, `idEstabelecimento` ASC) ,
  INDEX `fk_Historico_Item_Lista` (`idLista` ASC, `idItem` ASC) ,
  INDEX `fk_Historico_UnidadeMedida` (`idUnidade` ASC) ,
  CONSTRAINT `fk_Historico_Item_Lista`
    FOREIGN KEY (`idLista` , `idItem` )
    REFERENCES `01search`.`Item_Lista` (`idLista` , `idItem` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historico_Produto`
    FOREIGN KEY (`idCodigoBarras` )
    REFERENCES `01search`.`Produto` (`idCodigoBarras` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historico_UnidadeMedida`
    FOREIGN KEY (`idUnidade` )
    REFERENCES `01search`.`UnidadeMedida` (`idUnidade` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

	
-- ----------------------------------------------------
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

	
INSERT INTO `Usuario_Item` (`idUsuario`, `idItem`) VALUES
(1, 1),
(1, 2),
(1, 3);
	
	
-- -----------------------------------------------------
-- Table `01search`.`CodigoBarras_Item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `01search`.`Item_CodigoBarras` ;

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
	
	
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
