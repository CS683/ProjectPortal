package edu.bu.projectportal;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FireBaseHelper {
    //  private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance ();
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance ();
    private String curUid;
    private String TAG = getClass ().getSimpleName ();

    private static FireBaseHelper instance;

    public CollectionReference getmProjectRef() {
        return mProjectRef;
    }

    private CollectionReference mProjectRef;

    private List<Project> projects;

    public interface UpdateProjectUI {
        void addProjectUI();

    }

    UpdateProjectUI updateProjectUI;

    public FireBaseHelper() {
        curUid = mAuth.getUid ();

        mProjectRef = firebaseFirestore.collection ("users/" + curUid + "/projects");
    }


    public static synchronized FireBaseHelper getInstance() {
        if (instance == null)
            instance = new FireBaseHelper ();
        return instance;
    }




    public void addProject(Project project) {

        mProjectRef.add (project)
                .addOnSuccessListener (new OnSuccessListener<DocumentReference> () {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d (TAG, "DocumentSnapshot added with ID: " + documentReference.getId ());
                    }
                })
                .addOnFailureListener (new OnFailureListener () {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w (TAG, "Error adding document", e);
                    }
                });

    }

    public List<Project> getProject(final UpdateProjectUI updateProjectUI) {

        mProjectRef.orderBy ("id").get ()
                .addOnCompleteListener (new OnCompleteListener<QuerySnapshot> () {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful ()) {
                                Project.projects = new ArrayList<> ();
                            for (QueryDocumentSnapshot document : task.getResult ()) {

                                Project project = document.toObject (Project.class);
                                project.setDocId(document.getId());

                                Project.projects.add(project);

                                updateProjectUI.addProjectUI ();
                            }
                        } else {
                            Log.d (TAG, "error on query");
                        }
                    }
                });
        return projects;
    }

    public void delProject(String docId) {
        mProjectRef.document (docId)
                .delete ()
                .addOnSuccessListener (new OnSuccessListener<Void> () {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d (TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener (new OnFailureListener () {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w (TAG, "Error deleting document", e);
                    }
                });
    }
}
