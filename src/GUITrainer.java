/**
 * Represents a GUITrainer.
 * A type of {@link Teacher}.
 * Has subject specialism of 4.
 */
public class GUITrainer extends Teacher {

    /**
     * Constructs a GUITrainer.
     *
     * @param name   takes the name of the person
     * @param gender takes the gender of the person
     * @param age    takes the age of the person
     */
    public GUITrainer(String name, char gender, int age) {
        super(name, gender, age);
    }

    @Override
    public boolean canTeach(Subject subject) {
        int subjectSpecialism = subject.getSpecialism();
        if (subjectSpecialism == 4) return true;
        else return super.canTeach(subject);
    }
}
