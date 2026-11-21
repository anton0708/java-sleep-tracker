package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChronotypeAnalyzerTest {

    private final ChronotypeAnalyzer analyzer = new ChronotypeAnalyzer();

    private static SleepSession session(int day, int startHour, int startMin,
                                        int endDay, int endHour, int endMin) {
        return new SleepSession(
                LocalDateTime.of(2025, 10, day, startHour, startMin),
                LocalDateTime.of(2025, 10, endDay, endHour, endMin),
                SleepQuality.GOOD);
    }

    @Test
    void shouldReturnPigeonForEmptyList() {
        assertEquals("Голубь", analyzer.analyze(List.of()));
    }

    @Test
    void shouldReturnOwlWhenMostNightsAreOwl() {
        List<SleepSession> sessions = List.of(
                session(1, 23, 30, 2, 9, 30),
                session(2, 23, 45, 3, 10, 0),
                session(3, 21, 0, 4, 6, 0)
        );
        assertEquals("Сова", analyzer.analyze(sessions));
    }

    @Test
    void shouldIgnoreDaytimeSessions() {
        List<SleepSession> sessions = List.of(
                session(1, 14, 0, 1, 15, 30),   // дневная — игнорируется
                session(2, 13, 0, 2, 14, 30)    // дневная — игнорируется
        );
        assertEquals("Голубь", analyzer.analyze(sessions));
    }

}