package com.kaznowski.hugh.pairsInASet;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class Application {

    /*
    Passed 200000 numbers in 5842092702 nanoseconds (5.000000 seconds, 0.083333 minutes)
     */
    public static void main(String[] args) {
        int datasetSize = 200000;
        List<Integer> dataset = constructDataSet(datasetSize);
        FinderService<Integer> finderService = new FinderService<>(dataset);
        long startTime = System.nanoTime();
        List<Pair<Integer, Integer>> pairs = finderService.findAllPairs();
        long endTime = System.nanoTime();
        List<Integer> result = pairs
                .stream()
                .map(pair -> Arrays.asList(pair.getKey(), pair.getValue()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        if (result.size()!=dataset.size()) {
            throw new RuntimeException(format("Failed with size=%d", result.size()));
        }
        long duration = endTime-startTime;
        System.out.format("Passed %d numbers in %d nanoseconds (%f seconds, %f minutes)\n", datasetSize, duration, secondsFromNano(duration),
                minutesFromNano(duration));
    }

    private static double secondsFromNano(long nano) {
        return nano / (1000000 * 1000);
    }

    private static double minutesFromNano(long nano) {
        return secondsFromNano(nano)/60;
    }

    private static List<Integer> constructDataSet(int size) {
        List<Integer> data = new ArrayList<>(size);
        for (int i = 0; i< size/2; i++) {
            int value = Double.valueOf(Math.random()*1000).intValue();
            data.add(value);
            data.add(value);
        }
        Collections.shuffle(data);
        return data;
    }
}