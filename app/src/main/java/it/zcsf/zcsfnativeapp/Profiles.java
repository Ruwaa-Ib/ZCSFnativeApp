package it.zcsf.zcsfnativeapp;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by new1 on 9/4/2017.
 */

public class Profiles {
    private String tid;

    private String Pass;
    private String TClass;
    private Context myContext;

    public Profiles(String  TID, String pass, String TClass, Context context){

        tid=TID;
        Pass=pass;
        this.TClass=TClass;
        myContext=context;

    }

    public void getTid(TextView dataContainer){
        dataContainer.setText(tid);
    }

    public void getSchedule( final TableLayout tableLayout){


        FirebaseDatabase secondaryDB=FirebaseDatabase.getInstance();
        DatabaseReference mRef =secondaryDB.getReference();

        mRef.child("Verify").child(TClass).child("Schedule").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){

                    String talkTitle=postSnapshot.getKey();
                    String location= postSnapshot.child("Location").getValue().toString();
                    String time= postSnapshot.child("Time").getValue().toString();

                    Schedule mSchedule=new Schedule(talkTitle,location,time);

                    TextView dataContainer=new TextView(myContext);
                    dataContainer.setText(mSchedule.talkTitle);
                    dataContainer.setPadding(5,5,5,5);

                    TextView dataContainer2=new TextView(myContext);
                    dataContainer2.setText(mSchedule.location);
                    dataContainer2.setPadding(5,5,5,5);

                    TextView dataContainer3=new TextView(myContext);
                    dataContainer3.setText(mSchedule.time);
                    dataContainer3.setPadding(5,5,5,5);

                    TableRow nTableRow=new TableRow(myContext);
                    nTableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

                    dataContainer.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT));
                    nTableRow.addView(dataContainer);

                    dataContainer2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT));
                    nTableRow.addView(dataContainer2);

                    dataContainer3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT));
                    nTableRow.addView(dataContainer3);

                    nTableRow.setPadding(20,10,20,10);

                    tableLayout.setStretchAllColumns (true);

                    tableLayout.addView(nTableRow,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));



                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void getTClass(TextView dataContainer){
         dataContainer.setText(TClass);
    }

    public void getName(final TextView dataContainer){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myUser = database.getReference();
        myUser.child("Users").child(tid).child("Name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataContainer.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void getEmail(final TextView dataContainer){



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myUser = database.getReference();
        myUser.child("Users").child(tid).child("Email").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataContainer.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void getPass(TextView dataContainer){
        dataContainer.setText(Pass);
    }

    public class Schedule{
        String talkTitle;
        String location;
        String time;
        Schedule(String talkTitle,String location,String time){
            this.talkTitle=talkTitle;
            this.location=location;
            this.time=time;

        }
    }
}


