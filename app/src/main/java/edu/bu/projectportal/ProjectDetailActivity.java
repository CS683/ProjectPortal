package edu.bu.projectportal;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ProjectDetailActivity extends AppCompatActivity {

    ProjectDetailFragment projectDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        //add fragments dynamically
        //create a fragment object
        projectDetailFragment = new ProjectDetailFragment();
        projectDetailFragment.setArguments(getIntent().getExtras());
        // get the reference to the FragmentManger object
        FragmentManager fragManager = getSupportFragmentManager();
        // get the reference to the FragmentTransaction object
        FragmentTransaction transaction = fragManager.beginTransaction();
        // add the fragment into the transaction
        transaction.add(R.id.proDetailfragContainer, projectDetailFragment);
        // commit the transaction.
        transaction.commit();


    }

    public void onClick(View view){
        int id = projectDetailFragment.getProjectId();
        projectDetailFragment.setProject( (id + 1) % Project.projects.length);
    }
}