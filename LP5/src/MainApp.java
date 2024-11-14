import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainApp {
    public static void main(String[] args) {
        try {
            List<String> arrayListLogs = List.of(
                    "ArrayList_Log_10.txt",
                    "ArrayList_Log_100.txt",
                    "ArrayList_Log_1000.txt",
                    "ArrayList_Log_10000.txt",
                    "ArrayList_Log_100000.txt"
            );

            List<String> linkedListLogs = List.of(
                    "LinkedList_Log_10.txt",
                    "LinkedList_Log_100.txt",
                    "LinkedList_Log_1000.txt",
                    "LinkedList_Log_10000.txt",
                    "LinkedList_Log_100000.txt"
            );

            LogParser arrayListParser = new LogParser();
            LogParser linkedListParser = new LogParser();

            for (String log : arrayListLogs) {
                arrayListParser.parseLog(log, true);
            }
            for (String log : linkedListLogs) {
                linkedListParser.parseLog(log, false);
            }

            // Average Time Graph
            Map<Integer, Long> avgAddTimes = new HashMap<>(arrayListParser.getAvgAddTimes());
            avgAddTimes.putAll(linkedListParser.getAvgAddTimes());

            Map<Integer, Long> avgRemoveTimes = new HashMap<>(arrayListParser.getAvgRemoveTimes());
            avgRemoveTimes.putAll(linkedListParser.getAvgRemoveTimes());

            new GraphFrame(avgAddTimes, avgRemoveTimes, new HashMap<>(), new HashMap<>(), "Average Time Graph");

            // Total Time Graph
            Map<Integer, Long> totalAddTimes = new HashMap<>(arrayListParser.getTotalAddTimes());
            totalAddTimes.putAll(linkedListParser.getTotalAddTimes());

            Map<Integer, Long> totalRemoveTimes = new HashMap<>(arrayListParser.getTotalRemoveTimes());
            totalRemoveTimes.putAll(linkedListParser.getTotalRemoveTimes());

            new GraphFrame(new HashMap<>(), new HashMap<>(), totalAddTimes, totalRemoveTimes, "Total Time Graph");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
