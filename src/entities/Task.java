package entities;

import enums.Status;
import utils.Validations;

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
        this.status = status;
    }

    @Override
    public String toString() {
        return this.name;
    }
}