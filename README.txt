Project Title: Coursework

Description: The project simulates the functioning of a school with given name, number of days to run,subjects, students
and instructors.There is also an administrator that is required for the operation of the school. After each day a 
"pretty string" method is called printing the status information of the school, such as day number, courses, students, 
instructors,graduates and how they are connected (ex. Student1 has enrolled in 2 courses). New students and instructors 
with random names have the chance to be added to the school every new day. 

Extension: The extension I chose was the instructor to have a number of courses that he/she can teach and the student - 
a number of courses that he/she can attend. The first change was adding lists of courses to the Instructor and Student
classes with proper methods to access them. The second change was to make the method aDayAtSchool() to add enrol the 
students in as much courses possible, so that the full potential of the school is used. I also added a courses status
method to return weather the student/instructor does not have any course, has some, or has the maximum he can. 

Executing program: 
1.Open a terminal and go to the directory of the program. The "Administrator.java" file must be compiled first. Typing
"javac Administrator.java" would do the job. After the compilation, the user has to start the program by choosing a
specific text file to provide the base information of the new school and typing a number of days the school should run
(ex. "java Administrator sample.txt 100"). The file sample.txt should be in the following format:

school:ECS Java Training School
subject:Basics,1,1,5
subject:Lab 1,2,2,2
subject:Arrays,3,1,4
student:Peter,M,60
student:John,M,22
student:Annabelle,F,31
student:Maggie,F,58
student:Alex,M,23
Teacher:Yvonne,F,55
Demonstrator:Beth,F,45
OOTrainer:Chris,M,62
GUITrainer:Sarah,F,48

If the format of the file is wrong, a proper exception will be executed.

2.Another way to execute the program is to run the CourseworkExtended.jar file which will start the GUI version of the 
program. Then the "New School" button should be clicked, opening a window for a file selection. The user should select
the file with the school information and the enter the number of days he/she wants it to work. The "Graduates" button
shows the final graduates of the school.

Author: Metodi Gachev 