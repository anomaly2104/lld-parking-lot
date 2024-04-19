## Video explanation: 
https://youtu.be/7IX84K9g23U

## Connect with me and my offerings:
https://enginebogie.com/u/anomaly2104

## LLD Cohort:
https://enginebogie.com/u/anomaly2104/offerings/PATH/e6cce7f1-6a56-4fe3-bb82-48e1876e4596

## Multi-threading Cohort: 
https://enginebogie.com/u/anomaly2104/offerings/PATH/e9522ac1-4e4c-4217-92ba-f691f34c321b

----
# Low Level System Design - Parking lot 

### Problem Statement
[Check here](problem-statment.md)

### Project Requirements

* JDK 1.8
* Maven
* For Unit Tests:  
  * Junit 4
  * Mockito

### Compiling/Building and running the unit tests
Go to the project root folder and then run: ./bin/setup.sh

### Runing the project
NOTE: Before running, please make sure you do the above setup step. Otherwise it will not run. 
The project can be run as follows in one of the two ways:

1) **Using file based input:**: It accepts a filename as a parameter at the command prompt and read the commands from that file.   
  ./bin/parking_lot.sh  <input_filepath>  
 Example: ./bin/parking_lot.sh  ./file_input.txt
2) **Using file based input:**: This will run the program in the interactive shell mode where commands can be typed in.  
  ./bin/parking_lot.sh 
  

### Further Enhancements:

* Dependency injection: Currently dependencies are injected manually. We can use some 
dependency injection framework like spring. 
* Exit command: Exit command is currently coupled with interactive mode only which makes
it non-reusable.
* Parking strategy: Parking strategy is currently associated with `ParkingLotService`. 
Instead of that, it makes more sense to associate it with `ParkingLot`.
* Mode: Mode checking is currently done in main function directly. There could be a
factory for that.
