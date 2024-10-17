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
    private int readCount;
    private int writeCount;
    private long totalReadTime;
    private long totalWriteTime;

    public OperationLogger() {
        logs = new ArrayList<>();
    }

    public void logAdd(int id, long time) {
        logs.add("add, ID = " + id + ", " + time + " ns");
        totalAddTime += time;
        addCount++;
    }

    public void logRemove(int id, long time) {
        logs.add("remove, ID = " + id + ", " + time + " ns");
        totalRemoveTime += time;
        removeCount++;
    }

    public void logRead(int id, long time) {
        logs.add("read, ID = " + id + ", " + time + " ns");
        totalReadTime += time;
        readCount++;
    }

    public void logWrite(int id, long time) {
        logs.add("write, ID = " + id + ", " + time + " ns");
        totalWriteTime += time;
        writeCount++;
    }

    public void logError(String errorMessage) {
        logs.add("ERROR: " + errorMessage);
    }

    public void printLog(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write("Start program: " + java.time.LocalDateTime.now() + "\n");
        for (String log : logs) {
            writer.write(log + "\n");
        }
        writer.write("addTotalCount = " + addCount + "\n");
        writer.write("addTotalTime = " + totalAddTime + " ns\n");
        writer.write("addMedianTime = " + (addCount > 0 ? totalAddTime / addCount : 0) + " ns\n");
        writer.write("removeTotalCount = " + removeCount + "\n");
        writer.write("removeTotalTime = " + totalRemoveTime + " ns\n");
        writer.write("removeMedianTime = " + (removeCount > 0 ? totalRemoveTime / removeCount : 0) + " ns\n");
        writer.write("readTotalCount = " + readCount + "\n");
        writer.write("readTotalTime = " + totalReadTime + " ns\n");
        writer.write("writeTotalCount = " + writeCount + "\n");
        writer.write("writeTotalTime = " + totalWriteTime + " ns\n");
        writer.write("Finish program: " + java.time.LocalDateTime.now() + "\n");
        writer.close();
    }
}
