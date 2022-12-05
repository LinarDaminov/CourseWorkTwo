import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task{
    public DailyTask(String title, String description, LocalDateTime localDateTime, TaskType taskType) {
        super(title, description, localDateTime, taskType);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate taskDate = this.getLocalDateTime().toLocalDate();
        return taskDate.equals(taskDate)||localDate.isAfter(taskDate);
    }

    @Override
    public Repeatability getRepeatabilityType() {
        return Repeatability.DAILY;
    }
}
