CREATE TABLE `rol` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `nombre_rol` varchar(50) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
);

CREATE TABLE `opciones_menu` (
  `id_opcion` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_opcion`)
);

CREATE TABLE `categoria_gastos` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `nombre_categoria` varchar(100) NOT NULL,
  PRIMARY KEY (`id_categoria`)
);

CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(100) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `id_rol` int NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `usuario_rol_FK` (`id_rol`),
  CONSTRAINT `usuario_rol_FK` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`)
);

CREATE TABLE `cuenta` (
  `id_cuenta` int NOT NULL AUTO_INCREMENT,
  `saldo` float NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_cuenta`),
  KEY `cuenta_usuario_FK` (`id_usuario`),
  CONSTRAINT `cuenta_usuario_FK` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
);

CREATE TABLE `gasto` (
  `id_gasto` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) DEFAULT NULL,
  `monto` float NOT NULL,
  `fecha_registro` date NOT NULL,
  `id_usuario` int NOT NULL,
  `id_categoria` int NOT NULL,
  PRIMARY KEY (`id_gasto`),
  KEY `gastos_usuario_FK` (`id_usuario`),
  KEY `gastos_categoria_gastos_FK` (`id_categoria`),
  CONSTRAINT `gastos_categoria_gastos_FK` FOREIGN KEY (`id_categoria`) REFERENCES `categoria_gastos` (`id_categoria`),
  CONSTRAINT `gastos_usuario_FK` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
);

CREATE TABLE `objetivo_gastos` (
  `id_objetivo` int NOT NULL AUTO_INCREMENT,
  `monto_objetivo` float NOT NULL,
  `tipo_objetivo` varchar(100) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_objetivo`),
  KEY `objetivo_gastos_usuario_FK` (`id_usuario`),
  CONSTRAINT `objetivo_gastos_usuario_FK` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
);

CREATE TABLE `recordatorio_gastos` (
  `id_recordatorio` int NOT NULL AUTO_INCREMENT,
  `estado` tinyint(1) NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `id_gasto` int NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_recordatorio`),
  KEY `recordatorio_gastos_usuario_FK` (`id_usuario`),
  KEY `recordatorio_gastos_gasto_FK` (`id_gasto`),
  CONSTRAINT `recordatorio_gastos_gasto_FK` FOREIGN KEY (`id_gasto`) REFERENCES `gasto` (`id_gasto`),
  CONSTRAINT `recordatorio_gastos_usuario_FK` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
);

CREATE TABLE `rol_opciones_menu` (
  `id_rol` int NOT NULL,
  `id_opcion` int NOT NULL,
  `id_rol_menu` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_rol_menu`),
  KEY `rol_opciones_menu_rol_FK` (`id_rol`),
  KEY `rol_opciones_menu_opciones_menu_FK` (`id_opcion`),
  CONSTRAINT `rol_opciones_menu_opciones_menu_FK` FOREIGN KEY (`id_opcion`) REFERENCES `opciones_menu` (`id_opcion`),
  CONSTRAINT `rol_opciones_menu_rol_FK` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`)
);

CREATE TABLE `ahorro` (
  `id_ahorro` int NOT NULL AUTO_INCREMENT,
  `monto_ahorro` float DEFAULT NULL,
  `id_cuenta` int NOT NULL,
  PRIMARY KEY (`id_ahorro`),
  KEY `ahorro_cuenta_FK` (`id_cuenta`),
  CONSTRAINT `ahorro_cuenta_FK` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id_cuenta`)
);

