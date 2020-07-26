import java.util.Iterator;
import java.util.Random;

/**
 * Represents an administrator.
 * Runs the school by:
 * creating random students and instructors,
 * running the school for a day,
 * removing the instructors and students when needed.
 */
public class Administrator {

    private School school;
    private final String[] FIRST_MALE_NAMES = new String[]{"Jake", "Mike", "Jordan", "Taylor", "Kalin", "Patrick",
            "Angel", "Donald", "Floyd", "Franklin", "Tom", "Pete"};
    private final String[] FIRST_FEMALE_NAMES = new String[]{"Eve", "Donna", "Ivana", "Gergana", "Maria", "Michelle",
            "Beth", "Anna", "Lizzy", "Aretha", "Daniella", "Sasha", "Maddy"};
    private final String[] LAST_NAMES = new String[]{"Belford", "Austin", "Trump", "Davis", "Martin", "McGregor",
            "Bush", "Steward", "Clark", "Williams", "Jackson", "Thompson"};
    private Random random = new Random();

    public Administrator(School school) {
        this.school = school;
    }

    /**
     * Simulates a how a school is running for one day:
     * creates and adds new students and teachers to the school,
     * runs the school,
     * removes students or instructors that have no job.
     */
    private void run() {
        char gender;

        //Creating a random number of students
        int randomNumberStudents = random.nextInt(3);
        for (int i = 0; i < randomNumberStudents; i++) {
            gender = getRandomGender();
            school.add(new Student(getRandomName(gender), gender, getRandomAge()));
        }

        //Adding instructors based on their probabilities of coming to the school
        int probability = random.nextInt(100) + 1;
        if (probability > 0 && probability <= 20) {
            gender = getRandomGender();
            school.add(new Teacher(getRandomName(gender), gender, getRandomAge()));
        }
        probability = random.nextInt(100) + 1;
        if (probability > 20 && probability <= 30) {
            gender = getRandomGender();
            school.add(new Demonstrator(getRandomName(gender), gender, getRandomAge()));
        }
        probability = random.nextInt(100) + 1;
        if (probability > 30 && probability <= 35) {
            gender = getRandomGender();
            school.add(new OOTrainer(getRandomName(gender), gender, getRandomAge()));
        }
        probability = random.nextInt(100) + 1;
        if (probability > 35 && probability <= 40) {
            gender = getRandomGender();
            school.add(new GUITrainer(getRandomName(gender), gender, getRandomAge()));
        }

        //Now that the school has students and instructors it is started
        school.aDayAtSchool();

        //At the end of the school day checks for instructors that can leave the school
        Iterator iterator = school.getInstructors().iterator();
        while (iterator.hasNext()) {
            Instructor instructor = (Instructor) iterator.next();
            //If an instructor is not assigned any course -> has 20% chance of leaving the school
            if (instructor.getCourseStatus() == 0) {
                int chance = random.nextInt(100) + 1;
                if (chance > 0 && chance <= 20) iterator.remove();
            }
        }

        //Checks for students that can leave the school
        iterator = school.getStudents().iterator();
        while (iterator.hasNext()) {
            Student student = (Student) iterator.next();
            int chance = random.nextInt(100) + 1;
            //If a student graduates all of the subjects he is leaving the school
            if (student.getCertificates().containsAll(school.getSubjectIDs())) {
                school.graduate(student);
                iterator.remove();
            }
            //If a student does not enrol in any course, he or she has 5% chance of leaving the school
            else if (student.getCourseStatus() == 0 && chance > 0 && chance <= 5) iterator.remove();
        }
    }

    /**
     * Makes the run method run for a specific number of days.
     *
     * @param numberOfDays takes the number of days as an integer
     */
    public void run(int numberOfDays) {
        for (int i = 0; i < numberOfDays; i++) {
            run();
            System.out.println("Day " + (i + 1));
            System.out.println(school);
        }
    }

    /**
     * Makes the run method run for a specific days for the GUI.
     *
     * @param numberOfDays takes the number of days to run
     * @return the data in the form of a string
     */
    public String runGUI(int numberOfDays) {
        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < numberOfDays; i++) {
            run();
            finalString.append("Day ").append(i + 1).append("\n");
            finalString.append(school.toString());
        }
        //System.out.println(finalString);
        return finalString.toString();
    }

    /**
     * Generates a random gender.
     *
     * @return the random generated gender
     */
    private char getRandomGender() {
        char gender;
        //chance could be 0 or 1 (50 percent chance)
        boolean chance = random.nextBoolean();
        if (chance) gender = 'M';
        else gender = 'F';
        return gender;
    }

    /**
     * Generates a random name based on the gender given.
     *
     * @param gender takes the specific gender as 'M' or 'F'
     * @return the generated name
     */
    private String getRandomName(char gender) {
        String firstName;
        String secondName;
        if (gender == 'M') firstName = FIRST_MALE_NAMES[random.nextInt(FIRST_MALE_NAMES.length)];
        else firstName = FIRST_FEMALE_NAMES[random.nextInt(FIRST_FEMALE_NAMES.length)];
        secondName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return firstName + " " + secondName;
    }

    /**
     * Generates a random age from 20 to 50.
     *
     * @return a random age
     */
    private int getRandomAge() {
        return random.nextInt(30) + 20;
    }

    /**
     * Creates the helper that starts reading the file.
     *
     * @param args takes the array of strings from the console
     */
    public static void main(String[] args) {
//        String fileName = args[0];
//        int numberOfDays = Integer.parseInt(args[1]);
        //Creates a helper which will read the file and pass the info to an admin
        new Starter("index.txt", 50);
    }
}
