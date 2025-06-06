import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class TimerManager {
    private static TimerManager instance;
    private final ScheduledExecutorService timerExecutor = Executors.newScheduledThreadPool(1);
    private long timer = 0L;

    private TimerManager() {
        this.startTimer();
    }

    public static synchronized TimerManager getInstance() {
        if (instance == null) {
            instance = new TimerManager();
        }

        return instance;
    }

    private void startTimer() {
        this.timerExecutor.scheduleAtFixedRate(() -> {
            ++this.timer;
            System.out.println("Timer " + this.timer + " seconds.");
        }, 0L, 1L, TimeUnit.SECONDS);
    }

    public long getTimer() {
        return this.timer;
    }
}
