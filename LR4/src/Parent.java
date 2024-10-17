import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Parent {
    private String name;
    private String mood;
    private double bonus;

    public Parent(String name) {
        this.name = name;
    }

    public void calculateBonus(double averageGrade) {
        if (averageGrade >= 3.0 && averageGrade < 4.0) {
            mood = "Хмурый";
            bonus = 0;
        } else if (averageGrade >= 4.0 && averageGrade <= 4.5) {
            mood = "Удовлетворенный";
            bonus = 5000;
        } else if (averageGrade > 4.5) {
            mood = "Радостный";
            bonus = 10000;
        }
    }

    // Метод для записи родителя в файл
    public void saveToFile(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename, true);  // Открываем файл в режиме добавления
        writer.write("Родитель: " + name + ", Настроение: " + mood + ", Премиальные: " + bonus + "\n");
        writer.close();
    }

    // Метод для чтения родителя из файла
    public static Parent loadFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        String[] parts = line.split(", ");
        String name = parts[0].split(": ")[1];
        reader.close();
        return new Parent(name);
    }

    @Override
    public String toString() {
        return "Родитель: " + name + ", Настроение: " + mood + ", Премиальные: " + bonus;
    }
}
