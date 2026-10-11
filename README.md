# Collections & Streams Drill Set

Repeatable practice exercises for the Java Collections Framework and Streams
API, in the same style as the `txn-coding-round` project (Stage 1). Each
stage is a small interface + stub implementation (with `TODO`s) + a JUnit
test suite + a hidden reference solution.

## Stages

| Stage | Package | Topic |
|-------|---------|-------|
| 2 | `practice.stage2` | Comparator, sorting, `PriorityQueue` |
| 3 | `practice.stage3` | Streams fundamentals (map/filter/reduce/collect) |
| 4 | `practice.stage4` | `groupingBy` / `partitioningBy` / `flatMap` |
| 5 | `practice.stage5` | `Optional` |
| 6 | `practice.stage6` | Immutable collections, `ConcurrentHashMap`, `CopyOnWriteArrayList` |

(Stage 1 — plain `HashMap`/`List`/`Set` without streams — is the separate
`txn-coding-round` project.)

## How to practice

1. Pick a stage, e.g. `practice.stage3.StreamExercisesImpl`.
2. Implement the `TODO` methods (open the matching `*Exercises.java`
   interface for the problem description — also summarized in each method's
   Javadoc).
3. Run just that stage's tests:
   ```bash
   mvn -q -Dtest=StreamExercisesTest test
   ```
   Or run everything:
   ```bash
   mvn test
   ```
4. Check your work against `solution/stageN/*Solution.java` if you're stuck
   or want to compare approaches. These live outside `src/`, so they're never
   compiled and never spoil the exercise by accident.
5. To redo a stage from scratch (for speed drills), reset it back to the
   TODO stub:
   ```bash
   ./reset.sh stage3      # just one stage
   ./reset.sh all         # every stage
   ```

## Suggested drill routine

Do a stage cold, time yourself, then reset and redo it 1-2 more times later
the same day or the next day until you can write each method without
pausing to recall the API. Move to the next stage once a full `mvn test` run
for the current one passes in one pass, under a few minutes.
