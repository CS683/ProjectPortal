package edu.bu.projectportal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectsListFragment extends Fragment {

    public ProjectsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_projects_list, container, false);


        int numOfProjects = Project.projects.length;
        String[] projectNameList = new String[numOfProjects];

        for (int i=0; i< numOfProjects; i++){
            projectNameList[i] =  Project.projects[i].getTitle();
        }

        ArrayAdapter<String> projectsListAdapter =
                new ArrayAdapter<String>(Objects.requireNonNull (getContext ()),
                        android.R.layout.simple_list_item_1, projectNameList);

        ListView projectsListView = v.findViewById(R.id.projectsList_view);
        projectsListView.setAdapter(projectsListAdapter);

        projectsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ProjectDetailActivity.class);
                intent.putExtra(getString(R.string.projectid_key),i );
                startActivity(intent);

            }
        });

        return v;
    }
}
