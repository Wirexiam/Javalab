import java.util.Random;

public class RussianGenerator {
    private static final Random random = new Random();
    private static final String[] maleNames = {"Алексей", "Дмитрий", "Евгений", "Иван", "Павел"};
    private static final String[] femaleNames = {"Анастасия", "Екатерина", "Мария", "Ольга", "Светлана"};
    private static final String[] subjects = {"Математика", "Физика", "Химия", "Информатика", "Литература"};
    private static final String[] parentNames = {"Владимир", "Наталья", "Сергей", "Оксана", "Юрий"};

    public static Student generateRandomStudent(int id) {
        String gender = random.nextBoolean() ? "мужской" : "женский";
        String name = (gender.equals("мужской")) ? maleNames[random.nextInt(maleNames.length)]
                : femaleNames[random.nextInt(femaleNames.length)];
        int age = random.nextInt(8) + 18; // Возраст от 18 до 25
        return new Student(name, age, gender);
    }

    public static Parent generateRandomParent(int id) {
        String name = parentNames[random.nextInt(parentNames.length)];
        return new Parent(name);
    }

    public static Subject generateRandomSubject(int id) {
        String name = subjects[random.nextInt(subjects.length)];
        return new Subject(name);
    }

    // Добавим метод для генерации русскоязычных преподавателей
    public static Teacher generateRandomTeacher(int id) {
        String name = random.nextBoolean() ? maleNames[random.nextInt(maleNames.length)]
                : femaleNames[random.nextInt(femaleNames.length)];
        String subject = subjects[random.nextInt(subjects.length)];
        return new Teacher(name, subject);
    }

    public static int generateRandomGrade() {
        return random.nextInt(3) + 3; // Оценки от 3 до 5
    }
}
