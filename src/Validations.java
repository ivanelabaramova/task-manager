public class Validations {

    public static void isNullOrEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be null, empty or containing only whitespaces.");
        }
    }
}