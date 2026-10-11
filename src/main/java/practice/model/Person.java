package practice.model;

public record Person(String name, int age, Address address) {

    public Person(String name, int age) {
        this(name, age, null);
    }
}
