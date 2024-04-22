-- Table structure for table `rol`
DROP TABLE IF EXISTS `rol`;
CREATE TABLE `rol` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `nombre_rol` varchar(50) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
);

-- Table structure for table `opciones_menu`
DROP TABLE IF EXISTS `opciones_menu`;
CREATE TABLE `opciones_menu` (
  `id_opcion` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_opcion`)
);

-- Table structure for table `usuario`
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(100) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `id_rol` int NOT NULL,
  PRIMARY KEY (`id_usuario`),
  CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`)
);

-- Table structure for table `categoria_gastos`
DROP TABLE IF EXISTS `categoria_gastos`;
CREATE TABLE `categoria_gastos` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `nombre_categoria` varchar(100) NOT NULL, 
  PRIMARY KEY (`id_categoria`),
  CONSTRAINT `fk_categoria_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
);

-- Table structure for table `cuenta`
DROP TABLE IF EXISTS `cuenta`;
CREATE TABLE `cuenta` (
  `id_cuenta` int NOT NULL AUTO_INCREMENT,
  `saldo` float NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_cuenta`),
  CONSTRAINT `fk_cuenta_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
);

-- Table structure for table `gasto`
DROP TABLE IF EXISTS `gasto`;
CREATE TABLE `gasto` (
  `id_gasto` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) DEFAULT NULL,
  `monto` float NOT NULL,
  `fecha_registro` date NOT NULL,
  `id_usuario` int NOT NULL,
  `id_categoria` int NOT NULL,
  PRIMARY KEY (`id_gasto`),
  CONSTRAINT `fk_gasto_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `fk_gasto_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria_gastos` (`id_categoria`)
);

-- Table structure for table `recordatorio_gastos`
DROP TABLE IF EXISTS `recordatorio_gastos`;
CREATE TABLE `recordatorio_gastos` (
  `id_recordatorio` int NOT NULL AUTO_INCREMENT,
  `estado` tinyint(1) NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `id_gasto` int NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_recordatorio`),
  CONSTRAINT `fk_recordatorio_gastos_gasto` FOREIGN KEY (`id_gasto`) REFERENCES `gasto` (`id_gasto`),
  CONSTRAINT `fk_recordatorio_gastos_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
);

-- Table structure for table `objetivo_gastos`
DROP TABLE IF EXISTS `objetivo_gastos`;
CREATE TABLE `objetivo_gastos` (
  `id_objetivo` int NOT NULL AUTO_INCREMENT,
  `monto_objetivo` float NOT NULL,
  `tipo_objetivo` varchar(100) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_objetivo`),
  CONSTRAINT `fk_objetivo_gastos_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
);

