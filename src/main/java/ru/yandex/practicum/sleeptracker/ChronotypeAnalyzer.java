package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChronotypeAnalyzer implements SleepAnalyzer<String> {

    @Override
    public String analyze(List<SleepSession> sessions) {
        Map<String, Long> counts = sessions.stream()
                .filter(ChronotypeAnalyzer::coversNight)
                .map(ChronotypeAnalyzer::classify)
                .collect(Collectors.groupingBy(t -> t, Collectors.counting()));

    long owl = counts.getOrDefault("Сова", 0L);
    long lark = counts.getOrDefault("Жаворонок", 0L);
    long pigeon = counts.getOrDefault("Голубь", 0L);

    if (owl > lark && owl > pigeon) {
        return "Сова";
    }
    if (lark > owl && lark > pigeon) {
        return "Жаворонок";
    }
    return "Голубь";
}

    @Override
    public String getName() {
        return "Хронотип пользователя";
    }

    private static String classify(SleepSession session) {
        LocalTime start = session.getSleepStart().toLocalTime();
        LocalTime end = session.getSleepEnd().toLocalTime();

        if (start.isAfter(LocalTime.of(23, 0)) && end.isAfter(LocalTime.of(9, 0))) {
            return "Сова";
        } else if (start.isBefore(LocalTime.of(22, 0)) && end.isBefore(LocalTime.of(7, 0))) {
            return "Жаворонок";
        } else {
            return "Голубь";
        }
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
        LocalDateTime nightStart = night.atStartOfDay();
        LocalDateTime nightEnd = night.atTime(6, 0);

        return session.getSleepStart().isBefore(nightEnd)
                && session.getSleepEnd().isAfter(nightStart);
    }
}
