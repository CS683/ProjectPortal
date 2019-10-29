package edu.bu.projectportal.database;

import android.content.UriMatcher;
import android.net.Uri;

/**
 * Created by danazh on 4/18/18.
 */

public final class ProjectPortalDBContract {
    public static final String DBName = "projectportal.db";
    public static final int DB_VERSION = 1;

    public static final class ProjectContract{
        public static final String TABLE_NAME = "projects";
        public static final String COLUMN_PROJECT_ID = "project_id";
        public static final String  COLUMN_PROJECT_Title = "project_title";
        public static final String COLUMN_PROJECT_SUMMARY = "project_summary";
    }

    public static final String CREATE_PROJECT_TABLE = "CREATE TABLE " +
            ProjectPortalDBContract.ProjectContract.TABLE_NAME +
            "(" +
            ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title +
            " TEXT," +
            ProjectContract.COLUMN_PROJECT_SUMMARY +

            " TEXT);";

    public static final String DROP_PROJECT_TABLE = "DROP TABLE IF EXISTS "
            + ProjectPortalDBContract.ProjectContract.TABLE_NAME;

    //define Uri for the content provider

    public static final String PROVIDER_NAME = "edu.bu.projectportal.database.ProjectProvider";
    public static final String URL = "content://" + PROVIDER_NAME;


    public static final Uri projectUri =
            Uri.parse(URL+ "/" + ProjectPortalDBContract.ProjectContract.TABLE_NAME);

    public static final int PROJECT = 1;
    public static final int PROJECTID = 2;

    public static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(PROVIDER_NAME,ProjectContract.TABLE_NAME, PROJECT);
        sURIMatcher.addURI(PROVIDER_NAME,ProjectContract.TABLE_NAME + "/#", PROJECTID);
    }

}