-- Table structure for table `ahorro`
DROP TABLE IF EXISTS `ahorro`;
CREATE TABLE `ahorro` (
  `id_ahorro` int NOT NULL AUTO_INCREMENT,
  `monto_ahorro` float DEFAULT NULL,
  `id_cuenta` int NOT NULL,
  PRIMARY KEY (`id_ahorro`),
  CONSTRAINT `fk_ahorro_cuenta` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id_cuenta`)
);


-- INSERCION DE DATOS
# Tabla rol 

INSERT INTO `ahorro_app`.`rol` (`nombre_rol`, `descripcion`) VALUES ('admin', 'Administrador de la app'); 

INSERT INTO `ahorro_app`.`rol` (`nombre_rol`, `descripcion`) VALUES ('mobile_user', 'Usuario de la app'); 

  

# Tabla opciones_menu 

INSERT INTO `ahorro_app`.`opciones_menu` (`descripcion`) VALUES ('Pantalla de login'); 

INSERT INTO `ahorro_app`.`opciones_menu` (`descripcion`) VALUES ('Pantalla de inicio'); 

INSERT INTO `ahorro_app`.`opciones_menu` (`descripcion`) VALUES ('Pantalla de ingreso de objetivos'); 

INSERT INTO `ahorro_app`.`opciones_menu` (`descripcion`) VALUES ('Pantalla de estadistica'); 

INSERT INTO `ahorro_app`.`opciones_menu` (`descripcion`) VALUES ('Pantalla de recordatorio gastos'); 

INSERT INTO `ahorro_app`.`opciones_menu` (`descripcion`) VALUES ('Pantalla de ver usuario'); 

INSERT INTO `ahorro_app`.`opciones_menu` (`descripcion`) VALUES ('Pantalla administrar'); 

  

# Tabla usuario 

INSERT INTO `ahorro_app`.`usuario` (`nombre_usuario`, `password`, `estado`, `id_rol`) VALUES ('amandapineda', '12345', '1', '2'); 

INSERT INTO `ahorro_app`.`usuario` (`nombre_usuario`, `password`, `estado`, `id_rol`) VALUES ('admin1', '54321', '1', '1'); 

INSERT INTO `ahorro_app`.`usuario` (`nombre_usuario`, `password`, `estado`, `id_rol`) VALUES ('carloslobato', '12345', '1', '2'); 

INSERT INTO `ahorro_app`.`usuario` (`nombre_usuario`, `password`, `estado`, `id_rol`) VALUES ('luismenjivar', '12345', '1', '2'); 

  

# Tabla cuenta 

INSERT INTO `ahorro_app`.`cuenta` (`saldo`, `id_usuario`) VALUES ('500', '2'); 

INSERT INTO `ahorro_app`.`cuenta` (`saldo`, `id_usuario`) VALUES ('500', '3'); 

INSERT INTO `ahorro_app`.`cuenta` (`saldo`, `id_usuario`) VALUES ('500', '4'); 

  

# Tabla ahorro 

INSERT INTO `ahorro_app`.`ahorro` (`monto_ahorro`, `id_cuenta`) VALUES ('150', '1'); 

INSERT INTO `ahorro_app`.`ahorro` (`monto_ahorro`, `id_cuenta`) VALUES ('150', '2'); 

INSERT INTO `ahorro_app`.`ahorro` (`monto_ahorro`, `id_cuenta`) VALUES ('150', '3'); 

  

# Tabla objetivo_gastos 

INSERT INTO `ahorro_app`.`objetivo_gastos` (`monto_objetivo`, `tipo_objetivo`, `fecha_inicio`, `fecha_fin`, `id_usuario`) VALUES ('50', 'Cafè', STR_TO_DATE('2024-05-01','%Y-%m-%d'), STR_TO_DATE('2024-05-15','%Y-%m-%d'), '2'); 

INSERT INTO `ahorro_app`.`objetivo_gastos` (`monto_objetivo`, `tipo_objetivo`, `fecha_inicio`, `fecha_fin`, `id_usuario`) VALUES ('80', 'Gasolina', STR_TO_DATE('2024-05-01','%Y-%m-%d'), STR_TO_DATE('2024-05-15','%Y-%m-%d'), '3'); 

INSERT INTO `ahorro_app`.`objetivo_gastos` (`monto_objetivo`, `tipo_objetivo`, `fecha_inicio`, `fecha_fin`, `id_usuario`) VALUES ('45', 'Golosinas', STR_TO_DATE('2024-05-01','%Y-%m-%d'), STR_TO_DATE('2024-05-15','%Y-%m-%d'), '4'); 

  

# Tabla categoria_gastos 

INSERT INTO `ahorro_app`.`categoria_gastos` (`nombre_categoria`,`id_usuario`) VALUES ('Alimentacion','2');  

INSERT INTO `ahorro_app`.`categoria_gastos` (`nombre_categoria`,`id_usuario`) VALUES ('Casa','2');  

INSERT INTO `ahorro_app`.`categoria_gastos` (`nombre_categoria`,`id_usuario`) VALUES ('Transporte','2');  

INSERT INTO `ahorro_app`.`categoria_gastos` (`nombre_categoria`,`id_usuario`) VALUES ('Entretenimiento','2');  

INSERT INTO `ahorro_app`.`categoria_gastos` (`nombre_categoria`,`id_usuario`) VALUES ('Salud','2');  

INSERT INTO `ahorro_app`.`categoria_gastos` (`nombre_categoria`,`id_usuario`) VALUES ('Cuentas y pagos','2'); 

  

# Tabla gasto 

INSERT INTO `ahorro_app`.`gasto` (`descripcion`, `monto`, `fecha_registro`, `id_usuario`, `id_categoria`) VALUES ('Pago de luz', '35', STR_TO_DATE('2024-05-15','%Y-%m-%d'), '2', '2'); 

INSERT INTO `ahorro_app`.`gasto` (`descripcion`, `monto`, `fecha_registro`, `id_usuario`, `id_categoria`) VALUES ('Viaje a la playa', '50', STR_TO_DATE('2024-05-15','%Y-%m-%d'), '3', '4'); 

INSERT INTO `ahorro_app`.`gasto` (`descripcion`, `monto`, `fecha_registro`, `id_usuario`, `id_categoria`) VALUES ('Compra supermercado', '75', STR_TO_DATE('2024-05-15','%Y-%m-%d'), '4', '1'); 

  

 

# Tabla recordatorio_gastos 

INSERT INTO `ahorro_app`.`recordatorio_gastos` (`estado`, `fecha_vencimiento`, `id_gasto`, `id_usuario`) VALUES ('1', STR_TO_DATE('2024-05-15','%Y-%m-%d'), '1', '2'); 

INSERT INTO `ahorro_app`.`recordatorio_gastos` (`estado`, `fecha_vencimiento`, `id_gasto`, `id_usuario`) VALUES ('1', STR_TO_DATE('2024-05-15','%Y-%m-%d'), '2', '3'); 

INSERT INTO `ahorro_app`.`recordatorio_gastos` (`estado`, `fecha_vencimiento`, `id_gasto`, `id_usuario`) VALUES ('1', STR_TO_DATE('2024-05-15','%Y-%m-%d'), '3', '4'); 

