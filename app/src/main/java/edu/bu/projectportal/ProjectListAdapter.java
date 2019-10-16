package edu.bu.projectportal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectListViewHolder>{
    private List<Project> projects;
    private Listener listener;

    interface Listener {
        void onClick(int id, int position);
    }

    public ProjectListAdapter(List<Project> projects){this.projects = projects;}

    @Override
    public int getItemCount() {
        return projects.size();
    }

    @NotNull
    @Override
    public ProjectListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_item, parent,
                        false);
        return new ProjectListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectListViewHolder viewHolder, final int position){
        viewHolder.projTitleView.setText(projects.get(position).getTitle());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), ProjectDetailActivity.class);
//                intent.putExtra("projectid", position);
//                view.getContext().startActivity(intent);
                listener = (Listener)view.getContext();
               // listener = (ProjectsListActivity)view.getContext();
                if (listener != null)
                    listener.onClick(projects.get(position).getId(), position);
            }
        });


    }

    public void setListener(Listener listener){
        this.listener = listener;
    }




    //ViewHolder inner class
    public static class ProjectListViewHolder extends RecyclerView.ViewHolder {
        private TextView projTitleView;
        private CardView cardView;
        public ProjectListViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            projTitleView = itemView.findViewById(R.id.projListTitleTextViewId);
        }
    }
}
