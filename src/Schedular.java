//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class Schedular {
    public static List<Task> undoneTasks;
    public static List<Task> doneTasks;
    private final List<Processor> processors;
    private ThreadPoolExecutor executor;

    public Schedular(List<Task> doneTasks, List<Task> undoneTasks, List<Processor> processors) {
        Schedular.undoneTasks = undoneTasks;
        Schedular.doneTasks = doneTasks;
        this.processors = processors;
        this.executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(processors.size());
        TimerManager.getInstance();
    }

    public void startWork() {
        Iterator var1 = this.processors.iterator();

        while(var1.hasNext()) {
            Processor processor = (Processor)var1.next();
            this.executor.execute(new ProcessorTask(processor, undoneTasks, doneTasks));
        }

        this.executor.shutdown();
    }
}
