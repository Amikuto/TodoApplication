package uni.ami.todoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button personalButton, eduButton, workButton, recrButton;
    public static ArrayList<Task> todoList = new ArrayList<>(Arrays.asList(
            new Task(1L, "Title1", "Desc1", TaskType.PERSONAL),
            new Task(1L, "Title1", "Desc1", TaskType.PERSONAL),
            new Task(1L, "Title1", "Desc1", TaskType.EDU),
            new Task(1L, "Title1", "Desc1", TaskType.WORK),
            new Task(1L, "Title1", "Desc1", TaskType.RECREATION)
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.personalButton).setOnClickListener(this);
        eduButton = findViewById(R.id.eduButton);
        eduButton.setOnClickListener(this);
        workButton = findViewById(R.id.workButton);
        workButton.setOnClickListener(this);
        recrButton = findViewById(R.id.recrButton);
        recrButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, TaskListActivity.class);
        TaskType type = TaskType.PERSONAL;
        switch (view.getId()) {
            case R.id.personalButton:
                type = TaskType.PERSONAL;
                break;
            case R.id.eduButton:
                type = TaskType.EDU;
                break;
            case R.id.workButton:
                type = TaskType.WORK;
                break;
            case R.id.recrButton:
                type = TaskType.RECREATION;
                break;
        }
        intent.putExtra("TASK_TYPE", type);
        final TaskType finalType = type;
//        ArrayList<Task> dtoList = (ArrayList<Task>) todoList
//                .stream().filter(task -> task.getType() == finalType)
//                .collect(Collectors.toList());
        intent.putExtra(
                "TASKS_ARRAY_LIST", todoList
        );
        startActivity(intent);
    }
}