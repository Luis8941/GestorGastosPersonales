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

