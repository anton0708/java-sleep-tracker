package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class TotalSessionsAnalyzer implements SleepAnalyzer<Integer>{

    @Override
    public Integer analyze(List<SleepSession> sessions) {
        return sessions.size();
    }

    @Override
    public String getName(){
        return "Всего сессий сна";
    }

}
