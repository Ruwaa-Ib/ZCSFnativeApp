package it.zcsf.zcsfnativeapp;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeTableFragment extends Fragment {

    public TimeTableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_time_table, container, false);
        String tid=getArguments().getString("tid");
        String pass=getArguments().getString("pass");
        FirebaseClass table=new FirebaseClass(getActivity());

        TableLayout tableLayout=(TableLayout)view.findViewById(R.id.table);

        TableRow tableRow=new TableRow(getActivity());
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tableRow.setPadding(20,10,20,10);

        TextView title=new TextView(getActivity());
        title.setText("Talk Title");
        title.setTextColor(Color.MAGENTA);
        title.setPadding(5,5,5,5);
        title.setTextSize(16);
        tableRow.addView(title);

        TextView location=new TextView(getActivity());
        location.setText("Location");
        location.setTextColor(Color.MAGENTA);
        location.setPadding(5,5,5,5);
        location.setTextSize(16);
        tableRow.addView(location);

        TextView time=new TextView(getActivity());
        time.setText("Time");
        time.setTextColor(Color.MAGENTA);
        time.setPadding(5,5,5,5);
        time.setTextSize(16);
        tableRow.addView(time);

        tableRow.setPadding(5,5,5,5);

        tableLayout.setStretchAllColumns (true);

        tableLayout.addView(tableRow);



        table.getInfo(tid,pass,"schedule",null,tableLayout);

        return view;
    }

}
