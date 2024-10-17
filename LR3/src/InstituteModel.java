import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class InstituteModel {
    public static void main(String[] args) throws Exception {
        OperationLogger loggerArrayList = new OperationLogger();
        OperationLogger loggerLinkedList = new OperationLogger();

        // Создаем коллекции студентов для ArrayList и LinkedList
        List<Student> studentsArrayList = new ArrayList<>();
        List<Student> studentsLinkedList = new LinkedList<>();

        Random random = new Random();
        int[] studentCounts = {10, 100, 1000, 10000, 100000};

        for (int studentCount : studentCounts) {
            // Добавляем студентов в ArrayList с логированием
            long startTime = System.nanoTime();
            for (int i = 0; i < studentCount; i++) {
                Student student = RussianGenerator.generateRandomStudent(i);
                long addStart = System.nanoTime();
                studentsArrayList.add(student);
                long addEnd = System.nanoTime();
                loggerArrayList.logAdd(i, addEnd - addStart);
            }
            long totalArrayListTime = System.nanoTime() - startTime;

            // Добавляем студентов в LinkedList с логированием
            startTime = System.nanoTime();
            for (int i = 0; i < studentCount; i++) {
                Student student = RussianGenerator.generateRandomStudent(i);
                long addStart = System.nanoTime();
                studentsLinkedList.add(student);
                long addEnd = System.nanoTime();
                loggerLinkedList.logAdd(i, addEnd - addStart);
            }
            long totalLinkedListTime = System.nanoTime() - startTime;

            // Выставляем оценки и логируем процесс
            for (Student student : studentsArrayList) {
                Teacher teacher = RussianGenerator.generateRandomTeacher(random.nextInt(5));
                teacher.giveGrades(student);
            }

            // Удаление студентов с логированием
            int studentsToRemove = studentCount / 10;
            for (int i = 0; i < studentsToRemove; i++) {
                int indexToRemove = random.nextInt(studentsArrayList.size());

                // Логируем удаление из ArrayList
                long removeStart = System.nanoTime();
                studentsArrayList.remove(indexToRemove);
                long removeEnd = System.nanoTime();
                loggerArrayList.logRemove(indexToRemove, removeEnd - removeStart);

                // Логируем удаление из LinkedList
                indexToRemove = random.nextInt(studentsLinkedList.size());
                removeStart = System.nanoTime();
                studentsLinkedList.remove(indexToRemove);
                removeEnd = System.nanoTime();
                loggerLinkedList.logRemove(indexToRemove, removeEnd - removeStart);
            }

            // Логирование результатов для каждой коллекции
            loggerArrayList.printLog("ArrayList_Log_" + studentCount + ".txt");
            loggerLinkedList.printLog("LinkedList_Log_" + studentCount + ".txt");

            // Очистка коллекций перед следующим экспериментом
            studentsArrayList.clear();
            studentsLinkedList.clear();
        }
    }
}
