package com.mylaps;

import com.mylaps.model.Race;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * Main class for Racing Car Simulation
 * @author rameswaree@gmail.com
 */
public class CarSimulationApp {

    public static void main(String[] args){

        Stream<Race> raceStream;

        Map<Integer, List<LocalTime>> map = new LinkedHashMap<>();//LinkedHashMap is used as it maintains the order of insertion

        int winner = 0;

        LocalTime localTime = LocalTime.MAX;

        LocalTime startTime = LocalTime.parse("11:59:00"); //start time for the race; can be modified later to match with time zones

        try(
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(Objects.requireNonNull(CarSimulationApp.class.getClassLoader().getResourceAsStream("karttimes.csv")))
                        );
                Stream<String> lines = reader.lines()
           ) {

            raceStream = lines.map(line -> {
                String[] s = line.split(",");
                if ((!("").equalsIgnoreCase(s[0]) && !("").equalsIgnoreCase(s[1])) &&
                        (!("kart").equalsIgnoreCase(s[0]) && !("passingtime").equalsIgnoreCase(s[1]))) {

                    Race race = new Race(Integer.parseInt(s[0]), LocalTime.parse(s[1]));

                    if (map.containsKey(race.getKartNo())) {
                        map.get(race.getKartNo()).add(race.getPassingTime());
                    } else {
                        map.put(race.getKartNo(),
                                new ArrayList<>(Collections.singleton(race.getPassingTime())));
                    }
                    return race;
                }
                return null;
            }).filter(Objects::nonNull);

            raceStream.forEach(System.out::println);

            System.out.println(map);

            //when file is empty
            if (map.size() == 0) {
                System.out.println("Empty file. Please provide a file with valid data!!");
                System.exit(0);
            }

            for (Map.Entry<Integer, List<LocalTime>> m : map.entrySet()) {
                LocalTime local = m.getValue().get(m.getValue().size() - 1);
                if (local.isBefore(localTime)) {
                    winner = m.getKey();
                    localTime = m.getValue().set(m.getValue().size() - 1, local);
                }
            }

            //list of lap times of the winner
            List<LocalTime> localTimesList = map.get(winner);

            //lap duration
            long duration = startTime.until(localTimesList.get(0), SECONDS);
            int lapNumber = 0;

            for (int i = 0; i < localTimesList.size() - 1; i++) {
                if (localTimesList.get(i).until(localTimesList.get(i + 1), SECONDS) < duration) {
                    duration = localTimesList.get(i).until(localTimesList.get(i + 1), SECONDS);
                    lapNumber = i + 1;
                    startTime = localTimesList.get(i);

                    if (duration < 0) {
                        System.out.println("Duration of lap number " + (lapNumber + 1) + " is negative. Please check input!");
                        System.exit(0);
                    }
                }
            }

            if(duration%60 == 0 && duration != 60){
                System.out.println("Winner is kart number " + winner + ". The fastest lap is lap number " +
                        lapNumber + " starting at " + startTime + ". The duration of the lap is " +
                        (duration/60) + " minutes.");
            }else if (duration > 120) {
                System.out.println("Winner is kart number " + winner + ". The fastest lap is lap number " +
                        lapNumber + " starting at " + startTime + ". The duration of the lap is " +
                        (duration / 60) + " minutes and " + (duration % 60) + " seconds.");
            }else if(duration > 60 && duration < 120){
                System.out.println("Winner is kart number " + winner + ". The fastest lap is lap number " +
                        lapNumber + " starting at " + startTime + ". The duration of the lap is " +
                        (duration / 60) + " minute and " + (duration % 60) + " seconds.");
            }else if (duration == 60){
                System.out.println("Winner is kart number " + winner + ". The fastest lap is lap number " +
                        lapNumber + " starting at " + startTime + ". The duration of the lap is " +
                        (duration/60) + " minute.");
            }else{
                System.out.println("Winner is kart number " + winner + ". The fastest lap is lap number " +
                        lapNumber + " starting at " + startTime + ". The duration of the lap is " +
                        duration + " seconds.");
            }



        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
