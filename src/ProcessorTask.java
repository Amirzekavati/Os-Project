import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

class ProcessorTask implements Runnable {
    private final Processor processor;
    public static List<Task> doneTasks;
    public static List<Task> undoneTasks;
    private static Semaphore write = new Semaphore(1);

    public ProcessorTask(Processor processor, List<Task> undoneTasks, List<Task> doneTasks) {
        this.processor = processor;
        ProcessorTask.doneTasks = doneTasks;
        ProcessorTask.undoneTasks = undoneTasks;
        undoneTasks.sort(new Task.TaskComparator());
    }

    public void run() {
        while(true) {
            try {
                write.acquire();
                System.out.println("Done tasks: ");
                Iterator<Task> var1 = doneTasks.iterator();

                Task undoneTask;
                while(var1.hasNext()) {
                    undoneTask = (Task)var1.next();
                    System.out.print(undoneTask.getId() + " ");
                }

                System.out.println();
                System.out.print("Undone tasks: ");
                var1 = undoneTasks.iterator();

                while(var1.hasNext()) {
                    undoneTask = (Task)var1.next();
                    if (!doneTasks.contains(undoneTask)) {
                        System.out.print(undoneTask.getId() + " ");
                    }
                }

                System.out.println();
                write.release();
                Task selectedTask = this.bestTask();
                if (selectedTask != null) {
                    PrintStream var10000 = System.out;
                    long var10001 = this.processor.id();
                    var10000.println("Processor " + var10001 + " is executing task " + selectedTask.getId() + " at time " + TimerManager.getInstance().getTimer());

                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException var3) {
                        var3.printStackTrace();
                    }

                    selectedTask.setEnd(TimerManager.getInstance().getTimer());
                    var10000 = System.out;
                    var10001 = this.processor.id();
                    var10000.println("Processor " + var10001 + " executed task " + selectedTask.getId() + ".\nStart: " + selectedTask.getStart() + " End: " + selectedTask.getEnd());
                    doneTasks.add(selectedTask);
                    selectedTask.getTaskSemaphore().release();
                }
            } catch (InterruptedException var4) {
                var4.printStackTrace();
            }
        }
    }

    private Task bestTask() throws InterruptedException {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException var6) {
            var6.printStackTrace();
        }

        Task selectTask = null;
        Iterator var2 = undoneTasks.iterator();

        while(var2.hasNext()) {
            Task task = (Task)var2.next();
            if (this.canExecute(task)) {
                if (this.checkDeadline(task)) {
                    System.out.println("Task " + task.getId() + " couldn't be executed because its deadline over!");
                } else if (this.checkArrivalTime(task)) {
                    System.out.println("Task " + task.getId() + " can't be executed because its arrivalTime is not reach yet!");
                } else {
                    long time = TimerManager.getInstance().getTimer();
                    if (time + 1L < task.getDeadline()) {
                        task.getTaskSemaphore().acquire();
                        if (!doneTasks.contains(task)) {
                            selectTask = task;
                            task.setStart(time);
                            task.setProcessor_id(this.processor.id());
                            break;
                        }
                    } else {
                        System.out.println("Task " + task.getId() + " couldn't be executed because its runtime over from deadline!");
                    }
                }
            } else {
                PrintStream var10000 = System.out;
                long var10001 = this.processor.id();
                var10000.println("The processor " + var10001 + " doesn't has a requirement features for task " + task.getId() + "!!");
            }
        }

        return selectTask;
    }

    private boolean checkArrivalTime(Task task) {
        return task.getArrivalTime() > TimerManager.getInstance().getTimer();
    }

    private boolean checkDeadline(Task task) {
        return task.getDeadline() <= TimerManager.getInstance().getTimer();
    }

    private boolean canExecute(Task task) {
        return task.getCache_requirement() <= this.processor.cache() && task.getFrequency_requirement() <= this.processor.frequency() && task.getMemory_requirement() <= this.processor.memory();
    }
}
