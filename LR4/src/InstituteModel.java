import java.io.*;
import java.util.*;

public class InstituteModel {
    private static Properties config = new Properties();
    private static String currentUser;
    private static boolean debugMode;
    private static boolean autoTestMode;

    private static List<Student> studentsArrayList = new ArrayList<>();
    private static Random random = new Random();
    private static OperationLogger logger = new OperationLogger();  // Логгер

    public static void main(String[] args) throws Exception {
        loadConfig();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите логин:");
        String login = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        if (authenticateUser(login, password)) {
            System.out.println("Добро пожаловать, " + currentUser + "!");
            log("Старт программы для пользователя: " + currentUser);

            if (autoTestMode) {
                runAutoTests();
            }

            boolean running = true;
            while (running) {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();  // Очищаем ввод

                switch (choice) {
                    case 1:
                        generateAndSaveStudents();  // Генерация и запись студентов
                        break;
                    case 2:
                        loadStudentsFromFile();  // Чтение студентов из файла
                        break;
                    case 3:
                        if (debugMode) {
                            debugModeOperations();
                        }
                        break;
                    case 0:
                        running = false;
                        log("Завершение программы");
                        break;
                    default:
                        System.out.println("Неверный выбор.");
                }
            }
        } else {
            System.out.println("Неверный логин или пароль.");
            log("Ошибка аутентификации для пользователя: " + login);
        }

        scanner.close();
        logger.printLog("program_log.txt");  // Печатаем логи по завершению программы
    }

    // Загрузка файла настроек
    private static void loadConfig() throws IOException {
        InputStream input = new FileInputStream("config.properties");
        config.load(input);
        currentUser = config.getProperty("user.name");
        debugMode = Boolean.parseBoolean(config.getProperty("debug.mode"));
        autoTestMode = Boolean.parseBoolean(config.getProperty("autotest.mode"));
        input.close();
    }

    // Аутентификация пользователя
    private static boolean authenticateUser(String login, String password) {
        return login.equals(config.getProperty("user.name")) && password.equals(config.getProperty("user.password"));
    }

    // Меню программы
    private static void displayMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Генерация и запись студентов в файл");
        System.out.println("2. Прочитать студентов из файла");
        if (debugMode) {
            System.out.println("3. Отладка");
        }
        System.out.println("0. Выход");
    }

    // Генерация студентов и запись в файл
    private static void generateAndSaveStudents() {
        System.out.println("Введите количество студентов для генерации:");
        Scanner scanner = new Scanner(System.in);
        int studentCount = scanner.nextInt();
        scanner.nextLine();  // Очищаем ввод

        // Генерация студентов
        for (int i = 0; i < studentCount; i++) {
            Student student = RussianGenerator.generateRandomStudent(i);

            // Добавляем оценки по предметам
            student.addGrade("Русский", random.nextInt(3) + 3);
            student.addGrade("Высшая математика", random.nextInt(3) + 3);
            student.addGrade("Программирование на Java", random.nextInt(3) + 3);

            studentsArrayList.add(student);
        }

        try {
            // Логируем операцию записи студентов
            long writeStart = System.nanoTime();
            saveStudentsToFile(studentsArrayList, "studentsArrayList.txt");
            long writeEnd = System.nanoTime();
            logger.logWrite(studentsArrayList.size(), writeEnd - writeStart);
            log("Операция записи студентов в файл.");
        } catch (Exception e) {
            logError("Ошибка при записи студентов в файл: " + e.getMessage());
        }
    }

    // Чтение студентов из файла
    private static void loadStudentsFromFile() {
        try {
            long readStart = System.nanoTime();
            studentsArrayList = loadStudentsFromFile("studentsArrayList.txt");
            long readEnd = System.nanoTime();
            logger.logRead(studentsArrayList.size(), readEnd - readStart);

            // Печать студентов
            for (Student student : studentsArrayList) {
                System.out.println(student);
            }
            log("Операция чтения студентов из файла.");
        } catch (Exception e) {
            logError("Ошибка при чтении студентов из файла: " + e.getMessage());
        }
    }

    // Логирование отладочных операций
    private static void debugModeOperations() {
        System.out.println("Режим отладки активен.");
        log("Запущены операции отладки.");
    }

    // Автотесты
    private static void runAutoTests() {
        System.out.println("Запуск автотестов...");
        log("Запуск автотестов.");
        // Логика автотестов
        log("Автотесты завершены.");
    }

    // Сохранение студентов в файл
    public static void saveStudentsToFile(List<Student> students, String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        for (Student student : students) {
            student.saveToFile(writer);  // Сохранение данных студента
        }
        writer.close();
    }

    // Чтение студентов из файла
    public static List<Student> loadStudentsFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<Student> students = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            students.add(Student.loadFromFile(line));  // Восстановление студента
        }
        reader.close();
        return students;
    }

    // Логирование
    private static void log(String message) {
        try (FileWriter writer = new FileWriter("program.log", true)) {
            writer.write(java.time.LocalDateTime.now() + ": " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void logError(String errorMessage) {
        log("ERROR: " + errorMessage);
        logger.logError(errorMessage);  // Запись ошибки в логгер
    }
}
