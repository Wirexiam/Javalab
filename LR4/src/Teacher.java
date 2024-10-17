import java.util.Random;

public class Teacher {
    private String name;
    private String subject;

    public Teacher(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    // Метод для выставления оценок студентам по предметам
    public void giveGrades(Student student) {
        Random random = new Random();
        student.addGrade("Русский", random.nextInt(3) + 3);               // Оценки по Русскому
        student.addGrade("Высшая математика", random.nextInt(3) + 3);    // Оценки по Математике
        student.addGrade("Программирование на Java", random.nextInt(3) + 3); // Оценки по Java
    }

    @Override
    public String toString() {
        return "Преподаватель: " + name + ", Предмет: " + subject;
    }
}
