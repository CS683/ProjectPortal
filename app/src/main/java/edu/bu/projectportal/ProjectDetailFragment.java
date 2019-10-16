package edu.bu.projectportal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import edu.bu.projectportal.database.ProjectDao;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectDetailFragment extends Fragment {

    private final static String TAG = ProjectDetailFragment.class.getSimpleName ();

    private int projectId ;
    private TextView titleTextView, summaryTextView;
    private Button delBtn;

    public ProjectDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_project_detail, container, false);

        titleTextView = view.findViewById(R.id.projTitleTextViewId);
        summaryTextView = view.findViewById(R.id.projSummaryTextViewId);
        delBtn = view.findViewById (R.id.del_btn);

        delBtn.setOnClickListener (new OnClickListener () {
            @Override
            public void onClick(View v) {
                deleteProject (v);
            }
        });

        if (getArguments()!= null)
            projectId = getArguments().getInt("projectid");
        else
            projectId = ProjectDao.getInstance (getContext()).getNexProjectId (0);

        Log.d(TAG, " Project Id: " + projectId);
        if (projectId > 0)
            setProject(projectId);
        else
            delBtn.setVisibility (View.INVISIBLE);



        return view;
    }

    public void setProject(int projId) {
        projectId = projId;

        ProjectDao projectDao = ProjectDao.getInstance (getContext());
        Project project = projectDao.getProjectById(projectId);

        if (titleTextView != null)
            titleTextView.setText(project.getTitle());
        if (summaryTextView != null)
            summaryTextView.setText(project.getSummary());
    }

    public int getProjectId(){
        return projectId;
    }

    public void deleteProject(View v) {

        ProjectDao.getInstance (getContext ()).delectProjectById (projectId);
        Intent intent = new Intent (getContext(), ProjectsListActivity.class);
        startActivity (intent);
    }
}
