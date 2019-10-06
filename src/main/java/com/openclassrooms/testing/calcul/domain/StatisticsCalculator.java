package com.openclassrooms.testing.calcul.domain;

import java.util.IntSummaryStatistics;
import java.util.List;

/**
 * Calculates the mean using an instance of IntSummaryStatistics
 */
public class StatisticsCalculator {

    private final IntSummaryStatistics summaryStatistics;

    public StatisticsCalculator(IntSummaryStatistics summaryStatistics) {
        this.summaryStatistics = summaryStatistics;
    }

    public Integer average(List<Integer> samples) {
        samples.forEach(summaryStatistics::accept);
        // Extract the average
        Double average = summaryStatistics.getAverage();
        return average.intValue();
    }
}
