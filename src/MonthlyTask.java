import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task{
    public MonthlyTask(String title, String description, LocalDateTime localDateTime, TaskType taskType) {
        super(title, description, localDateTime, taskType);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate taskDate = this.getLocalDateTime().toLocalDate();
        return taskDate.equals(localDate) || taskDate.getDayOfMonth() == localDate.getDayOfMonth();
    }

    @Override
    public Repeatability getRepeatabilityType() {
        return Repeatability.MONTHLY;
    }
}
