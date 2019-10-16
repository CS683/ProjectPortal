package edu.bu.projectportal;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class ProjectsListActivity extends AppCompatActivity implements ProjectListAdapter.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_main);

        // get the last access time from the shared preferences. The file name is accesstime.xml
        SharedPreferences sharedPreferences = getSharedPreferences("accesstime", Context.MODE_PRIVATE);
        String lastAccessTime = sharedPreferences.getString("lastaccesstime", "");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddProjectActivity.class);
                startActivity(intent);
            }
        });


        if (lastAccessTime.compareTo ( "") != 0) {
            TextView textView = findViewById(R.id.latid);
            textView.setText(lastAccessTime);
        }

        // get current time and write to the shared preferences file
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String curTime = (new Date()).toString();
        editor.putString("lastaccesstime","last access at " + curTime);
        editor.commit();
    }

    @Override
    public void onClick(int id, int position) {
        ProjectDetailFragment detailFragment =
                (ProjectDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailfragment);

        if (detailFragment != null) {
            detailFragment.setProject(id);
        } else {
            Intent intent = new Intent(this, ProjectDetailActivity.class);
            intent.putExtra("position", position);
            intent.putExtra ("projectid", id);
            startActivity(intent);

        }

    }
}
