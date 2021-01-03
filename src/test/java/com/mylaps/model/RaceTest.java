package com.mylaps.model;


import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class RaceTest {

    private Race race;

    private static final int KART_NUMBER = 12;
    private LocalTime localTime = LocalTime.parse("08:15:30");

    @Before
    public void setUp(){
        race = new Race();
    }

    @Test
    public void shouldReturnValidKartNumber(){
        race.setKartNo(KART_NUMBER);

        assertEquals(KART_NUMBER, race.getKartNo());
    }

    @Test
    public void shouldReturnPassingTime(){
        race.setPassingTime(localTime);

        assertEquals(localTime, race.getPassingTime());
    }

    @Test
    public void shouldReturnZeroKartNumber(){
        assertEquals(0, race.getKartNo());
    }

    @Test
    public void shouldReturnNullPassingTime(){
        assertNull(race.getPassingTime());
    }
}