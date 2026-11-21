package ru.yandex.practicum.sleeptracker;

import java.util.List;

public interface SleepAnalyzer<T> {

    T analyze(List<SleepSession> sessions);

    String getName();
}
