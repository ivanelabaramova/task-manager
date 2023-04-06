import java.util.*;

public class Story {
    private UUID code;
    private String name;
    private String description;
    private Status status;
    private Integer points;
    private List<Task> listOfTasks;
    public static final List<Integer> availablePoints = Arrays.asList(1, 2, 3, 5, 8, 13);

    public Story(String name, String description, Integer points) {
        this.code = UUID.randomUUID();
        setName(name);
        setDescription(description);
        this.status = Status.ACTIVE;
        setPoints(points);
        this.listOfTasks = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        Validations.isNullOrEmpty(name);
        this.name = name;
    }

    public void setDescription(String description) {
        Validations.isNullOrEmpty(description);
        this.description = description;
    }

    public void setStatus(Status status) {
        if (status.name().equals("active")) {
            this.status = Status.ACTIVE;
        } else if (status.name().equals("closed")) {
            this.status = Status.CLOSED;
        } else {
            System.err.println("Status has to be active or closed.\n");
        }
    }

    public void setPoints(Integer points) {
        if (availablePoints.contains(points)) {
            this.points = points;
        } else {
            System.err.println("Points have to be a Fibonacci number between 1 and 13 (including).\n");
        }
    }

    public List<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    public void addTask(Task task) {
        if (!listOfTasks.contains(task)) {
            listOfTasks.add(task);
        } else {
            System.err.println("This task is already in the list.\n");
        }
    }

    public Task removeTask(Task task) {
        if (listOfTasks.contains(task)) {
            listOfTasks.remove(task);
            return task;
        } else {
            System.err.println("This task does not exist in the list.\n");
            return null;
        }
    }

    public void printAllTasks() {
        listOfTasks.forEach(System.out::println);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("Story ")
                .append(this.name)
                .append(": ")
                .append(System.lineSeparator());

        for (Task task : listOfTasks) {
            sb
                    .append("- ")
                    .append(task)
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}