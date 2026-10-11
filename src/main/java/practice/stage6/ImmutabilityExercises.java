package practice.stage6;

import java.util.List;
import java.util.Map;

/**
 * Stage 6 — Immutability & concurrency-adjacent collections. See README.md for problem descriptions.
 */
public interface ImmutabilityExercises {

    /** Return an unmodifiable copy of source (List.copyOf or similar). */
    List<Integer> makeImmutableCopy(List<Integer> source);

    /** Uppercase every string and collect into an unmodifiable list. */
    List<String> toUnmodifiableUpper(List<String> strings);

    /** Word -> occurrence count, computed into a ConcurrentHashMap (use computeIfAbsent/merge, not a plain HashMap). */
    Map<String, Integer> concurrentWordCount(List<String> words);

    /**
     * Build a CopyOnWriteArrayList from initial, then iterate over it with a for-each loop,
     * appending toAdd to the list the first time you see initial.get(0) during that iteration.
     * A plain ArrayList would throw ConcurrentModificationException here; CopyOnWriteArrayList
     * iterates over a snapshot, so it won't. Return the final contents as a List.
     */
    List<String> copyOnWriteDemo(List<String> initial, String toAdd);
}
