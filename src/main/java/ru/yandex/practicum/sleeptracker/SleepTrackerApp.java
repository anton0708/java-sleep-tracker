package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.util.List;

public class SleepTrackerApp {

    public static void main(String[] args) {

        List<SleepSession> sessions;

        if (args.length == 0) {
            System.out.println("Укажите путь к файлу с логом сна.");
            return;
        }

        try {
            sessions = SleepLogParser.readLog(args[0]);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return;
        }

        List<SleepAnalyzer<?>> analyzers = List.of(
                new TotalSessionsAnalyzer(),
                new MinDurationAnalyzer(),
                new MaxDurationAnalyzer(),
                new AverageDurationAnalyzer(),
                new BadQualitySessionsAnalyzer(),
                new InsomniaNightsAnalyzer(),
                new ChronotypeAnalyzer()
        );

        analyzers.forEach(analyzer -> {
            Object result = analyzer.analyze(sessions);
            System.out.println(analyzer.getName() + ": " + result);
        });
    }
}