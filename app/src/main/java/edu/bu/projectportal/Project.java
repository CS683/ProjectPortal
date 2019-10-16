package edu.bu.projectportal;


import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by danazh on 2/21/18.
 */

public class Project {

//   public final static Project[] projects = {
//            new Project("Weather Forecast", "Weather Forcast is an app ..."),
//            new Project ("Connect Me", "Connect Me is an app ... "),
//            new Project("What to Eat", "What to Eat is an app ..."),
//            new Project ("Project Portal", "Project Portal is an app ...")
//    };

    private int id;
    private String title;
    private String summary;

    private List<Project> allProjects;

    public List<Project> getAllProjects(){
        return allProjects;
    }

    public Project(@NotNull String title, @NotNull String summary) {

        this.title = title;
        this.summary = summary;
    }

    public Project(int id, @NotNull String title,  @NotNull String summary) {
        this.id = id;
        this.title = title;
        this.summary = summary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @NotNull
    @Override
    public String toString() {

        return "Project{" +
                "title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }

}
