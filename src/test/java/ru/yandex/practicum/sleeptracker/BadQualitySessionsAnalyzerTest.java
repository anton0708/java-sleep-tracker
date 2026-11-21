package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BadQualitySessionsAnalyzerTest {

    private final BadQualitySessionsAnalyzer analyzer = new BadQualitySessionsAnalyzer();

    @Test
    void shouldReturnZeroForEmptyList() {
        assertEquals(0, analyzer.analyze(List.of()));
    }

    @Test
    void shouldCountOnlyBadSessions() {
        SleepSession s1 = new SleepSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 2, 6, 0),
                SleepQuality.GOOD);
        SleepSession s2 = new SleepSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 7, 0),
                SleepQuality.BAD);
        SleepSession s3 = new SleepSession(
                LocalDateTime.of(2025, 10, 3, 14, 0),
                LocalDateTime.of(2025, 10, 3, 16, 0),
                SleepQuality.BAD);

        assertEquals(2, analyzer.analyze(List.of(s1, s2, s3)));
    }
}