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
