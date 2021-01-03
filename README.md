A Java application to read data from file and determine the winner of the race.

The start time for the race is assumed to be 11:59 am, based on the sample input csv file. However, this can be changed according to the Zone.
If the file is empty, then the program ends with the message "Empty file. Please provide a file with input!!".
If the file is not present, ideally it should throw FileNotFoundException. But since the file is read using InputStreamReader, which extends Reader, it throws NullPointerException.

If the data was typed incorrectly, say, the last lap has the passing time lesser than the previous lap, then the program ends with the message "Duration of lap number {lapNumber} is negative. Please check input!" .

With proper data being fed, the message displayed will be "Winner is kart number {winner}. The fastest lap number is {lapNumber} starting at {startTime} with a lap duration of {duration} seconds." . After this, the program terminates. 