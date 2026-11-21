package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InsomniaNightsAnalyzer implements SleepAnalyzer<Integer> {

    @Override
    public Integer analyze(List<SleepSession> sessions) {
        if (sessions.isEmpty()) {
            return 0;
        }

        Set<LocalDate> coveredNights = sessions.stream()
                .filter(session -> coversNight(session))
                .map(InsomniaNightsAnalyzer::getNightDate)
                .collect(Collectors.toSet());

        LocalDate firstNight = getNightDate(sessions.get(0));
        LocalDate lastNight = getNightDate(sessions.get(sessions.size() - 1));

        return (int) firstNight.datesUntil(lastNight.plusDays(1))
                .filter(night -> !coveredNights.contains(night))
                .count();
    }

    @Override
    public String getName() {
        return "Бессонные ночи";
    }

    private static LocalDate getNightDate(SleepSession session) {
        int hour = session.getSleepStart().getHour();
        LocalDate startDate = session.getSleepStart().toLocalDate();

        if (hour >= 12) {
            return startDate.plusDays(1);
        } else {
            return startDate;
        }
    }

    private static boolean coversNight(SleepSession session) {
        LocalDate night = getNightDate(session);
        LocalDateTime nightStart = night.atStartOfDay();          // N 00:00
        LocalDateTime nightEnd = night.atTime(6, 0);              // N 06:00

        return session.getSleepStart().isBefore(nightEnd)
                && session.getSleepEnd().isAfter(nightStart);
    }
}
