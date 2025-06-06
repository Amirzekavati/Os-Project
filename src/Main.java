import java.util.ArrayList;
import java.util.List;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        String tasksFile = "C:\\Users\\amirz\\OneDrive\\Documents\\Os-project\\src\\tasks.csv";
        String processorsFile = "C:\\Users\\amirz\\OneDrive\\Documents\\Os-project\\src\\processors.csv";
        List<Task> undoneTasks = ReadTask.readFromFile(tasksFile);
        List<Task> doneTasks = new ArrayList();
        List<Processor> processors = ProcessorReader.readFromFile(processorsFile);
        Schedular taskScheduler = new Schedular(doneTasks, undoneTasks, processors);
        taskScheduler.startWork();
    }
}