package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class BadQualitySessionsAnalyzer implements SleepAnalyzer<Integer>{
    @Override
    public Integer analyze(List<SleepSession> sessions) {
        return (int) sessions.stream()
                .filter(s -> s.getQuality() == SleepQuality.BAD)
                .count();
    }

    @Override
    public String getName() {
        return "Сессий с плохим качеством";
    }
}
