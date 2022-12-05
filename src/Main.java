
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    private static final Schedule SCHEDULE = new Schedule();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SCHEDULE.addTask(new SingleTask("Test","Test",LocalDateTime.now().plusHours(4),TaskType.PERSONAL));
        SCHEDULE.addTask(new WeeklyTask("Test","Test",LocalDateTime.now().plusHours(1),TaskType.WORK));
        SCHEDULE.addTask(new MonthlyTask("Test","Test",LocalDateTime.now().plusHours(6),TaskType.PERSONAL));
        SCHEDULE.addTask(new YearlyTask("Test","Test",LocalDateTime.now().plusHours(9),TaskType.WORK));
        SCHEDULE.addTask(new SingleTask("Test","Test",LocalDateTime.now().plusHours(2),TaskType.PERSONAL));


    }

    private static void addTask(Scanner scanner) {
        String title = readString("Введите название задачи:", scanner);
        String description = readString("введите описание задачи:", scanner);
        LocalDateTime taskDate = readDateTime(scanner);
        TaskType taskType = readType(scanner);
        Repeatability repeatability = readRepeatability(scanner);
        Task task = switch (repeatability) {
            case SINGLE -> new SingleTask(title,description,taskDate,taskType);
            case DAILY -> new DailyTask(title, description, taskDate, taskType);
            case WEEKLY -> new WeeklyTask(title, description, taskDate, taskType);
            case MONTHLY -> new MonthlyTask(title,description,taskDate,taskType);
            case YEARLY -> new YearlyTask(title,description,taskDate,taskType);
        };
        SCHEDULE.addTask(task);
    }

    private static Repeatability readRepeatability(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Выберите тип повторяемости:");
                for (Repeatability repeatability : Repeatability.values()) {
                    System.out.println(repeatability.ordinal() + "." + localizeRepeatability(repeatability));
                }
                System.out.println("Выберите тип задачи:");
                String ordinalLine = scanner.nextLine();
                int ordinal = Integer.parseInt(ordinalLine);
                return Repeatability.values()[ordinal];
            } catch (NumberFormatException e) {
                System.out.println("Введен неверный номер типа задачи ");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Тип задачи не найден");
            }
        }
    }

    private static TaskType readType(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Выберите тип задачи:");
                for (TaskType taskType : TaskType.values()) {
                    System.out.println(taskType.ordinal() + "." + localizeType(taskType));
                }
                System.out.println("Выберите тип задачи:");
                String ordinalLine = scanner.nextLine();
                int ordinal = Integer.parseInt(ordinalLine);
                return TaskType.values()[ordinal];
            } catch (NumberFormatException e) {
                System.out.println("Введен неверный номер типа задачи ");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Тип задачи не найден");
            }
        }
    }

    private static LocalDateTime readDateTime(Scanner scanner) {
        LocalDate localDate = readDate(scanner);
        LocalTime localTime = readTime(scanner);
        return localDate.atTime(localTime);
    }


    private static String readString(String message, Scanner scanner) {
        while (true) {
                System.out.print(message);
                String readString = scanner.nextLine();
            if (readString == null || readString.isBlank()) {
                System.out.println("Введено пустое значение ");
            } else {
                return readString;
            }
        }
    }

        private static void removeTask(Scanner scanner) {
        System.out.println("Все задачи:");
        for (Task task : SCHEDULE.getAllTask()) {
            System.out.printf("%d.%s[%s](%d)%n",
                    task.getId(),
                    task.getTitle(),
                    localizeType(task.getTaskType()),
                    localizeRepeatability(task.getRepeatabilityType()));
        }
        while (true) {
            try {
                System.out.print("Выберите задачу для удаления:");
                String idLine = scanner.nextLine();
                int id = Integer.parseInt(idLine);
                SCHEDULE.removeTask(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ВВеден неверный id задачи");
            }
        }
    }
    private static void printTaskForDate(Scanner scanner) {
        LocalDate localDate = readDate(scanner);
        Collection<Task> taskForDate = SCHEDULE.getTaskForDate(localDate);
        System.out.println("Задачи на" + localDate.format(DATE_FORMAT));
        for (Task task : taskForDate) {
            System.out.printf("[%s]%s:%s(%s)%n",
                    localizeType(task.getTaskType()),
                    task.getTitle(),
                    task.getLocalDateTime().format(TIME_FORMAT),
                    task.getDescription());
        }
    }

    private static LocalDate readDate(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите дату:");
                String dateLine = scanner.nextLine();
                return LocalDate.parse(dateLine, DATE_FORMAT);

            } catch (DateTimeParseException e) {
                System.out.println("Введена дата в неверном формате");
            }
    }
    }
    private static LocalTime readTime(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите дату:");
                String dateLine = scanner.nextLine();
                return LocalTime.parse(dateLine, TIME_FORMAT);

            } catch (DateTimeParseException e) {
                System.out.println("Введена дата в неверном формате");
            }
        }
    }
        private static String localizeType (TaskType taskType){
            return switch (taskType) {
                case WORK -> "Рабочая задача";
                case PERSONAL -> "Личная задача";
            };
    }

    private static String localizeRepeatability(Repeatability repeatability) {
        return switch (repeatability) {

            case SINGLE -> "Разовая";
            case DAILY -> "Ежедневная";
            case WEEKLY -> "Еженедельная";
            case MONTHLY -> "Ежемесячная";
            case YEARLY -> "Ежегодная";
        };
    }
}



