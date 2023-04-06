import java.util.List;

public class TaskManager {
    private String name;
    private List<Story> listOfStories;

    public TaskManager(String name, List<Story> listOfStories) {
        this.name = name;
        this.listOfStories = listOfStories;
    }

    public void addStory(Story story) {
        listOfStories.add(story);
    }

    public void printAllStories() {
        listOfStories.forEach(System.out::println);
    }
}