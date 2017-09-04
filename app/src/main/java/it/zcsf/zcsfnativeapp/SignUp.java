package it.zcsf.zcsfnativeapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.Objects;

public class SignUp extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button signUp=(Button)findViewById(R.id.button3);
        RadioButton radioButton=(RadioButton)findViewById(R.id.radioButton);
        RadioButton radioButton2=(RadioButton)findViewById(R.id.radioButton2);
        radioButton2.toggle();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name=(EditText)findViewById(R.id.editText3);
                EditText email=(EditText)findViewById(R.id.editText4);
                EditText pass=(EditText)findViewById(R.id.editText5);
                EditText passC=(EditText)findViewById(R.id.editText6);
                EditText tid=(EditText)findViewById(R.id.editText7);

                TextView welcome = (TextView) findViewById(R.id.welcome);
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/HVD_Comic_Serif_Pro.otf");
                welcome.setTypeface(typeface);

                if (Objects.equals(pass.getText().toString(), passC.getText().toString())){
                    Clients clients=new Clients(SignUp.this);
                    String Name=name.getText().toString();
                    String Email=email.getText().toString();
                    String Pass=pass.getText().toString();
                    String Tid=tid.getText().toString();
                    try {
                        clients.open();
                        clients.addClient(Name, Pass, Email, Tid);
                        clients.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                Intent i= new Intent(SignUp.this,MainActivity.class);
                startActivity(i);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
