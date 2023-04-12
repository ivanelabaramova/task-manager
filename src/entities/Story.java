package entities;

import enums.Status;
import utils.Validations;

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
        this.status = status;
    }

    public Integer getPoints() {
        return this.points;
    }

    public void setPoints(Integer points) {
        if (availablePoints.contains(points)) {
            this.points = points;
        } else {
            System.err.println("Invalid number.");
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

    public Task deleteTask(int index) {
        if (index > listOfTasks.size()) {
            System.err.println("Invalid number.\n");;
            return null;
        } else {
            Task deletedTask = listOfTasks.remove(index - 1);
            System.out.println(String.format("Task: %s has been successfully deleted.\n", deletedTask.getName()));
            return deletedTask;
        }
    }

    public void completeTask(int index) {
        if (index > listOfTasks.size()) {
            System.err.println("Invalid number.\n");
        } else {
            Task taskToComplete = listOfTasks.get(index - 1);
            taskToComplete.setStatus(Status.CLOSED);
            System.out.println(String.format("Task: %s has been successfully completed.\n", taskToComplete.getName()));
        }
    }

    public void printAllTasks() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < listOfTasks.size(); i++) {
            sb
                    .append(i + 1)
                    .append(". ")
                    .append(listOfTasks.get(i))
                    .append(System.lineSeparator());
        }

        System.out.println(sb);
    }

    public void printAllActiveTasks() {
        StringBuilder sb = new StringBuilder();
        List<Task> activeTasks = new ArrayList<>();

        for (Task task : listOfTasks) {
            if (task.getStatus().equals(Status.ACTIVE)) {
                activeTasks.add(task);
            }
        }

        for (int i = 0; i < activeTasks.size(); i++) {
            Task currentTask = activeTasks.get(i);
            sb
                    .append(i + 1)
                    .append(". ")
                    .append(currentTask)
                    .append(System.lineSeparator());
        }

        System.out.println(sb);
    }

    @Override
    public String toString() {
        return this.name;
    }
}