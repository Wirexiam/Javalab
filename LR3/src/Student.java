import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int age;
    private String gender;
    private List<Integer> grades;
    private double averageGrade;

    public Student(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addGrade(int grade) {
        grades.add(grade);
        calculateAverageGrade();
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    private void calculateAverageGrade() {
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        this.averageGrade = grades.size() > 0 ? sum / grades.size() : 0;
    }

    @Override
    public String toString() {
        return "Студент: " + name + ", Возраст: " + age + ", Пол: " + gender + ", Средняя оценка: " + averageGrade;
    }
}
