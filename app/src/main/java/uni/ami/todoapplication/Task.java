package uni.ami.todoapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Task implements Serializable {
    private Long id;
    private String title;
    private String description;
    private TaskType type;

    public Task(Long id, String title, String description, TaskType type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Заголовок: " + title + '\'' +
                "Описание: " + description + '\'';
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeStringArray(new String[] {id.toString(), title, description});
//
//    }
}
