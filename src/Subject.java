/**
 * Represents a subject.
 * Has an ID, specialism, duration and description.
 * Courses need a subject in order to be created.
 */
public class Subject {

    private int id;
    private int specialism;
    private int duration;
    private String description;
    private Course course = null;

    /**
     * Constructs a subject.
     *
     * @param id         takes the id of the subject
     * @param specialism takes the specialism id of the subject
     * @param duration   takes the duration (number of days) required to cover the subject
     */
    public Subject(int id, int specialism, int duration) {
        this.id = id;
        this.specialism = specialism;
        this.duration = duration;
    }

    /**
     * Returns the id of the subject.
     */
    public int getID() {
        return id;
    }

    /**
     * Returns the specialism of the subject.
     */
    public int getSpecialism() {
        return specialism;
    }

    /**
     * Returns the duration of the subject.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets a description to the subject.
     *
     * @param description takes a description in the form a string
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the description of the subject
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param course sets the course of the subject
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * @return whether the subject is part of a course
     */
    public Course getCourse() {
        return course;
    }
}
