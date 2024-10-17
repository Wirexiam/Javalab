import java.util.Random;

public class Teacher {
    private String name;
    private String subject;

    public Teacher(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public void giveGrades(Student student) {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int grade = random.nextInt(3) + 3; // Оценки от 3 до 5
            student.addGrade(grade);
        }
    }

    @Override
    public String toString() {
        return "Преподаватель: " + name + ", Предмет: " + subject;
    }
}
