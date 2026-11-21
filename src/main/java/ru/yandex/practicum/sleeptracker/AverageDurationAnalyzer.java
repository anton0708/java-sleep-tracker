package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class AverageDurationAnalyzer implements SleepAnalyzer<Double>{
    @Override
    public Double analyze(List<SleepSession> sessions) {
        return sessions.stream()
                .mapToLong(SleepSession::getDurationMinutes)
                .average()
                .orElse(0.0);
    }

    @Override
    public String getName() {
        return "Средняя продолжительность сессии (мин)";
    }
}
