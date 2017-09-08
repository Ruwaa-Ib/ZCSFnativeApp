package it.zcsf.zcsfnativeapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {


    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_my_profile, container, false);
        String tid=getArguments().getString("tid");
        String pass=getArguments().getString("pass");
        FirebaseClass myProfile=new FirebaseClass(getActivity());

        TextView Name=(TextView) view.findViewById(R.id.name) ;
        TextView Email=(TextView)view.findViewById(R.id.email);
        TextView Pass=(TextView)view.findViewById(R.id.pass);
        TextView ticketID=(TextView)view.findViewById(R.id.ticketid);
        TextView ticketClass=(TextView)view.findViewById(R.id.ticketclass);


        myProfile.getInfo(tid,pass,"name",Name,null);
        myProfile.getInfo(tid,pass,"email",Email,null);
        myProfile.getInfo(tid,pass,"pass",Pass,null);
        myProfile.getInfo(tid,pass,"ticketID",ticketID,null);
        myProfile.getInfo(tid,pass,"ticketClass",ticketClass,null);




        return view;
    }

}
