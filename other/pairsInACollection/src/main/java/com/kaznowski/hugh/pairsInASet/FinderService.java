package com.kaznowski.hugh.pairsInASet;

import javafx.util.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.String.format;

public class FinderService<E> {

    private final List<E> collection;
    private final Predicate<Pair<E, E>> hasIncompletePair = pair -> pair.getValue()==null;

    public FinderService(Collection<E> collection) {
        this.collection = new ArrayList<E>(collection);
    }

    public List<Pair<E, E>> findAllPairs() {
        return collection.stream()
                .parallel()
                .sorted()
                .collect(Collectors.groupingByConcurrent(
                        Function.identity(),
                        Collectors.toList()))
                .entrySet()
                .stream()
                .flatMap(idAndListEntry -> idAndListEntry
                        .getValue()
                        .stream()
                        .reduce(new ArrayList<>(),
                                this::constructPartialResult,
                                this::combinePartialResults).stream())
                .collect(Collectors.toList());
    }

    private List<Pair<E, E>> combinePartialResults(List<Pair<E, E>> leftPartialResult, List<Pair<E, E>> rightPartialResult) {
        Optional<Pair<E, E>> leftIncomplete = leftPartialResult.stream().filter(hasIncompletePair).findFirst();
        Optional<Pair<E, E>> rightIncomplete = rightPartialResult.stream().filter(hasIncompletePair).findFirst();
        if (leftIncomplete.isPresent() && rightIncomplete.isPresent()) {
            leftPartialResult.remove(leftIncomplete.get());
            rightPartialResult.remove(rightIncomplete.get());
            leftPartialResult.add(new Pair<E, E>(leftIncomplete.get().getKey(), rightIncomplete.get().getKey()));
        }
        leftPartialResult.addAll(rightPartialResult);
        return leftPartialResult;
    }

    private List<Pair<E, E>> constructPartialResult(List<Pair<E, E>> list, E item) {
        if (list.size() == 0) {
            return Arrays.asList(new Pair<E, E>(item, null));
        }
        Optional<Pair<E, E>> hasPartial = list.stream().filter(hasIncompletePair).findFirst();
        Pair<E, E> pairToInsert;
        list = new ArrayList<>(list);
        if (hasPartial.isPresent()) {
            list.remove(hasPartial.get());
            pairToInsert = new Pair<>(hasPartial.get().getKey(), item);
        }
        else {
            pairToInsert = new Pair<>(item, null);
        }
        list.add(pairToInsert);
        return list;
    }

    public List<Pair<E, E>> findAllPairsOld() {
        int count = 4;
        int numberOfThreads = collection.size() / count;
        List<Pair<E, E>> accumulator = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            threads.add(new Thread(constructRunnableWithAccumulator(accumulator, count)));
        }
        threads.forEach(Thread::start);
        boolean anyThreadsRunning = true;
        while (anyThreadsRunning) {
            anyThreadsRunning = threads.stream().anyMatch(Thread::isAlive);
        }
        synchronized (collection) {
            synchronized (accumulator) {
                if (accumulator.size() < collection.size() / 2)
                    throw new RuntimeException(format("Threads have crashed, but found %d pairs", accumulator.size()));
            }
        }
        return accumulator;
    }

    private Runnable constructRunnableWithAccumulator(Collection<Pair<E, E>> accumulator, int count) {
        return () -> {
            for (int i = 0; i < count; i++) {
                Pair<E, E> pair = findPair(count - 1);
                synchronized (accumulator) {
                    accumulator.add(pair);
                }
            }
        };
    }

    private Pair<E, E> findPair(int chunkSize) {
        E left = null;
        E right = null;

        while (left == null || right == null) {
            List<E> dataset = null;
            synchronized (collection) {
//                dataset = IntStream.range(1, chunkSize)
//                        .mapToObj(i -> collection.remove())
//                        .collect(Collectors.toList());
            }
            for (int i = 0; i < dataset.size() - 1; i++) {
                for (int j = i + 1; j < dataset.size(); j++) {
                    if (dataset.get(i).equals(dataset.get(j))) {
                        left = dataset.get(i);
                        right = dataset.get(j);
                        break;
                    }
                }
                if (left != null) break;
            }
        }

        return new Pair<>(left, right);
    }
}
