package entities;

import utils.Validations;

import java.util.List;

public class TaskManager {
    private String name;
    private List<Story> listOfStories;

    public TaskManager(String name, List<Story> listOfStories) {
        this.name = name;
        this.listOfStories = listOfStories;
    }

    public void setName(String name) {
        Validations.isNullOrEmpty(name);
        this.name = name;
    }

    public List<Story> getListOfStories() {
        return this.listOfStories;
    }

    public void addStory(Story story) {
        listOfStories.add(story);
    }

    public void printAllStories() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < listOfStories.size(); i++) {
            sb
                    .append(i + 1)
                    .append(". ")
                    .append(listOfStories.get(i))
                    .append(System.lineSeparator());
        }

        System.out.println(sb);
    }

    public void printAllStoriesWithTasks() {
        StringBuilder sb = new StringBuilder();

        for (Story story : listOfStories) {
            sb
                    .append("Story ")
                    .append(story.getName())
                    .append(": ")
                    .append(System.lineSeparator());

            for (Task task : story.getListOfTasks()) {
                sb
                        .append("- ")
                        .append(task)
                        .append(System.lineSeparator());
            }
        }

        System.out.println(sb);
    }
}