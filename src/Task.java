import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Task {
    private static int counter = 0;
    private final int id;
    private final String title;
    private final String description;

    private final LocalDateTime localDateTime;
    private final TaskType taskType;

    public Task(String title, String description, LocalDateTime localDateTime, TaskType taskType) {
        this.id = counter++;
        this.title = title;
        this.description = description;
        this.localDateTime = localDateTime;
        this.taskType = taskType;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public TaskType getTaskType() {
        return taskType;
    }


    public abstract boolean appearsIn(LocalDate localDate);

    public abstract Repeatability getRepeatabilityType();

}
