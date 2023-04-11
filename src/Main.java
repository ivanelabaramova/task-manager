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

        while (!command.equalsIgnoreCase("End")) {
            boolean isStoryFound = false;
            boolean storyExists = false;
            Story chosenStory = null;
            int taskNumber = 0;

            switch (command) {
                //Create Story
                case "cs":
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

                        int storyPoints = 0;
                        while(!Story.availablePoints.contains(storyPoints)) {
                            System.out.println("Give the story points. The number should be a Fibonacci one " +
                                    "in the range 1 to 13 (including).");
                            storyPoints = Integer.parseInt(scanner.nextLine());
                        }

                        Story story = new Story(storyName, storyDescription, storyPoints);
                        listOfStories.add(story);

                        System.out.println(String.format("Your Story: %s is created.\n", storyName));
                    }

                    break;
                    //Add Task
                case "at":
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
                        System.err.println("This story does not exist.\n");
                    } else {
                        System.out.println("How would you like to name the task?");
                        String taskName = scanner.nextLine();
                        System.out.println("Give the task a description.");
                        String taskDescription = scanner.nextLine();

                        Task task = new Task(taskName, taskDescription);
                        chosenStory.addTask(task);

                        System.out.println(String.format("Your Task: %s has been successfully added in Story: %s.\n",
                                taskName, chosenStory.getName()));
                    }
                    break;
                    //Delete Task
                case "dt":
                    System.out.println("What story would you like to delete a task from?");
                    storyName = scanner.nextLine();

                    for (Story s : listOfStories) {
                        if (s.getName().equals(storyName)) {
                            isStoryFound = true;
                            chosenStory = s;
                            break;
                        }
                    }

                    if (!isStoryFound) {
                        System.err.println("This story does not exist.\n");
                    } else {
                        System.out.println("Which task would you like to delete?");
                        chosenStory.printAllTasks();

                        taskNumber = Integer.parseInt(scanner.nextLine());
                        if (taskNumber > chosenStory.getListOfTasks().size()) {
                            System.err.println("Invalid number.\n");
                        } else {
                            Task removedTask = chosenStory.getListOfTasks().remove(taskNumber - 1);
                            System.out.println(String.format("Task: %s has been successfully deleted.\n",
                                    removedTask.getName()));
                        }
                    }
                    break;
                    //Complete Task
                case "ct":
                    System.out.println("Which story would you like to complete a task from?");
                    taskManager.printAllStories();

                    int storyNumber = Integer.parseInt(scanner.nextLine());
                    if (storyNumber > taskManager.getListOfStories().size()) {
                        System.err.println("Invalid number.\n");
                    } else {
                        chosenStory = taskManager.getListOfStories().get(storyNumber - 1);
                        System.out.println("Which task would you like to complete?");
                        chosenStory.printAllActiveTasks();

                        taskNumber = Integer.parseInt(scanner.nextLine());
                        if (taskNumber > chosenStory.getListOfTasks().size()) {
                            System.err.println("Invalid number.\n");
                        } else {
                            Task taskToComplete = chosenStory.getListOfTasks().get(taskNumber - 1);
                            taskToComplete.setStatus(Status.CLOSED);
                            System.out.println(String.format("Task %s has been successfully completed.\n",
                                    taskToComplete.getName()));
                        }
                    }
                    break;
                    //Print Story
                case "ps":
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
                        System.err.println("This story does not exist.\n");
                    } else {
                        if (chosenStory.getListOfTasks().isEmpty()) {
                            System.out.println("There are currently no tasks in this story.\n");
                        } else {
                            chosenStory.printAllTasks();
                        }
                    }

                    break;
                    //Print All Stories
                case "pas":
                    if (listOfStories.isEmpty()) {
                        System.out.println("There are currently no stories to be displayed.\n");
                    } else {
                        taskManager.printAllStories();
                    }
                    break;
                default:
                    System.err.println("Invalid command.\n");
            }

            System.out.println("What would you like to do?");
            System.out.println(Commands.showAllCommands());
            command = scanner.nextLine();
        }
    }
}