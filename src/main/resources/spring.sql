CREATE TABLE `t_shares` (
  `s_code` varchar(10) NOT NULL,
  `s_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `prefix` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`s_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `t_shares_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `s_date` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `s_time` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `s_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `s_code` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `jrkp` double(10,3) DEFAULT NULL,
  `zrsp` double(10,3) DEFAULT NULL,
  `dqjg` double(10,3) DEFAULT NULL,
  `zd` double(10,3) DEFAULT NULL,
  `zdf` double(10,3) DEFAULT NULL,
  `jrzg` double(10,3) DEFAULT NULL,
  `jrzd` double(10,3) DEFAULT NULL,
  `jrbd` double(10,3) DEFAULT NULL,
  `jrbf` double(10,3) DEFAULT NULL,
  `cjsl` int(11) DEFAULT NULL,
  `cjje` double(20,3) DEFAULT NULL,
  `jrsp` double(10,3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;