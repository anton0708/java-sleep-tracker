package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinDurationAnalyzerTest {

    private final MinDurationAnalyzer analyzer = new MinDurationAnalyzer();

    @Test
    void shouldReturnZeroForEmptyList() {
        assertEquals(0L, analyzer.analyze(List.of()));
    }

    @Test
    void shouldRetrunMinDuration(){
        SleepSession s1 = new SleepSession(
                LocalDateTime.of(2025, 10, 1, 22, 15),
                LocalDateTime.of(2025, 10, 2, 8, 0),
                SleepQuality.GOOD);

        SleepSession s2 = new SleepSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 8, 0),
                SleepQuality.NORMAL);

        SleepSession s3 = new SleepSession(
                LocalDateTime.of(2025, 10, 3, 14, 30),
                LocalDateTime.of(2025, 10, 3, 15, 20),
                SleepQuality.BAD);

    assertEquals(50L, analyzer.analyze(List.of(s1, s2, s3)));
    }
}