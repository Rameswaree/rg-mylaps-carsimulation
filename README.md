A Java application to read data from a file and determine the winner of the race. The required input headers on the file are "kart" and "passingtime". The application can be run on any IDE. 

The first time the drivers pass the start/finish line is considered to be as lap 1. The start time for this sample race is assumed to be 11:59:00, based on the input csv file. However, this can be changed in the Java file to any other time value based on the data set being loaded.

List of validations implemented:
 1. If the file is empty, then the program ends with the message "Empty file. Please provide a file with valid data!!".
 2. If the file is not present, it would throw NullPointerException.
 3. If the data was typed incorrectly, say, the lap has a value lesser than its previous lap in the "passingtime" column, then the program ends with the message "Duration of lap number {lapNumber} is negative. Please check input!" .
 4. With proper data being fed, the message displayed will be "Winner is kart number {winner}. The fastest lap number is {lapNumber} starting at {startTime} with a lap duration of {duration} seconds." . After this, the program terminates. 
