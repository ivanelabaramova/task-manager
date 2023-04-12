import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoryService {
    private String storyName;
    private boolean storyExists;
    private boolean isStoryFound;
    private Story chosenStory;
    private int taskIndex;
    private List<Story> listOfStories = new ArrayList<>();
    private TaskManager taskManager = new TaskManager("", listOfStories);
    private Scanner scanner = new Scanner(System.in);

    public void createStory() {
        System.out.println("How would you like to name your story?");
        storyName = scanner.nextLine();

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
            while (!Story.availablePoints.contains(storyPoints)) {
                System.out.println("Give the story points. The number should be a Fibonacci one " +
                        "in the range 1 to 13 (including).");
                storyPoints = Integer.parseInt(scanner.nextLine());
            }

            Story story = new Story(storyName, storyDescription, storyPoints);
            listOfStories.add(story);

            System.out.println(String.format("Your Story: %s is created.\n", storyName));
        }
    }

    public void addTask() {
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
    }

    public void deleteTask() {
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

            taskIndex = Integer.parseInt(scanner.nextLine());
            chosenStory.deleteTask(taskIndex);
        }
    }

    public void completeTask() {
        System.out.println("Which story would you like to complete a task from?");
        taskManager.printAllStories();

        int storyNumber = Integer.parseInt(scanner.nextLine());
        if (storyNumber > taskManager.getListOfStories().size()) {
            System.err.println("Invalid number.\n");
        } else {
            chosenStory = taskManager.getListOfStories().get(storyNumber - 1);
            System.out.println("Which task would you like to complete?");
            chosenStory.printAllActiveTasks();

            taskIndex = Integer.parseInt(scanner.nextLine());
            chosenStory.completeTask(taskIndex);
        }
    }

    public void printStory() {
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
    }

    public void printAllStories() {
        if (listOfStories.isEmpty()) {
            System.out.println("There are currently no stories to be displayed.\n");
        } else {
            taskManager.printAllStoriesWithTasks();
        }
    }

    public void setTaskManagerName(String taskManagerName) {
        this.taskManager.setName(taskManagerName);
    }
}