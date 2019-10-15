package edu.bu.projectportal;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ProjectsListActivity extends AppCompatActivity implements ProjectListAdapter.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_list);

        // get the last access time from the shared preferences. The file name is accesstime.xml
        SharedPreferences sharedPreferences = getSharedPreferences("accesstime", Context.MODE_PRIVATE);
        String lastAccessTime = sharedPreferences.getString("lastaccesstime", "");

        if (lastAccessTime.compareTo ( "") == 0) {
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
    public void onClick(int position) {
        ProjectDetailFragment detailFragment =
                (ProjectDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailfragment);

        if (detailFragment != null) {
            detailFragment.setProject(position);
        } else {
            Intent intent = new Intent(this, ProjectDetailActivity.class);
            intent.putExtra("projectid", position);
            startActivity(intent);

        }

    }
}
