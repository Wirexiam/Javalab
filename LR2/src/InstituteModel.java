import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InstituteModel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Создаем студентов
        List<Student> students = new ArrayList<>();
        System.out.println("Введите количество студентов:");
        int studentCount = scanner.nextInt();
        scanner.nextLine();  // Очищаем ввод

        for (int i = 0; i < studentCount; i++) {
            System.out.println("Введите имя студента " + (i + 1) + ":");
            String studentName = scanner.nextLine();
            System.out.println("Введите возраст студента " + (i + 1) + ":");
            int studentAge = scanner.nextInt();
            scanner.nextLine();  // Очищаем ввод
            System.out.println("Введите пол студента " + (i + 1) + ":");
            String studentGender = scanner.nextLine();

            students.add(new Student(studentName, studentAge, studentGender));
        }

        // Создаем преподавателей
        List<Teacher> teachers = new ArrayList<>();
        System.out.println("Введите количество преподавателей:");
        int teacherCount = scanner.nextInt();
        scanner.nextLine();  // Очищаем ввод

        for (int i = 0; i < teacherCount; i++) {
            System.out.println("Введите имя преподавателя " + (i + 1) + ":");
            String teacherName = scanner.nextLine();
            System.out.println("Введите предмет преподавателя " + (i + 1) + ":");
            String subjectName = scanner.nextLine();

            teachers.add(new Teacher(teacherName, subjectName));
        }

        // Выставляем оценки студентам по предметам
        for (Teacher teacher : teachers) {
            for (Student student : students) {
                teacher.giveGrades(student);
            }
        }

        // Создаем родителей и определяем их премиальные
        List<Parent> parents = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println("Введите имя родителя студента " + student.getName() + ":");
            String parentName = scanner.nextLine();
            Parent parent = new Parent(parentName);
            parent.calculateBonus(student.getAverageGrade());
            parents.add(parent);
        }

        // Вывод информации о студентах, преподавателях и родителях
        System.out.println("\nСтуденты:");
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("\nПреподаватели:");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }

        System.out.println("\nРодители:");
        for (Parent parent : parents) {
            System.out.println(parent);
        }

        scanner.close();
    }
}
