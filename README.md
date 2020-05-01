## Camper Information System - JDBC Project
#### Lauren Rose
#### Radford University, Database I, Spr20

All code herein is the original work of the author, and is submitted for a grade. Therefore, it is intended for the author only. Author is not responsible for any duplication or use by other students for their submitted coursework.

## Build Instructions:

### With Gradle

To run as a gradle build:

Clone the repo (link provided)

From project root:

``gradle build``

``gradle run``

#

### Without Gradle

To run without Gradle, there are two options:

1. Add the provided `swingx-1.6.1.jar` to your classpath, or

2. Go into `CamperDataTerminal.java` and comment out the block of code specified

To only test functionality, you can run the `CamperSystem.jar` provided to use the system.

#

## Usage Instructions:

Enter a camper ID, first name, or last name into the search box and press Search. 
If a camper is found in the system, their information will populate in the form.
Change any field in the form and click Save to save the altered camper data to the
database.

If a camper is not found, nothing will populate.

For testing, the database has been populated with campers 101-104.

### Additional Notes

The system does not currently support searching by name if there are multiple campers with the same name.

You must be on a Radford University network or VPN for the database connection to be successful.
