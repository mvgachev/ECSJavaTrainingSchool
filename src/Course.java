/**
 * Represents a course.
 * Has an subject, number of days until start and number of days that needs to run.
 * Has a status accessor method.
 * Has a method that represents a day for the course.
 * Has a method that enrolls students in it.
 * Has a method that returns the number of student enrolled.
 * Has a method that returns a list of the students.
 */
public class Course {

    private Subject subject;
    private int daysUntilStarts;
    private int daysToRun;
    private Student[] enrolledStudents = new Student[3];
    private int numberOfStudents = 0;
    private Instructor instructor = null;
    private boolean isCancelled = false;
    private boolean hasFinished = false;

    /**
     * Constructs a course.
     *
     * @param subject         the subject associated with the course
     * @param daysUntilStarts number of days until the course starts
     */
    public Course(Subject subject, int daysUntilStarts) {
        this.subject = subject;
        this.daysUntilStarts = daysUntilStarts;
        this.daysToRun = subject.getDuration();
        subject.setCourse(this);
    }

    /**
     * Represents what happens after:
     * a day has passed for a course,
     * a course finishes,
     * a course is cancelled.
     */
    public void aDayPasses() {
        if (daysUntilStarts > 0) daysUntilStarts--;
            //Checks if the course has been cancelled already
        else if (!isCancelled) {
            //The course has started
            if (daysToRun > 0) daysToRun--;
            //when the course ends graduates the students and sets it to finished
            if (daysToRun == 0) {
                //graduates the students and then removes them from the course
                for (int i = 0; i < numberOfStudents; i++) {
                    enrolledStudents[i].graduate(subject);
                    enrolledStudents[i].removeCourse(this);
                }
                instructor.unassignCourse(this);    //removes the Instructor
                subject.setCourse(null);      //Makes the subject available for other courses
                hasFinished = true;             //Makes the course finished
            }
        }
        //Checks on the last day before the course starts whether it should be cancelled
        if (daysUntilStarts == 0 && (!hasInstructor() || numberOfStudents == 0)) {
            //Checks if the course has students and makes them free if so
            if (numberOfStudents != 0) {
                for (int i = 0; i < numberOfStudents; i++) {
                    enrolledStudents[i].removeCourse(this);
                }
            }
            //Checks if the course has an instructor and makes him available
            if (hasInstructor()) instructor.unassignCourse(this);
            subject.setCourse(null);               //Makes the subject available for other course
            numberOfStudents = 0;                  //Removes all of the students in the course
            isCancelled = true;                     //Cancels the course
        }
    }

    /**
     * Enrolls students and checks if they have been enrolled successfully.
     * The course has a maximum of 3 students.
     * Checks if the student can enroll given his specific number of courses he can attend.
     *
     * @param student takes the student taking the course
     */
    public boolean enrolStudent(Student student) {
        if (numberOfStudents == 3 || daysUntilStarts == 0) return false;
        else {
            boolean studentCan;
            //Enrols the student to the course and saves the result of the enrolment
            studentCan = student.enrolTo(this);
            //If it was successful adds the student to the course as well
            if(studentCan) {
                enrolledStudents[numberOfStudents] = student;
                numberOfStudents++;
            }
            //Returns if the enrolment is successful
            return studentCan;
        }
    }

    /**
     * @return the subject of the course
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @return the status of the course in the form of days until the course starts or finishes
     */
    public int getStatus() {
        if (daysUntilStarts > 0) return -daysUntilStarts;
        else return daysToRun;
    }

    /**
     * Returns the number of students in the course.
     */
    public int getSize() {
        return numberOfStudents;
    }

    /**
     * Returns the list of students enrolled in an array.
     */
    public Student[] getStudents() {
        Student[] enrolledStudents = new Student[numberOfStudents];
        //Creates a new array, which contains only the cells which have a student in them from the local array
        System.arraycopy(this.enrolledStudents, 0, enrolledStudents, 0, numberOfStudents);
        return enrolledStudents;
    }

    /**
     * Checks if an instructor can teach the subject of the course.
     * Assigns the course to the instructor if he can.
     *
     * @param instructor takes the instructor
     * @return returns whether the assignment is successful
     */
    public boolean setInstructor(Instructor instructor) {
        if (instructor.canTeach(subject)) {
            instructor.assignCourse(this);
            this.instructor = instructor;
            return true;
        } else return false;
    }

    /**
     * @return true or false whether the course has instructor
     */
    public boolean hasInstructor() {
        return instructor != null;
    }

    /**
     * @return true or false whether the course has been cancelled
     */
    public boolean isCancelled() {
        return isCancelled;
    }

    /**
     * @return true or false whether the course has finished
     */
    public boolean hasFinished() {
        return hasFinished;
    }
}
