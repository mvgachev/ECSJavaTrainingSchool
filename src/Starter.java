import java.io.*;

public class Starter {

    private School school;
    private BufferedReader reader;
    private int numberOfDays;
    private String stringGUI = null;

    /**
     * Constructs a normal starter.
     *
     * @param fileName     takes the name of the file
     * @param numberOfDays takes the number of days to run as an integer
     */
    public Starter(String fileName, int numberOfDays) {

        //Creates a new buffered reader to read through the specific file
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("There is no such file!");   //prints message when no such file is found
        }
        this.numberOfDays = numberOfDays;
        checkData(reader);
        //When everything is ready without an exception runs the school with its admin
        Administrator admin = new Administrator(school);
        admin.run(numberOfDays);
    }

    /**
     * Constructs a GUI Starter.
     *
     * @param file         takes the file
     * @param numberOfDays takes the number of days to run as an integer
     */
    public Starter(File file, int numberOfDays) {
        //Creates a new buffered reader to read through the specific file
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("There is no such file!");   //prints message when no such file is found
        }
        this.numberOfDays = numberOfDays;
        assert reader != null;
        checkData(reader);
        //When everything is ready without an exception runs the school with its admin
        Administrator admin = new Administrator(school);
        stringGUI = admin.runGUI(numberOfDays);
    }

    /**
     * @return the string for the GUI
     */
    public String getStringGUI() {
        return stringGUI;
    }

    /**
     * Checks the data from the reader if it has a school and compiles the rest.
     *
     * @param reader takes the buffered reader from the constructor
     */
    private void checkData(BufferedReader reader) {
        try {
            String line = reader.readLine();
            //The first line should start with a school initialization
            if (line.startsWith("school:")) {
                school = new School(line.substring(line.indexOf(":") + 1));
                line = reader.readLine();
                //After we have the school, we are ready to add everything else
                while (line != null) {
                    compile(line);
                    line = reader.readLine();
                }
            }
            //If there is no school -> throws exception
            else throw new IOException("There is no school, or it hasn't been created in the proper format!");

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Input/Output exception. Message: " + e.getMessage());
        }
    }

    /**
     * Compiles the lines after the school initialization
     *
     * @param line takes the line that should be compiled as a string
     *             Everything it reads - creates and puts it in the school object
     */
    private void compile(String line) {

        //Saves the start of the line (what are we adding to the school)
        String start = line.substring(0, line.indexOf(":") + 1).trim();
        //Leaves the specifications in the line string
        line = line.substring(line.indexOf(":") + 1).trim();
        //Splits the data from the string and puts in an array
        String[] data = line.split(",");
        //An appropriate exception message providing the line where the mistake is
        String exception = "invalid " + start.substring(0, start.length() - 1) + "\n" + start + line;

        //Checks if a subject needs to be created
        if (start.equals("subject:")) {
            //Checks if there is enough data to create a subject
            if (data.length != 4)
                throw new IllegalStateException(exception);
            //Takes the info needed to create a subject and adds it to the school
            int id = Integer.parseInt(data[1]);
            int specialism = Integer.parseInt(data[2]);
            int duration = Integer.parseInt(data[3]);
            //Creates a new subject with this data
            Subject newSubject = new Subject(id, specialism, duration);
            newSubject.setDescription(data[0]);
            //Checks if the subject has the same ID as the one already in the school
            if (!school.getSubjectIDs().contains(newSubject.getID()))
                //If it does not - adds it to the school
                school.add(newSubject);
                //If it does - returns an exception
            else throw new IllegalStateException(exception);
        }

        //Checks for types of Person and gets their data
        else {
            if (data.length != 3)
                throw new IllegalStateException(exception);
            String name;
            int age;
            char gender;
            name = data[0];
            gender = data[1].charAt(0);
            age = Integer.parseInt(data[2]);
            //For every different case creates and adds a specific type of Person to the school
            switch (start) {
                case "student:":
                    school.add(new Student(name, gender, age));
                    break;
                case "Teacher:":
                    school.add(new Teacher(name, gender, age));
                    break;
                case "Demonstrator:":
                    school.add(new Demonstrator(name, gender, age));
                    break;
                case "OOTrainer:":
                    school.add(new OOTrainer(name, gender, age));
                    break;
                case "GUITrainer:":
                    school.add(new GUITrainer(name, gender, age));
                    break;
                default:
                    //If none of the above is found throws an exception about the format
                    throw new IllegalStateException("The format is wrong: " + " \"" + start + "\"");
            }
        }
    }
}
