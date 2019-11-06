package edu.bu.projectportal;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.bu.projectportal.database.ProjectDao;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectsListFragment extends Fragment {
    RecyclerView projectsListRecyclerView;

    public ProjectsListFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_projects_list, container, false);
        projectsListRecyclerView =
                (v.findViewById(R.id.projectlist_recyclerview));

        (new DatabaseAsyncTask()).execute();

//        ProjectDao projectDao = ProjectDao.getInstance (getContext());
//        projectDao.openDb ();
//
//        List<Project> projects;
//        projects = projectDao.getAllProject();
//
//        ProjectListAdapter projectListAdapter = new ProjectListAdapter(projects);
//        projectsListRecyclerView.setAdapter(projectListAdapter);
//        projectListAdapter.setListener((ProjectListAdapter.Listener)getActivity());
//
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//        projectsListRecyclerView.setLayoutManager(mLayoutManager);
//


        return v;
    }



    private class DatabaseAsyncTask extends AsyncTask<Void,Void,List<Project>> {

        @Override
        protected List<Project> doInBackground(Void... strings) {
            ProjectDao projectDao = ProjectDao.getInstance(getContext());
            projectDao.openDb();

            List<Project> projects;
            projects = projectDao.getAllProject();
            return projects;
        }

        protected void onPostExecute(List<Project> projects) {

            ProjectListAdapter projectListAdapter = new ProjectListAdapter(projects);
            projectsListRecyclerView.setAdapter(projectListAdapter);
            projectListAdapter.setListener((ProjectListAdapter.Listener)getActivity());

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            projectsListRecyclerView.setLayoutManager(mLayoutManager);
        }
    }


}


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View v =  inflater.inflate(R.layout.fragment_projects_list, container, false);
//
//
//        int numOfProjects = Project.projects.length;
//        String[] projectNameList = new String[numOfProjects];
//
//        for (int i=0; i< numOfProjects; i++){
//            projectNameList[i] =  Project.projects[i].getTitle();
//        }
//
//        ArrayAdapter<String> projectsListAdapter =
//                new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, projectNameList);
//
//        ListView projectsListView = (ListView) (v.findViewById(R.id.projectsList_view));
//        projectsListView.setAdapter(projectsListAdapter);
//
//        projectsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getContext(), ProjectDetailActivity.class);
//                intent.putExtra("projectid",i );
//                startActivity(intent);
//
//            }
//        });
//
//        return v;
//    }
//}
