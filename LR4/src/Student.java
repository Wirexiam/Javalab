import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private String name;
    private int age;
    private String gender;
    private Map<String, Integer> subjectGrades;  // Оценки по предметам
    private double averageGrade;

    public Student(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.subjectGrades = new HashMap<>();
        this.averageGrade = 0;
    }

    public String getName() {
        return name;
    }

    // Метод для добавления оценки по предмету
    public void addGrade(String subject, int grade) {
        subjectGrades.put(subject, grade);
        calculateAverageGrade();
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    // Вычисление средней оценки на основе всех предметов
    private void calculateAverageGrade() {
        double sum = 0;
        for (int grade : subjectGrades.values()) {
            sum += grade;
        }
        this.averageGrade = subjectGrades.size() > 0 ? sum / subjectGrades.size() : 0;
    }

    @Override
    public String toString() {
        return "Студент: " + name + ", Возраст: " + age + ", Пол: " + gender + ", Средняя оценка: " + averageGrade + ", Оценки: " + subjectGrades;
    }

    // Метод для записи студента в файл
    public void saveToFile(FileWriter writer) throws IOException {
        writer.write(name + "," + age + "," + gender + "," + subjectGrades.toString().replaceAll("[{}]", "") + "\n");
    }

    // Метод для чтения студента из файла
    public static Student loadFromFile(String line) {
        String[] parts = line.split(",");
        String name = parts[0];
        int age = Integer.parseInt(parts[1]);
        String gender = parts[2];
        Student student = new Student(name, age, gender);

        // Восстанавливаем оценки по предметам
        for (int i = 3; i < parts.length; i++) {
            String[] subjectGrade = parts[i].split("=");
            student.addGrade(subjectGrade[0].trim(), Integer.parseInt(subjectGrade[1].trim()));
        }

        return student;
    }
}
