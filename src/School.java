import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a school with its name.
 * Has {@link Student students}, {@link Instructor instructors} and {@link Subject subjects}.
 * Has a method that returns a status string of the school.
 * Has a method which runs a day at the school.
 */
public class School {

    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Subject> subjectList = new ArrayList<>();
    private ArrayList<Instructor> instructorList = new ArrayList<>();
    private ArrayList<Student> graduatesList = new ArrayList<>();
    private ArrayList<Course> courseList = new ArrayList<>();
    private ArrayList<Integer> subjectIDList = new ArrayList<>();
    private String name;

    /**
     * Constructs a school.
     *
     * @param name takes the name of the school
     */
    public School(String name) {
        this.name = name;
    }

    /**
     * Graduates a student.
     *
     * @param student takes a student and adds him to the graduates list
     */
    public void graduate(Student student) {
        graduatesList.add(student);
    }

    /**
     * Adds a new student to the school
     *
     * @param student takes a student
     */
    public void add(Student student) {
        studentList.add(student);
    }

    /**
     * Adds a subject to the school
     *
     * @param subject takes a subject
     */
    public void add(Subject subject) {
        subjectList.add(subject);
    }

    /**
     * Adds a course to the school
     *
     * @param course takes a course
     */
    public void add(Course course) {
        courseList.add(course);
    }

    /**
     * Adds an instructor to the school
     *
     * @param instructor takes an instructor
     */
    public void add(Instructor instructor) {
        instructorList.add(instructor);
    }

    /**
     * Removes a student from the school
     *
     * @param student takes a student
     */
    public void remove(Student student) {
        studentList.remove(student);
    }

    /**
     * Removes a subject from the school
     *
     * @param subject takes a subject
     */
    public void remove(Subject subject) {
        subjectList.remove(subject);
    }

    /**
     * Removes an instructor from the school
     *
     * @param instructor takes an instructor
     */
    public void remove(Instructor instructor) {
        instructorList.remove(instructor);
    }

    /**
     * Removes a course from the school
     *
     * @param course takes a course
     */
    public void remove(Course course) {
        courseList.remove(course);
    }

    /**
     * @return the list of students in the school
     */
    public ArrayList<Student> getStudents() {
        return studentList;
    }

    /**
     * @return the list of subjects in the school
     */
    public ArrayList<Subject> getSubjects() {
        return subjectList;
    }

    /**
     * @return the courses of the school
     */
    public ArrayList<Course> getCourses() {
        return courseList;
    }

    /**
     * @return the instructors of the school
     */
    public ArrayList<Instructor> getInstructors() {
        return instructorList;
    }

    /**
     * @return the list of subject IDs the school has
     */
    public ArrayList<Integer> getSubjectIDs() {
        for (Subject subject : subjectList)
            subjectIDList.add(subject.getID());
        return subjectIDList;
    }

    /**
     * Represents a day at school:
     * creates courses with subject unused,
     * assigns free instructors to the courses,
     * enrolls free students to the courses,
     * removes every cancelled or finished course at the end of the day.
     */
    public void aDayAtSchool() {
        //Every time it sees a subject that hasn't been used or open-for-registration ->
        for (Subject subject : subjectList) {
            //If there is no course with the given subject or the course is not open for registration ->
            if (subject.getCourse() == null || subject.getCourse().getStatus() == -1) {
                //-> Create a new course with this subject
                add(new Course(subject, 2));
            }
        }

        //Looks at each course that requires an instructor
        for (Course course : courseList) {
            if (!course.hasInstructor()) {
                //Finds an instructor that can teach the course and is free
                for (Instructor instructor : instructorList) {
                    //when it finds an instructor that does not have the max number of courses to teach
                    if (instructor.getCourseStatus() != -1)
                        //when it finds the proper instructor sets him for the course and stops the search
                        if (course.setInstructor(instructor)) break;
                }
            }
        }

        //Goes through each student ->
        for (Student student : studentList) {
            //-> Until it finds one that has time for more courses
            if (student.getCourseStatus() != -1)
                //Goes through each course ->
                for (Course course : courseList) {
                    //-> Until it finds one that the student has not graduated yet
                    if (!student.hasCertificate(course.getSubject()))
                        //And the student has not got any other assigned course with the same subject as this one
                        if (!student.hasAssignedSubject(course.getSubject()))
                            //Enrolls the student
                            course.enrolStudent(student);
                }
        }

        //Makes a day pass for each course
        for (Course course : courseList) {
            course.aDayPasses();
        }

        //Checks if the course has finished or has been cancelled and removes it
        Iterator courseIterator = courseList.iterator();
        while (courseIterator.hasNext()) {
            Course course = (Course) courseIterator.next();
            if (course.isCancelled() || course.hasFinished()) {
                courseIterator.remove();
            }
        }
    }

