package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class MinDurationAnalyzer implements SleepAnalyzer<Long> {

    @Override
    public Long analyze(List<SleepSession> sessions) {
        return sessions.stream()
                .mapToLong(SleepSession::getDurationMinutes)
                .min()
                .orElse(0L);
    }

    @Override
    public String getName() {
        return "Минимальная продолжительность сессии (мин)";
    }
}