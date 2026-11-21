package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InsomniaNightsAnalyzerTest {

    private final InsomniaNightsAnalyzer analyzer = new InsomniaNightsAnalyzer();

    private static SleepSession session(int day, int startHour, int startMin,
                                        int endDay, int endHour, int endMin) {
        return new SleepSession(
                LocalDateTime.of(2025, 10, day, startHour, startMin),
                LocalDateTime.of(2025, 10, endDay, endHour, endMin),
                SleepQuality.GOOD);
    }

    @Test
    void shouldReturnZeroForEmptyList() {
        assertEquals(0, analyzer.analyze(List.of()));
    }

    @Test
    void shouldReturnZeroWhenAllNightsCovered() {
        List<SleepSession> sessions = List.of(
                session(1, 23, 0, 2, 7, 0),   // ночь 2 окт
                session(2, 23, 0, 3, 7, 0)    // ночь 3 окт
        );
        assertEquals(0, analyzer.analyze(sessions));
    }

    @Test
    void shouldCountNightWithoutSleep() {
        List<SleepSession> sessions = List.of(
                session(3, 13, 0, 3, 14, 30)
        );
        assertEquals(1, analyzer.analyze(sessions));
    }

    @Test
    void shouldCountGapBetweenSessions() {
        List<SleepSession> sessions = List.of(
                session(1, 23, 0, 2, 7, 0),   // ночь 2
                session(3, 23, 0, 4, 7, 0)    // ночь 4
        );
        assertEquals(1, analyzer.analyze(sessions)); // ночь 3 бессонная
    }

    @Test
    void shouldNotCountNightWithBorderlineSleep() {
        List<SleepSession> sessions = List.of(
                session(2, 0, 0, 2, 6, 0)
        );
        assertEquals(0, analyzer.analyze(sessions));
    }

}