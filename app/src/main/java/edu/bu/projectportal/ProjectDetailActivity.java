package edu.bu.projectportal;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import edu.bu.projectportal.database.ProjectDao;

public class ProjectDetailActivity extends AppCompatActivity {

    ProjectDetailFragment projectDetailFragment;
    ProjectDao projectDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_project_detail);

        projectDao = ProjectDao.getInstance (getApplicationContext ());
        projectDao.openDb ();

        //add fragments dynamically
        //create a fragment object
        projectDetailFragment = new ProjectDetailFragment ();
        projectDetailFragment.setArguments (getIntent ().getExtras ());
        // get the reference to the FragmentManger object
        FragmentManager fragManager = getSupportFragmentManager ();
        // get the reference to the FragmentTransaction object
        FragmentTransaction transaction = fragManager.beginTransaction ();
        // add the fragment into the transaction
        transaction.add (R.id.proDetailfragContainer, projectDetailFragment);
        // commit the transaction.
        transaction.commit ();


    }

    public void onClick(View view) {
        int id = projectDetailFragment.getProjectId ();
        projectDetailFragment.setProject (projectDao.getNexProjectId (id));
    }



    @Override
    protected void onDestroy() {
        super.onDestroy ();
        projectDao.closeDB ();

    }
}