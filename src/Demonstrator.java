/**
 * Represents a demonstrator.
 * A type of {@link Instructor}.
 * Has subject specialism of 2.
 */
public class Demonstrator extends Instructor {

    /**
     * Constructs a Demonstrator.
     * @param name   takes the name of the person
     * @param gender takes the gender of the person
     * @param age    takes the age of the person
     */
    public Demonstrator(String name, char gender, int age) {
        super(name, gender, age);
    }

    @Override
    public boolean canTeach(Subject subject) {
        int subjectSpecialism = subject.getSpecialism();
        if(subjectSpecialism==2) return true;
        else return false;
    }
}
