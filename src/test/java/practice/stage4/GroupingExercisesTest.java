package practice.stage4;

import org.junit.jupiter.api.Test;
import practice.model.Employee;
import practice.model.Order;
import practice.model.Person;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupingExercisesTest {

    private final GroupingExercises ex = new GroupingExercisesImpl();

    @Test
    void groupsByAgeDecade() {
        List<Person> people = List.of(
                new Person("A", 24),
                new Person("B", 31),
                new Person("C", 29),
                new Person("D", 30)
        );

        Map<Integer, List<Person>> result = ex.groupByAgeDecade(people);

        assertEquals(2, result.get(20).size());
        assertEquals(2, result.get(30).size());
    }

    @Test
    void countsWordsByFirstLetter() {
        List<String> words = List.of("apple", "avocado", "banana", "cherry");

        Map<Character, Long> result = ex.countWordsByFirstLetter(words);

        assertEquals(2L, result.get('a'));
        assertEquals(1L, result.get('b'));
        assertEquals(1L, result.get('c'));
    }

    @Test
    void sumsOrderTotalsByCustomer() {
        List<Order> orders = List.of(
                new Order("alice", 10.0),
                new Order("bob", 5.0),
                new Order("alice", 15.0)
        );

        Map<String, Double> result = ex.sumOrderTotalsByCustomer(orders);

        assertEquals(25.0, result.get("alice"), 0.0001);
        assertEquals(5.0, result.get("bob"), 0.0001);
    }

    @Test
    void partitionsEvenOdd() {
        Map<Boolean, List<Integer>> result = ex.partitionEvenOdd(List.of(1, 2, 3, 4, 5, 6));

        assertEquals(List.of(2, 4, 6), result.get(true));
        assertEquals(List.of(1, 3, 5), result.get(false));
    }

    @Test
    void namesByDepartment() {
        List<Employee> employees = List.of(
                new Employee("Alice", "Eng", 100),
                new Employee("Bob", "Eng", 90),
                new Employee("Carol", "Sales", 80)
        );

        Map<String, List<String>> result = ex.namesByDepartment(employees);

        assertEquals(List.of("Alice", "Bob"), result.get("Eng"));
        assertEquals(List.of("Carol"), result.get("Sales"));
    }

    @Test
    void flattensAndDedupes() {
        List<List<Integer>> lists = List.of(
                List.of(1, 2, 3),
                List.of(2, 3, 4),
                List.of(4, 5)
        );

        List<Integer> result = ex.flattenAndDedupe(lists);

        assertEquals(List.of(1, 2, 3, 4, 5), result);
    }

    @Test
    void highestPaidByDepartment() {
        List<Employee> employees = List.of(
                new Employee("Alice", "Eng", 100),
                new Employee("Bob", "Eng", 150),
                new Employee("Carol", "Sales", 80)
        );

        Map<String, Employee> result = ex.highestPaidByDepartment(employees);

        assertEquals("Bob", result.get("Eng").name());
        assertEquals("Carol", result.get("Sales").name());
    }
}
