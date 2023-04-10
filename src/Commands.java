import java.util.Arrays;
import java.util.List;

public class Commands {
    private static List<String> commands = Arrays.asList(
            "- Create Story - cs",
            "- Add Task - at",
            "- Delete Task - dt",
            "- Print Story - ps",
            "- Print All Stories - pas",
            "- End");

    public static String showAllCommands() {
        StringBuilder sb = new StringBuilder();
        for (String command : commands) {
            sb
                    .append(command)
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}