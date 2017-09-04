package it.zcsf.zcsfnativeapp;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by new1 on 9/4/2017.
 */

public class FirebaseClass {
    private final Context myContext;
    private boolean e;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    public FirebaseClass(Context C){
        myContext=C;
    }

    public void SignUP(final String email, final String Pass, final String name, final String tid
    , final Context here, final Class there){

        /*here is the activity you are in, there is the activity you will
         start if process completed, the other inputs are the credentials
       */
        final DatabaseReference mUsers = database.getReference();
        mUsers.child(tid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    // Credentials
                    DatabaseReference mID = mUsers.child(tid);
                    mID.child("Pass").setValue(Pass);
                    mID.child("Name").setValue(name);
                    mID.child("Email").setValue(email);

                    Intent i=new Intent(here, there);
                    here.startActivity(i);
                }
                else {
                    Toast.makeText(myContext,"Ticket ID Used", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void SignIn(final String tid, final String Pass, final Context here, final Class there){

        /*here is the activity you are in, there is the activity you will
         start if process completed, the other inputs are the credentials
       */

        final DatabaseReference mUsers = database.getReference();
        mUsers.child(tid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    e = dataSnapshot.child("Pass").getValue().toString().equals(Pass);
                    if (e) {
                        Intent i = new Intent(here, there);
                        here.startActivity(i);
                    } else {
                        Toast.makeText(here, "Failed to Authenticate!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(here,"No Such Ticket ID", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void getInfo(String tid){
        /*Used to get profile info and schedule*/

    }

    public void readData(){
    /* Used to read data in the App feed*/
    }

    public void postData(){
        /*Used to make a Post in the App feed*/

    }
}
