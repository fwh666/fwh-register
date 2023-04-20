CREATE DATABASE `fwh_register` CHARACTER SET `utf8mb4`;

CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `username` varchar(50) NOT NULL,
                        `password` varchar(100) NOT NULL,
                        `created_time` timestamp NULL DEFAULT NULL,
                        `modified_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

## 账号授权
CREATE USER 'fwh_register_owner'@'%' IDENTIFIED BY 'vHdED1aDzbP0dPETwd5a7w==';
GRANT INSERT, UPDATE, SELECT ON fwh_register.*  TO 'fwh_register_owner'@'%';
