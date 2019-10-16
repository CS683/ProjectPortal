package edu.bu.projectportal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import edu.bu.projectportal.database.ProjectDao;

public class AddProjectActivity extends AppCompatActivity {
    ProjectDao projectDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        projectDao = ProjectDao.getInstance(getApplicationContext());
        projectDao.openDb();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        projectDao.closeDB();
    }

    public void onClickSubmit(View view){

        EditText titleEditView =  findViewById(R.id.titleEditTextId);
        String title = titleEditView.getText().toString();

        EditText descEditView= findViewById(R.id.descEditTextId);
        String summary = descEditView.getText().toString();


        ProjectDao projectDao = ProjectDao.getInstance(getApplicationContext());
        projectDao.insertProject(new Project(title, summary));
        Intent intent = new Intent(this, ProjectsListActivity.class);
        startActivity(intent);
    }

    public void onClickCancel(View v){
        Intent intent = new Intent(this, ProjectsListActivity.class);
        startActivity(intent);
    }


}
