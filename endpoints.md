# Pc Part Picker API

### Login
**POST** : http://localhost:8080/auth/login - Logs in the user provided in the body

## User

#### GET : http://localhost:8080/users - Returns a list of all users in the database
###### GET : http://localhost:8080/users/user - Returns the user "user" from the database

#### POST : http://localhost:8080/users - Creates a new user in the database using the body of the request
###### PUT : http://localhost:8080/users/test - Updates the "test" user in the database using the body of the request
#### DELETE : http://localhost:8080/users/test - Deletes the 'test' user from the database
###### POST : http://localhost:8080/users/test/roles - Creates a role with the body of the request to the "test" user
#### DELETE : http://localhost:8080/users/test/roles/TEST_ROLE - Deletes the role "TEST_ROLE" from user "test" in the database

## Cpu Cooler
#### GET : http://localhost:8080/cpuCooler - Returns a list of all cpu coolers in the database
###### POST : http://localhost:8080/cpuCooler - Creates a cpu cooler in the database using the body of the request
#### PUT : http://localhost:8080/cpuCooler/23 - Updates the cpu cooler with id "23" from the database using the body of the request
###### DELETE : http://localhost:8080/cpuCooler/23 - Deletes the cpu cooler with id "23" from the database

## Fans
#### GET : http://localhost:8080/fans - Returns a list of all fans in the database
###### POST : http://localhost:8080/fans - Creates a fan in the database using the body of the request
#### PUT : http://localhost:8080/fans/23 - Updates the fan with id "23" from the database using the body of the request
###### DELETE : http://localhost:8080/fans/23 - Deletes the fan with id "23" from the database

#### GET : http://localhost:8080/graphicsCards - Returns a list of all graphics cards in the database
###### POST : http://localhost:8080/graphicsCards - Creates a graphics card in the database using the body of the request
#### PUT : http://localhost:8080/graphicsCards/22 - Updates the graphics card with id "22" from the database using the body of the request
###### DELETE : http://localhost:8080/graphicsCards/22 - Deletes the graphics card with id "22" from the database

## Motherboards
#### GET : http://localhost:8080/motherboards - Returns a list of all motherboards in the database
###### POST : http://localhost:8080/motherboards - Creates a motherboard in the database using the body of the request
#### PUT : http://localhost:8080/motherboard/24 - Updates the motherboard with id "24" from the database using the body of the request
###### DELETE : http://localhost:8080/motherboard/24 - Deletes the motherboard with id "24" from the database

## Pc Case
#### GET : http://localhost:8080/pcCases - Returns a list of all cases in the database
###### POST : http://localhost:8080/pcCases - Creates a case in the database using the body of the request
#### PUT : http://localhost:8080/pcCases/6 - Updates the case with id "6" from the database using the body of the request
###### DELETE : http://localhost:8080/pcCases/6 - Deletes the case with id "6" from the database

## Power Supplies
#### GET : http://localhost:8080/powerSupplies - Returns a list of all power supplies in the database
###### POST : http://localhost:8080/powerSupplies - Creates a power supply in the database using the body of the request
#### PUT : http://localhost:8080/powerSupplies/19 - Updates the power supply with id "19" from the database using the body of the request
###### DELETE : http://localhost:8080/powerSupplies/19 - Deletes the power supply with id "19" from the database

## Processor
#### GET : http://localhost:8080/processors - Returns a list of all processors in the database
###### POST : http://localhost:8080/processors - Creates a processor in the database using the body of the request
#### PUT : http://localhost:8080/processors/16 - Updates the processor with id "16" from the database using the body of the request
###### DELETE : http://localhost:8080/processors/16 - Deletes the processor with id "16" from the database

## Ram
#### GET : http://localhost:8080/ram - Returns a list of all ram in the database
###### POST : http://localhost:8080/ram - Creates ram in the database using the body of the request
#### PUT : http://localhost:8080/ram/17 - Updates the ram with id "17" from the database using the body of the request
###### DELETE : http://localhost:8080/ram/17 - Deletes the ram with id "17" from the database

## Storage Drive
#### GET : http://localhost:8080/storageDrives - Returns a list of all storage drives in the database
###### POST : http://localhost:8080/storageDrives - Creates a storageDrive in the database using the body of the request
#### PUT : http://localhost:8080/storageDrives/46 - Updates the storageDrive with id "46" from the database using the body of the request
###### DELETE : http://localhost:8080/storageDrives/46 - Deletes the storageDrive with id "46" from the database

