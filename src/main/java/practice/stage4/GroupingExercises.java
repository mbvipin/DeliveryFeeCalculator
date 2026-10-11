package practice.stage4;

import practice.model.Employee;
import practice.model.Order;
import practice.model.Person;

import java.util.List;
import java.util.Map;

/**
 * Stage 4 — Grouping & partitioning. See README.md for problem descriptions.
 */
public interface GroupingExercises {

    /** Group people by age decade, e.g. age 24 -> key 20, age 31 -> key 30. */
    Map<Integer, List<Person>> groupByAgeDecade(List<Person> people);

    /** Count words by their lowercased first letter. */
    Map<Character, Long> countWordsByFirstLetter(List<String> words);

    /** Sum order totals per customer. */
    Map<String, Double> sumOrderTotalsByCustomer(List<Order> orders);

    /** Partition numbers into even (true) / odd (false). */
    Map<Boolean, List<Integer>> partitionEvenOdd(List<Integer> nums);

    /** Group employee names by department. */
    Map<String, List<String>> namesByDepartment(List<Employee> employees);

    /** Flatten a list of lists and remove duplicates, preserving first-seen order. */
    List<Integer> flattenAndDedupe(List<List<Integer>> lists);

    /** For each department, the employee with the highest salary. */
    Map<String, Employee> highestPaidByDepartment(List<Employee> employees);
}
