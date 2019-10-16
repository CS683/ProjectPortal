package edu.bu.projectportal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ProjectPortalDBHelper extends SQLiteOpenHelper{
    SQLiteDatabase sqLiteDatabase;

    public ProjectPortalDBHelper(Context context) {
        super(context,ProjectPortalDBContract.DBName, null, ProjectPortalDBContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ProjectPortalDBContract.CREATE_PROJECT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ProjectPortalDBContract.DROP_PROJECT_TABLE);
        onCreate(sqLiteDatabase);
    }

}
