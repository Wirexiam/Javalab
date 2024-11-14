import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogParser {
    private Map<Integer, Long> totalAddTimes = new HashMap<>();
    private Map<Integer, Long> totalRemoveTimes = new HashMap<>();
    private Map<Integer, Long> avgAddTimes = new HashMap<>();
    private Map<Integer, Long> avgRemoveTimes = new HashMap<>();

    public void parseLog(String filePath, boolean isArrayList) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int elementCount = extractElementCount(filePath);

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("addTotalTime")) {
                    long time = Long.parseLong(line.split(" = ")[1].trim());
                    totalAddTimes.put(elementCount, time);
                } else if (line.startsWith("removeTotalTime")) {
                    long time = Long.parseLong(line.split(" = ")[1].trim());
                    totalRemoveTimes.put(elementCount, time);
                } else if (line.startsWith("addMedianTime")) {
                    long time = Long.parseLong(line.split(" = ")[1].trim());
                    avgAddTimes.put(elementCount, time);
                } else if (line.startsWith("removeMedianTime")) {
                    long time = Long.parseLong(line.split(" = ")[1].trim());
                    avgRemoveTimes.put(elementCount, time);
                }
            }
        }
    }

    private int extractElementCount(String filePath) {
        String[] parts = filePath.split("_");
        return Integer.parseInt(parts[parts.length - 1].replace(".txt", ""));
    }

    public Map<Integer, Long> getTotalAddTimes() {
        return totalAddTimes;
    }

    public Map<Integer, Long> getTotalRemoveTimes() {
        return totalRemoveTimes;
    }

    public Map<Integer, Long> getAvgAddTimes() {
        return avgAddTimes;
    }

    public Map<Integer, Long> getAvgRemoveTimes() {
        return avgRemoveTimes;
    }
}
