package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class MaxDurationAnalyzer implements SleepAnalyzer<Long>{


    @Override
    public Long analyze(List<SleepSession> sessions) {
        return sessions.stream()
                .mapToLong(SleepSession::getDurationMinutes)
                .max()
                .orElse(0L);
    }

    @Override
    public String getName() {
        return "Максимальная продолжительность сессии (мин)";
    }
}
