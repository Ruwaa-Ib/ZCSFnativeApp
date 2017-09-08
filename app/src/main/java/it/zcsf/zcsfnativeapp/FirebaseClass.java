package it.zcsf.zcsfnativeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;
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
    private Profiles myProfile;



    public FirebaseClass(Context C){
        myContext=C;

    }

    private void SignUP(final String email, final String name, final String pass, final String tid, final Class there, final Bundle transInfo){

        /*here is the activity you are in, there is the activity you will
         start if process completed, the other inputs are the credentials
       */

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference mUsers = database.getReference();
        mUsers.child("Users").child(tid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    // Credentials
                    DatabaseReference mID = mUsers.child("Users").child(tid);
                    mID.child("Pass").setValue(pass);
                    mID.child("Name").setValue(name);
                    mID.child("Email").setValue(email);

                    Intent i=new Intent(myContext, there);
                    i.putExtras(transInfo);
                    myContext.startActivity(i);
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

    public void SignIn(final Class there, final String pass, String tid){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final Bundle bundle=new Bundle();
        bundle.putString("tid",tid);
        bundle.putString("pass",pass);

        final DatabaseReference mUsers = database.getReference();
        mUsers.child("Users").child(tid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    e = dataSnapshot.child("Pass").getValue().toString().equals(pass);
                    if (e) {
                        Intent i = new Intent(myContext, there);
                        i.putExtras(bundle);
                        myContext.startActivity(i);
                    } else {
                        Toast.makeText(myContext, "Failed to Authenticate!", Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(myContext,"No Such Ticket ID", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void getInfo(String tid, String Pass, String dataNeeded, TextView dataContainer, TableLayout tableLayout){
        /*Used to get profile info and schedule*/

        /*the input tid and pass are information passed between activities starting from the first activity
        * which is the sign in, this could be achieved using intent extras.
        * the input dataNeeded is one string of the following that gives you specific info you need.*/

        myProfile=new Profiles(tid,Pass,getTClass(tid),myContext);

        switch (dataNeeded){

            case "schedule":
                myProfile.getSchedule(tableLayout);

                break;

            case "email":


                 myProfile.getEmail(dataContainer);
                break;

            case "name":

                myProfile.getName(dataContainer);
                break;

            case "pass":
                myProfile.getPass(dataContainer);
                break;

            case "ticketID":
                myProfile.getTid(dataContainer);
                break;

            case "ticketClass":
                myProfile.getTClass(dataContainer);
                break;


        }


    }

    public void readData(){
    /* Used to read data in the App feed*/
    }

    public void postData(){
        /*Used to make a Post in the App feed*/

    }

    public void verifyTicket(final String email, final String name, final String pass, final String tid, final Class there, final Bundle bundle){

        FirebaseDatabase secondaryDB=FirebaseDatabase.getInstance();
        DatabaseReference mref =secondaryDB.getReference();

        /*Some function that read the ticket id and determines the ticket class should exist here*/
        /*let's say the ticket class is determined from the first 2 digits in the ticket ID*/

        String TClass=getTClass(tid);
        if (!TClass.equals("BB")){

        mref.child("Verify").child(TClass).child("TIDs").child(tid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                SignUP(email,name,pass,tid,there,bundle);
                }else {
                    Toast.makeText(myContext,"There is No Such Ticket ID!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        }else {
            Toast.makeText(myContext,"Please Check Your Ticket ID! ",Toast.LENGTH_SHORT).show();
        }
    }

    private String getTClass(String tid){
        String TClass;
        switch (tid.substring(0,2)){
            case "12":
                TClass="A1";
                break;
            case "15":
                TClass="A2";
                break;
            default:
                TClass="BB";
        }


        return TClass;
    }


}
