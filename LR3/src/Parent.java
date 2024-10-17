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

    @Override
    public String toString() {
        return "Родитель: " + name + ", Настроение: " + mood + ", Премиальные: " + bonus;
    }
}
