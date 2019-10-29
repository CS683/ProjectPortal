package edu.bu.projectportal.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by danazh on 4/25/18.
 */

public class ProjectProvider extends ContentProvider {
    ProjectPortalDBHelper projectPortalDBHelper;

    @Override
    public boolean onCreate() {
        projectPortalDBHelper = new ProjectPortalDBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        // match Uri using sURIMatcher defined in the contract
        int uriType = ProjectPortalDBContract.sURIMatcher.match(uri);
        long id = 0;

        switch(uriType){
            case ProjectPortalDBContract.PROJECT:
                // call corresponding db insert method
                SQLiteDatabase db = projectPortalDBHelper.getWritableDatabase();
                id = db.insert(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                        null, contentValues);
                db.close();
                break;
            default:
                throw new IllegalArgumentException("unknown URI: " + uri);
        }
        if (id >= 0) {
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return Uri.parse( ProjectPortalDBContract.ProjectContract.TABLE_NAME +
                "/" + id);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings,
                        @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        int uriType = ProjectPortalDBContract.sURIMatcher.match(uri);
        switch(uriType){
            case ProjectPortalDBContract.PROJECT:
                SQLiteDatabase db = projectPortalDBHelper.getReadableDatabase();
                Cursor cursor = db.query(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                        strings, s, strings1, null, null, s1, null);
                return cursor;
            default:
                throw new IllegalArgumentException("unknown URI: " + uri);
        }
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s,
                      @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues,
                      @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
