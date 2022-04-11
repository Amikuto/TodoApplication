package uni.ami.todoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button personalButton, eduButton, workButton, restButton;
    ArrayList<Task> mNameList = new ArrayList<>(Arrays.asList(
            new Task(1L, "Title1", "Desc1")
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.personalButton).setOnClickListener(this);
        eduButton = findViewById(R.id.eduButton);
        workButton = findViewById(R.id.workButton);
        restButton = findViewById(R.id.restButton);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, TaskListActivity.class);
        intent.putExtra("TASKS_ARRAY_LIST", mNameList);
        startActivity(intent);
    }
}