import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Story> listOfStories = new ArrayList<>();

        System.out.println("Welcome to your Task Manager. How would you like to name it?");
        String taskManagerName = scanner.nextLine();
        TaskManager taskManager = new TaskManager(taskManagerName, listOfStories);

        String command = "";
        System.out.println("What would you like to do?");
        System.out.println(Commands.showAllCommands());
        command = scanner.nextLine();

        while (!command.equals("End")) {
            boolean isStoryFound = false;
            boolean storyExists = false;
            Story chosenStory = null;

            switch (command) {
                case "Create Story":
                    System.out.println("How would you like to name your story?");
                    String storyName = scanner.nextLine();

                    for (Story story : listOfStories) {
                        if (story.getName().equals(storyName)) {
                            System.err.println("This story already exists.\n");
                            storyExists = true;
                            break;
                        }
                    }

                    if (!storyExists) {
                        System.out.println("Give the story a description.");
                        String storyDescription = scanner.nextLine();

                        System.out.println("Give the story points. The number should be a Fibonacci one in the range " +
                                "1 to 13 (including).");
                        int storyPoints = Integer.parseInt(scanner.nextLine());

                        Story story = new Story(storyName, storyDescription, storyPoints);
                        listOfStories.add(story);

                        System.out.println(String.format("Your Story: %s is created.\n", storyName));
                    }

                    break;
                case "Add Task":
                    System.out.println("What story would you like to add a task in?");
                    storyName = scanner.nextLine();

                    for (Story s : listOfStories) {
                        if (s.getName().equals(storyName)) {
                            isStoryFound = true;
                            chosenStory = s;
                            break;
                        }
                    }

                    if (!isStoryFound) {
                        throw new IllegalArgumentException("This story does not exist.\n");
                    } else {
                        System.out.println("How would you like to name the task?");
                        String taskName = scanner.nextLine();
                        System.out.println("Give the task a description.");
                        String taskDescription = scanner.nextLine();

                        Task task = new Task(taskName, taskDescription);
                        chosenStory.addTask(task);

                        System.out.println(String.format("Your task: %s is added in Story: %s.\n",
                                taskName, chosenStory.getName()));
                    }
                    break;
                case "Remove Task":
                    System.out.println("What story would you like to remove a task from?");
                    storyName = scanner.nextLine();

                    for (Story s : listOfStories) {
                        if (s.getName().equals(storyName)) {
                            isStoryFound = true;
                            chosenStory = s;
                            break;
                        }
                    }

                    if (!isStoryFound) {
                        throw new IllegalArgumentException("This story does not exist.\n");
                    } else {
                        System.out.println("Which task would you like to remove?");
                        String taskName = scanner.nextLine();
                        boolean isTaskFound = false;
                        Task taskToBeRemoved = null;

                        for (Task task : chosenStory.getListOfTasks()) {
                            if (task.getName().equals(taskName)) {
                                isTaskFound = true;
                                taskToBeRemoved = task;
                                break;
                            }
                        }

                        if (!isTaskFound) {
                            throw new IllegalArgumentException("This task does not exist.\n");
                        } else {
                            chosenStory.removeTask(taskToBeRemoved);
                            System.out.println(String.format("Task %s removed.\n", taskName));
                        }
                    }
                    break;
                case "Print Story":
                    System.out.println("Which story would you like to print?");
                    storyName = scanner.nextLine();

                    for (Story story1 : listOfStories) {
                        if (story1.getName().equals(storyName)) {
                            isStoryFound = true;
                            chosenStory = story1;
                            break;
                        }
                    }

                    if (!isStoryFound) {
                        throw new IllegalArgumentException("This story does not exist.\n");
                    } else {
                        if (chosenStory.getListOfTasks().isEmpty()) {
                            System.out.println("There are currently no tasks in this story.\n");
                        } else {
                            chosenStory.printAllTasks();
                        }
                    }

                    break;
                case "Print All Stories":
                    if (listOfStories.isEmpty()) {
                        System.out.println("There are currently no stories to be displayed.\n");
                    } else {
                        taskManager.printAllStories();
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid command.\n");
            }

            System.out.println("What would you like to do?");
            System.out.println(Commands.showAllCommands());
            command = scanner.nextLine();
        }
    }
}