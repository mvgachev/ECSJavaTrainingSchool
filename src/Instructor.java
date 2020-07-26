import java.util.ArrayList;

/**
 * Represents an instructor.
 * He can be assigned a {@link Course}.
 * Has sub classes: {@link Teacher} and {@link Demonstrator}.
 */
public abstract class Instructor extends Person {

    private final int MAX_COURSES = 3;
    private ArrayList<Course> assignedCourses = new ArrayList<>();

    /**
     * Constructs an instructor.
     *
     * @param name   takes the name of the person
     * @param gender takes the gender of the person
     * @param age    takes the age of the person
     */
    public Instructor(String name, char gender, int age) {
        super(name, gender, age);

    }

    /**
     * Assigns a course to the instructor
     *
     * @param course takes the course he is assigned to
     */
    public void assignCourse(Course course) {
        assignedCourses.add(course);
    }

    /**
     * The course is unassigned for the Instructor.
     * Removes the course from the array.
     * Brings the array back to its format: course1, course2, 0.
     */
    public void unassignCourse(Course course) {
        assignedCourses.remove(course);
    }

    /**
     * @return the assigned course of the student
     */
    public Course[] getAssignedCourses() {
        return this.assignedCourses.toArray(new Course[this.assignedCourses.size()]);
    }

    /**
     * @return the status of the instructor:
     * if 0 he has no courses assigned,
     * if 1 he has some assigned and can teach more,
     * if -1 he has the maximum number of assigned courses.
     */
    public int getCourseStatus() {
        int number = assignedCourses.size();
        if (number == 0) return 0;
        if (number < MAX_COURSES) return 1;
        else return -1;
    }

    /**
     * Checks if the instructor can teach a specific subject.
     *
     * @param subject takes a subject
     */
    public abstract boolean canTeach(Subject subject);
}
