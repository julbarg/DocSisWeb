CREATE TABLE `user_configuration` (
  `user` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`user`),
  UNIQUE INDEX `user_UNIQUE` (`user` ASC));
  
CREATE TABLE `docsis_audit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vpn_identifier` varchar(45) NOT NULL,
  `s_vlan` varchar(45) NOT NULL,
  `c_vlan` varchar(45) NOT NULL,
  `ancho_subida` varchar(45) DEFAULT NULL,
  `ancho_bajada` varchar(45) DEFAULT NULL,
  `mac_address` varchar(45) NOT NULL,
  `nombre_archivo` varchar(45) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id`)
);
