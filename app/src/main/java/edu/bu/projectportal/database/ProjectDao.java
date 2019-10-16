package edu.bu.projectportal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.bu.projectportal.Project;

public class ProjectDao {
    private static ProjectDao instance;

    private  ProjectPortalDBHelper projectPortalDBHelper;
    private SQLiteDatabase mReadableDB, mWritableDB;


    public ProjectDao(Context context){
        projectPortalDBHelper = new ProjectPortalDBHelper(context);
    }

    public void openDb(){
        mReadableDB = projectPortalDBHelper.getReadableDatabase();
        mWritableDB = projectPortalDBHelper.getWritableDatabase();
    }

    public void closeDB(){
        mReadableDB.close();
        mWritableDB.close();
    }

    public static ProjectDao getInstance(Context context){
        if (instance == null)
            instance = new ProjectDao(context);
        return instance;
    }

    public long insertProject(Project project) {

        ContentValues projectValue = new ContentValues();
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                project.getTitle());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY,
                project.getSummary());
        return mWritableDB.insert(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                null, projectValue);
    }

    public long delectProjectById(int projectId) {
        String selection = ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID + "=?";
        String[] selectionArgs = {projectId+""};

        return mWritableDB.delete(ProjectPortalDBContract.ProjectContract.TABLE_NAME ,
                selection,selectionArgs);

    }


    public List<Project> getAllProject() {

            String[] projection = {ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID,
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY};

            Cursor cursor = mReadableDB.query(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                    projection,
                    null, null, null, null,null);

            List<Project> projects = new ArrayList<Project>();

            while(cursor.moveToNext()) {
                int projectId = cursor.getInt(cursor.getColumnIndex(
                        ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID));
                String projectTitle = cursor.getString(cursor.getColumnIndex(
                        ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title));
                String projectSum = cursor.getString(cursor.getColumnIndex(
                        ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY));

                projects.add(new Project(projectId, projectTitle,projectSum));
            }

            cursor.close();

            return projects;
    }

    public Project getProjectById(int projectId) {

        Project project = null;

        String[] projection = {ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY};

        String selection = ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID + "=?";
        String[] selectionArgs = {Integer.toString(projectId)};

        Cursor cursor = mReadableDB.query(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                projection, selection, selectionArgs, null, null,null);



        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst ();

            String projectTitle = cursor.getString (cursor.getColumnIndex (
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title));
            String projectSum = cursor.getString (cursor.getColumnIndex (
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY));
            project = new Project(projectId, projectTitle,projectSum);

            cursor.close();
        }

        return project;
    }

    public int getNexProjectId(int id) {


            String[] projection = {ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID,
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY};

            String selection = ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID + ">?";
            String[] selectionArgs = {Integer.toString(id)};

            Cursor cursor = mReadableDB.query(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                    projection, selection, selectionArgs, null, null,null);

            int nextId;

            if(cursor!=null && cursor.getCount()>0){
                cursor.moveToFirst ();

                 nextId = cursor.getInt (cursor.getColumnIndex (
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID));

                cursor.close();
            } else {
                String[] selectionArgs1 = {"0"};
                cursor = mReadableDB.query(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                        projection, selection, selectionArgs1, null, null,null);

                if(cursor!=null && cursor.getCount()>0) {
                    cursor.moveToFirst ();
                    nextId = cursor.getInt (cursor.getColumnIndex (
                            ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID));
                    cursor.close();
                }
                else
                    nextId = 0;

                }



            return nextId;
    }

    public Cursor getProjectByTitle(String projectTitle) {

        String[] projection = {ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY};

        String selection = ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title + "LIKE ?";
        String[] selectionArgs = {projectTitle};

        return mReadableDB.query(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                projection, selection, selectionArgs, null, null,null);
    }


    public long updateProjectById(SQLiteDatabase sqLiteDatabase, Project project, int projectId) {

        String selection = ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID + "=?";
        String[] selectionArgs = {projectId +""};

        ContentValues projectValue = new ContentValues();
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                project.getTitle());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY,
                project.getSummary());
        return mWritableDB.update(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                projectValue, selection, selectionArgs);

    }
}
