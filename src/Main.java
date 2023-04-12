import services.StoryService;
import utils.Commands;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StoryService storyService = new StoryService();

        System.out.println("Welcome to your Task Manager. How would you like to name it?");
        String taskManagerName = scanner.nextLine();
        storyService.setTaskManagerName(taskManagerName);

        String command = "";
        System.out.println("What would you like to do?");
        System.out.println(Commands.showAllCommands());
        command = scanner.nextLine();

        while (!command.equalsIgnoreCase("End")) {
            switch (command) {
                case "cs" -> storyService.createStory();
                case "at" -> storyService.addTask();
                case "dt" -> storyService.deleteTask();
                case "ct" -> storyService.completeTask();
                case "ps" -> storyService.printStory();
                case "pas" -> storyService.printAllStories();
                default -> System.err.println("Invalid command.\n");
            }

            System.out.println("What would you like to do?");
            System.out.println(Commands.showAllCommands());
            command = scanner.nextLine();
        }
    }
}