/**
 * Represents a teacher.
 * A type of {@link Instructor}.
 * Has subject specialism of 1 or 2.
 */
public class Teacher extends Instructor {

    /**
     * Constructs a teacher.
     *
     * @param name   takes the name of the person
     * @param gender takes the gender of the person
     * @param age    takes the age of the person
     */
    public Teacher(String name, char gender, int age) {
        super(name, gender, age);
    }

    @Override
    public boolean canTeach(Subject subject) {
        int subjectSpecialism = subject.getSpecialism();
        return subjectSpecialism == 1 || subjectSpecialism == 2;
    }
}
