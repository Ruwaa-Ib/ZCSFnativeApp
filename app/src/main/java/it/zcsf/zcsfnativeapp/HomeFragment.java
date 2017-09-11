package it.zcsf.zcsfnativeapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import it.zcsf.zcsfnativeapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view=inflater.inflate(R.layout.fragment_home, container, false);


        //Reading posts
        if (getArguments()!=null) {
            final String tid = getArguments().getString("tid");
            final String pass = getArguments().getString("pass");
            final FirebaseClass myProfile = new FirebaseClass(getActivity());
            TableLayout tableLayout = (TableLayout) view.findViewById(R.id.table);
            myProfile.readData(tableLayout);

            //Posting to the server
            final EditText editText = (EditText) view.findViewById(R.id.editText8);
            editText.setSelected(false);

            final TextView textView=(TextView)view.findViewById(R.id.myName);

            myProfile.getInfo(tid, pass, "name", textView, null);
            textView.setVisibility(View.INVISIBLE);

            Button post = (Button) view.findViewById(R.id.button7);
            post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    myProfile.postData(textView.getText().toString(), editText.getText().toString());
                    editText.setText("");
                }
            });

        }


        return view;
    }

}
