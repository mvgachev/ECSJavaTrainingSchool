import java.util.ArrayList;

/**
 * Represents a student.
 * Can enter a course.
 * The student has certificates of his graduated subjects.
 */
public class Student extends Person {

    private ArrayList<Integer> certificates = new ArrayList<Integer>();
    private final int MAX_COURSES = 2;
    private ArrayList<Course> assignedCourses = new ArrayList<>();
    private ArrayList<Subject> assignedSubjects = new ArrayList<>();

    /**
     * Constructs a student
     *
     * @param name   takes the name of the person
     * @param gender takes the gender of the person
     * @param age    takes the age of the person
     */
    public Student(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * adds the ID of the subject to the collections of certificates of the student.
     *
     * @param subject takes the subject of the student
     */
    public void graduate(Subject subject) {
        certificates.add(subject.getID());
    }

    /**
     * Returns the array list of certificates of the student.
     */
    public ArrayList<Integer> getCertificates() {
        return certificates;
    }

    /**
     * Checks if the student has taken a specific subject.
     * Returns true if it has and false if it hasn't.
     *
     * @param subject takes the subject of the student
     */
    public boolean hasCertificate(Subject subject) {
        return certificates.contains(subject.getID());
    }

    /**
     * Enrols the student into a course if its not his max number of courses.
     *
     * @return whether the student enrolled or not
     */
    public boolean enrolTo(Course course) {
        if (assignedCourses.size() < MAX_COURSES) {
            assignedCourses.add(course);
            assignedSubjects.add(course.getSubject());
            return true;
        } else return false;
    }

    /**
     * Removes a specific course from the list of courses.
     * @param course takes a specific course from the list
     */
    public void removeCourse(Course course) {
        assignedCourses.remove(course);
    }

    /**
     * @return the assigned course of the student
     */
    public Course[] getAssignedCourses() {
        return this.assignedCourses.toArray(new Course[this.assignedCourses.size()]);
    }

    /**
     * @return the status of the student:
     * if 0 he has no courses assigned,
     * if 1 he has some assigned and can teach more,
     * if -1 he has the maximum number of assigned courses.
     */
    public int getCourseStatus() {
        int numberOfCourses = assignedCourses.size();
        if (numberOfCourses == 0) return 0;
        if (numberOfCourses < MAX_COURSES) return 1;
        else return -1;
    }

    /**
     * Checks if a student has a specific subject in his assigned courses.
     *
     * @return whether it has the specified subject in the assigned list
     */
    public boolean hasAssignedSubject(Subject subject) {
        return assignedSubjects.contains(subject);
    }
}
