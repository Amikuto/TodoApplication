package uni.ami.todoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class TaskListActivity extends AppCompatActivity implements View.OnClickListener {

    Button addNewTaskButton;
    ListView tasksListView;
    ArrayAdapter<Task> mainArrayAdapter;
    ArrayList<Task> tasksList;
    TextView taskTypeText;
    TaskType taskType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        tasksListView = findViewById(R.id.tasksListView);
        taskTypeText = findViewById(R.id.taskTypeText);
        addNewTaskButton = findViewById(R.id.addNewTaskButton);
        addNewTaskButton.setOnClickListener(this);

        Intent i = getIntent();
        taskType = (TaskType) i.getSerializableExtra("TASK_TYPE");
        tasksList = (ArrayList<Task>) MainActivity.todoList.stream().filter(task -> task.getType() == taskType).collect(Collectors.toList());
//        mNameList = (ArrayList<Task>) i.getSerializableExtra("TASKS_ARRAY_LIST");
        taskTypeText.setText(taskType.toString());

        mainArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasksList);
        tasksListView.setAdapter(mainArrayAdapter);
        tasksListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        tasksListView.setOnItemClickListener(
                (parent, view, position, id) -> {
                }
        );
        tasksListView.setOnItemLongClickListener((adapterView, view, position, id) -> {
            Toast.makeText(
                    getApplicationContext(),
                    "Дело удалено!",
                    Toast.LENGTH_SHORT
            ).show();
            removeTask(position);
            mainArrayAdapter.notifyDataSetChanged();
            return true;
        });
    }

    @Override
    public void onClick(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.add_task_popoutxml, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAsDropDown(view, 50, 50, 0);

        Button addTaskOkButton = popupView.findViewById(R.id.addTaskOkButton);
        Button addTaskCancelButton = popupView.findViewById(R.id.addTaskCancelButton);
        TextView taskTitle = popupView.findViewById(R.id.taskTitle);
        TextView taskDescription = popupView.findViewById(R.id.taskDescription);


        addTaskOkButton.setOnClickListener(e -> {
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
            String datetime = ft.format(dNow);
            Task newTask = new Task(
                    Long.parseLong(datetime),
                    taskTitle.getText().toString(),
                    taskDescription.getText().toString(),
                    taskType
            );
            addTask(newTask);
            mainArrayAdapter.notifyDataSetChanged();
        });

        addTaskCancelButton.setOnClickListener(e -> popupWindow.dismiss());

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

    }


    private void addTask(Task newTask) {
        MainActivity.todoList.add(newTask);
        mainArrayAdapter.add(newTask);
    }

    private void removeTask(int position) {
        MainActivity.todoList.remove(position);
        tasksList.remove(position);
    }
}