/**
 * Represents a person.
 * Has a name, gender and age.
 */
public class Person {

    private String name;
    private char gender;
    private int age;

    /**
     * Constructs a person.
     *
     * @param name   takes the name of the person
     * @param gender takes the gender of the person
     * @param age    takes the age of the person
     */
    public Person(String name, char gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    /**
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * @return the gender of the person
     */
    public char getGender() {
        return gender;
    }

    /**
     * @return the age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the person.
     */
    public void setAge(int newAge) {
        this.age = newAge;
    }
}
