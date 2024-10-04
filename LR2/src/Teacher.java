public class Teacher {
    private String name;
    private String subject;

    public Teacher(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }
    // Метод для выставления оценок студентам
    public void giveGrades(Student student) {
        for (int i = 0; i < 5; i++) {
            int grade = (int) (Math.random() * 3) + 3; // Выставляем случайные оценки от 3 до 5
            student.addGrade(grade); // Добавляем оценку студенту
        }
    }

    @Override
    public String toString() {
        return "Преподаватель: " + name + ", Предмет: " + subject;
    }
}
