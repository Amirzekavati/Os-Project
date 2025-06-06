//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Comparator;
import java.util.concurrent.Semaphore;

class Task {
    private final long id;
    private final long arrivalTime;
    private long start;
    private long end;
    private long processor_id;
    private final long deadline;
    private final long value;
    private final long cache_requirement;
    private final long memory_requirement;
    private final long frequency_requirement;
    private Semaphore taskSemaphore = new Semaphore(1);

    public Task(long id, long arrivalTime, long deadline, long value, long cache_requirement, long memory_requirement, long frequency_requirement) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.deadline = deadline;
        this.value = value;
        this.cache_requirement = cache_requirement;
        this.memory_requirement = memory_requirement;
        this.frequency_requirement = frequency_requirement;
    }

    public Semaphore getTaskSemaphore() {
        return this.taskSemaphore;
    }

    public long sumFeatures() {
        return this.getMemory_requirement() + this.getFrequency_requirement() + this.getCache_requirement();
    }

    public long getId() {
        return this.id;
    }

    public long getArrivalTime() {
        return this.arrivalTime;
    }

    public long getDeadline() {
        return this.deadline;
    }

    public long getValue() {
        return this.value;
    }

    public long getCache_requirement() {
        return this.cache_requirement;
    }

    public long getMemory_requirement() {
        return this.memory_requirement;
    }

    public long getFrequency_requirement() {
        return this.frequency_requirement;
    }

    public long getStart() {
        return this.start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return this.end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public void setProcessor_id(long processor_id) {
        this.processor_id = processor_id;
    }

    public long getProcessor_id() {
        return this.processor_id;
    }

    public static class TaskComparator implements Comparator<Task> {
        public TaskComparator() {
        }

        public int compare(Task o1, Task o2) {
            return o1.getValue() != o2.getValue() ? Long.compare(o2.getValue(), o1.getValue()) : Long.compare(o2.sumFeatures(), o1.sumFeatures());
        }
    }
}
