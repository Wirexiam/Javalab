import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Subject {
    private String name;

    public Subject(String name) {
        this.name = name;
    }

    // Метод для записи предмета в файл
    public void saveToFile(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename, true);  // Открываем файл в режиме добавления
        writer.write("Предмет: " + name + "\n");
        writer.close();
    }

    // Метод для чтения предмета из файла
    public static Subject loadFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        String name = line.split(": ")[1];
        reader.close();
        return new Subject(name);
    }

    @Override
    public String toString() {
        return "Предмет: " + name;
    }
}
