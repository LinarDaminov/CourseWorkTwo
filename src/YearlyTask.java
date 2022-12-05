import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task{
    public YearlyTask(String title, String description, LocalDateTime localDateTime, TaskType taskType) {
        super(title, description, localDateTime, taskType);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate taskDate = this.getLocalDateTime().toLocalDate();
        return localDate.equals(taskDate) || localDate.isAfter(taskDate) &&
                localDate.getDayOfMonth() == taskDate.getDayOfMonth()&&
                localDate.getMonth()==taskDate.getMonth();
    }

    @Override
    public Repeatability getRepeatabilityType() {
        return Repeatability.YEARLY;
    }
}
