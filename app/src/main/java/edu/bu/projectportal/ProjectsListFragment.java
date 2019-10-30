package edu.bu.projectportal;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import edu.bu.projectportal.database.ProjectDao;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectsListFragment extends Fragment implements
FireBaseHelper.UpdateProjectUI{

    RecyclerView projectsListRecyclerView;
    ProjectListAdapter projectListAdapter;

    public ProjectsListFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate (R.layout.fragment_projects_list, container, false);

        projectsListRecyclerView =
                (v.findViewById (R.id.projectlist_recyclerview));


        return v;
    }

    @Override
    public void onResume() {
        super.onResume ();

        FireBaseHelper.getInstance().getProject (this);

    }

    @Override
    public  void addProjectUI()
    {
        ProjectListAdapter projectListAdapter = new ProjectListAdapter (Project.projects);
        projectsListRecyclerView.setAdapter (projectListAdapter);
        projectListAdapter.setListener ((ProjectListAdapter.Listener) getActivity ());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager (getActivity ());
        projectsListRecyclerView.setLayoutManager (mLayoutManager);

    }

/*
    CollectionReference mProjectRef = FireBaseHelper.getInstance ().getmProjectRef ();
    mProjectRef.orderBy ("id").get ()
            .addOnCompleteListener (new OnCompleteListener<QuerySnapshot> () {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    if (task.isSuccessful ()) {
                        Project.projects = new ArrayList<Project> ();
                        for (QueryDocumentSnapshot document : task.getResult ()) {
                            Project project = document.toObject (Project.class);

                            Project.projects.add(project);

                            Project.nextId = project.getId () + 1;
                        }

                        ProjectListAdapter projectListAdapter = new ProjectListAdapter(Project.projects);
                        projectsListRecyclerView.setAdapter(projectListAdapter);
                        projectListAdapter.setListener((ProjectListAdapter.Listener)getActivity());

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        projectsListRecyclerView.setLayoutManager(mLayoutManager);

                    } else {
                        Log.d ("error", "error on query");
                    }
                }
            });

*/
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
