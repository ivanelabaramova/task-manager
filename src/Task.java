import java.util.UUID;

public class Task {
    private UUID code;
    private String name;
    private String description;
    private Status status;

    public Task(String name, String description) {
        this.code = UUID.randomUUID();
        setName(name);
        setDescription(description);
        this.status = Status.ACTIVE;
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

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        if (status.equals(Status.ACTIVE)) {
            this.status = Status.ACTIVE;
        } else if (status.equals(Status.CLOSED)) {
            this.status = Status.CLOSED;
        } else {
            System.err.println("Status has to be active or closed.\n");
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}