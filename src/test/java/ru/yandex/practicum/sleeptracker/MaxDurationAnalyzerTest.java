package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaxDurationAnalyzerTest {

    private final MaxDurationAnalyzer analyzer = new MaxDurationAnalyzer();

    @Test
    void shouldReturnZeroForEmptyList() {
        assertEquals(0L, analyzer.analyze(List.of()));
    }

    @Test
    void shouldReturnMaxDuration() {
        SleepSession s1 = new SleepSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 2, 6, 0),   // 480
                SleepQuality.GOOD);
        SleepSession s2 = new SleepSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 7, 0),   // 480
                SleepQuality.NORMAL);
        SleepSession s3 = new SleepSession(
                LocalDateTime.of(2025, 10, 3, 14, 0),
                LocalDateTime.of(2025, 10, 3, 16, 0),  // 120
                SleepQuality.BAD);

        assertEquals(480L, analyzer.analyze(List.of(s1, s2, s3)));
    }
}