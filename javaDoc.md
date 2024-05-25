# Pc Part API
Using postman to make changes to and view the database of pc parts

## Controllers
**User Controller** : Communicates to postman by using the user dao in order to map data to create, update, and delete both users and their roles in the database, including the role admin, which has access to make changes to all the other endpoints

**Cpu Cooler Controller** : Communicates to postman by using the cpu cooler dao in order to map data to the correct endpoints, as well as create, update, and delete cpu coolers in the database as an ADMIN

**Fan Controller** : Communicates to postman by using the fan dao in order to map data to the correct endpoints, as well as create, update, and delete fans in the database as an ADMIN

**Graphics Card Controller** : Communicates to postman by using the graphics card dao in order to map data to the correct endpoints, as well as create, update, and delete graphics cards in the database as an ADMIN

**Motherboard Controller** : Communicates to postman by using the motherboard dao in order to map data to the correct endpoints, as well as create, update, and delete motherboards in the database as an ADMIN

**Pc Case Controller** : Communicates to postman by using the pc case dao in order to map data to the correct endpoints, as well as create, update, and delete pc cases in the database as an ADMIN

**Power Supply Controller** : Communicates to postman by using the power supply dao in order to map data to the correct endpoints, as well as create, update, and delete power supplies in the database as an ADMIN

**Processor Controller** : Communicates to postman by using the processor dao in order to map data to the correct endpoints, as well as create, update, and delete processors in the database as an ADMIN

**Ram Controller** : Communicates to postman by using the ram dao in order to map data to the correct endpoints, as well as create, update, and delete ram in the database as an ADMIN

**Storage Drive Controller** : Communicates to postman by using the storage drive dao in order to map data to the correct endpoints, as well as create, update, and delete storage drives in the database as an ADMIN

## Daos
**User Dao** : Uses a jdbc template to manifest select, create, update, and delete user and role methods that communicate with the database

**Cpu Cooler Dao** : Uses a jdbc template to manifest select, create, update, and delete cpu cooler methods that communicate with the database

**Fan Dao** : Uses a jdbc template to manifest select, create, update, and delete fan methods that communicate with the database

**Graphics Card Dao** : Uses a jdbc template to manifest select, create, update, and delete graphics card methods that communicate with the database

**Motherboard Dao** : Uses a jdbc template to manifest select, create, update, and delete motherboard methods that communicate with the database

**Pc Case Dao** : Uses a jdbc template to manifest select, create, update, and delete pc case methods that communicate with the database

**Power Supply Dao** : Uses a jdbc template to manifest select, create, update, and delete power supply methods that communicate with the database

**Processor Dao** : Uses a jdbc template to manifest select, create, update, and delete processor methods that communicate with the database

**Ram Dao** : Uses a jdbc template to manifest select, create, update, and delete ram methods that communicate with the database

**Storage Drive Dao** : Uses a jdbc template to manifest select, create, update, and delete storage drive methods that communicate with the database

## Models
Each part in the database has their own model that provides the variables for each respective part, as well as any validation for entry in Postman

## Part Picker User Details Service
Used to set up the methods used to implement auth into the project