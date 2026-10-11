package practice.stage5;

import practice.model.Person;

import java.util.List;
import java.util.Optional;

public class OptionalExercisesImpl implements OptionalExercises {

    @Override
    public Optional<Person> firstOlderThan(List<Person> people, int age) {
        // TODO
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public String cityOrUnknown(Optional<Person> person) {
        // TODO: chain .map(...).map(...).orElse(...)
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Optional<Integer> safeDivide(int a, int b) {
        // TODO
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public int sumOrZero(Optional<List<Integer>> maybeList) {
        // TODO
        throw new UnsupportedOperationException("not implemented");
    }
}
