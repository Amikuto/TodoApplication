package uni.ami.todoapplication;

import java.io.Serializable;

public enum TaskType implements Serializable {
    PERSONAL("Личные"),
    EDU("Учеба"),
    WORK("Работа"),
    RECREATION("Отдых");
    private final String description;

    TaskType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
