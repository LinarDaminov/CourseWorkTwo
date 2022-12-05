import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task{
   public WeeklyTask(String title, String description, LocalDateTime localDateTime, TaskType taskType) {
        super(title, description, localDateTime, taskType);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate taskDate = this.getLocalDateTime().toLocalDate();
        return localDate.equals(taskDate) || localDate.isAfter(taskDate) &&
                localDate.getDayOfWeek().equals(taskDate.getDayOfWeek());
    }

    @Override
    public Repeatability getRepeatabilityType() {
        return Repeatability.WEEKLY;
    }
}
