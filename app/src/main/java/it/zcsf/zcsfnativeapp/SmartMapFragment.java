package it.zcsf.zcsfnativeapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SmartMapFragment extends Fragment {

    public SmartMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_smart_map, container, false);

        //final String option1 = null;

        final Button btnmap = (Button) v.findViewById(R.id.btn_map);

        final Spinner spinner1 = (Spinner) v.findViewById(R.id.spinner1);
        final Spinner spinner2 = (Spinner) v.findViewById(R.id.spinner2);

        final ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this.getActivity(), R.array.spinner_array, R.layout.support_simple_spinner_dropdown_item);
        //adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        final ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getActivity(), R.array.spinner_array, R.layout.support_simple_spinner_dropdown_item);
        //adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);


        //spinner2.setPrompt("Select where you are...");

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String option1=String.valueOf(spinner1.getSelectedItem());
                //TextView firstoption = (TextView) view;
                /* option1 = firstoption.getText() */
                //Toast.makeText(getActivity(), option1, Toast.LENGTH_SHORT).show();

                if (position==0){
                    //firstoption.setTextColor(R.color.colorAccent);
                    btnmap.setEnabled(false);
                    spinner2.setEnabled(false);
                }
                else {
                    spinner2.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TextView option2 = (TextView) view;
                if (position==0){
                    btnmap.setEnabled(false);
                    btnmap.setVisibility(View.INVISIBLE);
                }
                else {
                    btnmap.setEnabled(true);
                    btnmap.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String option1=String.valueOf(spinner1.getSelectedItem());
                String option2=String.valueOf(spinner2.getSelectedItem());

                //Toast.makeText(getActivity(),option1 +"\n"+option2,Toast.LENGTH_LONG).show();

                if (option2==option1){
                    Toast.makeText(getActivity(),"You are already there ;)",Toast.LENGTH_LONG).show();
                }
                else {
                    /* insert switch cases here */
                }
            }
        });

        return v;
    }
}
