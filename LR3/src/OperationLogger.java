import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OperationLogger {
    private List<String> logs;
    private long totalAddTime;
    private long totalRemoveTime;
    private int addCount;
    private int removeCount;

    public OperationLogger() {
        logs = new ArrayList<>();
    }

    public void logAdd(int id, long time) {
        logs.add("add, ID = " + id + ", " + time);
        totalAddTime += time;
        addCount++;
    }

    public void logRemove(int id, long time) {
        logs.add("remove, ID = " + id + ", " + time);
        totalRemoveTime += time;
        removeCount++;
    }

    public void printLog(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write("Start program: " + java.time.LocalDateTime.now() + "\n");
        for (String log : logs) {
            writer.write(log + "\n");
        }
        writer.write("addTotalCount = " + addCount + "\n");
        writer.write("addTotalTime = " + totalAddTime + "\n");
        writer.write("addMedianTime = " + (addCount > 0 ? totalAddTime / addCount : 0) + "\n");
        writer.write("removeTotalCount = " + removeCount + "\n");
        writer.write("removeTotalTime = " + totalRemoveTime + "\n");
        writer.write("removeMedianTime = " + (removeCount > 0 ? totalRemoveTime / removeCount : 0) + "\n");
        writer.write("Finish program: " + java.time.LocalDateTime.now() + "\n");
        writer.close();
    }
}
