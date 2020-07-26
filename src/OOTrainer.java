/**
 * Represents a OOTrainer.
 * A type of {@link Teacher}.
 * Has subject specialism of 3.
 */
public class OOTrainer extends Teacher {

    /**
     * Constructs a OOTrainer.
     *
     * @param name   takes the name of the person
     * @param gender takes the gender of the person
     * @param age    takes the age of the person
     */
    public OOTrainer(String name, char gender, int age) {
        super(name, gender, age);
    }

    @Override
    public boolean canTeach(Subject subject) {
        int subjectSpecialism = subject.getSpecialism();
        if (subjectSpecialism == 3) return true;
        else return super.canTeach(subject);
    }
}
