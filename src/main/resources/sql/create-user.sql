-- Drop user first if they exist
DROP USER if exists 'ksmirnov'@'localhost' ;

-- Now create user with prop privileges
CREATE USER 'ksmirnov'@'localhost' IDENTIFIED BY 'ksmirnov';

GRANT ALL PRIVILEGES ON * . * TO 'ksmirnov'@'localhost';