    /**
     * Overrides the method.
     *
     * @return returns a pretty-print string of the school containing:
     * school name,
     * subjects with their,
     * students with their characteristics,
     * instructors with their characteristics,
     * graduates with their characteristics.
     */
    public String toString() {
        int number = 0;
        String separation = "--------------------------------------------------------------------------------------\n";

        //School
        StringBuilder endString = new StringBuilder(separation);
        endString.append("School: ").append(name).append("\n");
        endString.append(separation);

        //Course
        endString.append("Courses:     Subjects:     Status:     Enrolled Students:\n");
        endString.append(separation);
        for (Course i : courseList) {
            number++;
            StringBuilder currentLine;
            //Prints the courses
            currentLine = new StringBuilder("Course" + number + ":");
            makeSpaces(currentLine, 13);
            //Prints the subjects
            currentLine.append(i.getSubject().getID()).append(",").append(i.getSubject().getDescription());
            makeSpaces(currentLine, 30);
            //Prints the status
            currentLine.append(i.getStatus());
            makeSpaces(currentLine, 38);
            //Prints the name of the students
            for (int j = 0; j < i.getStudents().length; j++) {
                if (i.getStudents()[j] != null)
                    currentLine.append(" ").append(i.getStudents()[j].getName()).append(",");
            }
            //Removes the last coma
            currentLine = new StringBuilder(currentLine.substring(0, currentLine.length() - 1));
            currentLine.append("\n");
            //Appends the current line to the end string
            endString.append(currentLine);
        }
        endString.append("\n");

        //Students
        endString.append(separation);
        endString.append("Students:     Student Name:     Certificates:     Number of courses assigned:\n");
        endString.append(separation);
        number = 0;
        for (Student i : studentList) {
            number++;
            StringBuilder currentLine;
            //Prints the student numbers
            currentLine = new StringBuilder("Student" + number + ":");
            makeSpaces(currentLine, 14);
            //Prints the names of the students
            currentLine.append(i.getName());
            makeSpaces(currentLine, 37);
            //Prints the certificates of the student
            if (!i.getCertificates().isEmpty()) {
                for (int j : i.getCertificates()) currentLine.append(j).append(",");
                currentLine = new StringBuilder(currentLine.substring(0, currentLine.length() - 1));    //remove the last coma
            } else currentLine.append("null");
            makeSpaces(currentLine, 64);
            //Prints the number of assigned courses of the student
            currentLine.append(i.getAssignedCourses().length).append("\n");
            endString.append(currentLine);
        }
        endString.append("\n");

        //Instructors
        endString.append(separation);
        endString.append("Instructors:     Name:               Type:              Assigned courses:\n");
        endString.append(separation);
        number = 0;
        for (Instructor i : instructorList) {
            StringBuilder currentLine;
            number++;
            //Prints the instructor numbers
            currentLine = new StringBuilder("Instructor" + number + ": ");
            makeSpaces(currentLine, 17);
            //Prints the names of the instructors
            currentLine.append(i.getName());
            makeSpaces(currentLine, 37);
            //Returns the name of the class of the instructor (ex. Teacher)
            currentLine.append(i.getClass().getSimpleName());
            makeSpaces(currentLine, 56);
            if (i.getCourseStatus() != 0)
                for (Course j : i.getAssignedCourses())
                    currentLine.append(j.getSubject().getID()).append(",").append(j.getSubject().getDescription()).append(";");
            else currentLine.append("null");
            currentLine.append("\n");
            endString.append(currentLine);
        }
        endString.append("\n");

        //Graduates
        endString.append(separation);
        endString.append("Graduates:     Name:               Gender:        Age:\n");
        endString.append(separation);
        number = 0;
        for (Student student : graduatesList) {
            StringBuilder currentLine;
            number++;
            //Prints graduate numbers
            currentLine = new StringBuilder("Graduate" + number + ":");
            makeSpaces(currentLine, 15);
            //Prints the name of the graduate
            currentLine.append(student.getName());
            makeSpaces(currentLine, 38);
            //Prints the gender of the graduate
            currentLine.append(student.getGender());
            makeSpaces(currentLine, 50);
            //Prints the age of the graduate
            currentLine.append(student.getAge());


            currentLine.append("\n");
            endString.append(currentLine);
        }
        //Checks if there are no graduates to return "Null"
        if (graduatesList.size() == 0) endString.append("Null");

        endString.append("\n\n");
        return endString.toString();
    }

    /**
     * Creates spaces in order to arrange the information in a readable way.
     *
     * @param currentLine takes the string for the current line
     * @param position    takes the position of the next element that has to be printed
     */
    private StringBuilder makeSpaces(StringBuilder currentLine, int position) {
        int length = currentLine.length();
        for (int i = 0; i < position - length; i++) currentLine.append(" ");
        return currentLine;
    }
}
