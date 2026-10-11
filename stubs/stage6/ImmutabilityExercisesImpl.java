package practice.stage6;

import java.util.List;
import java.util.Map;

public class ImmutabilityExercisesImpl implements ImmutabilityExercises {

    @Override
    public List<Integer> makeImmutableCopy(List<Integer> source) {
        // TODO
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public List<String> toUnmodifiableUpper(List<String> strings) {
        // TODO
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Map<String, Integer> concurrentWordCount(List<String> words) {
        // TODO: use ConcurrentHashMap
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public List<String> copyOnWriteDemo(List<String> initial, String toAdd) {
        // TODO: use CopyOnWriteArrayList
        throw new UnsupportedOperationException("not implemented");
    }
}
