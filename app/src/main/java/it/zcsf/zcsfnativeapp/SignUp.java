package it.zcsf.zcsfnativeapp;

import android.app.Activity;
import android.content.Intent;
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


FirebaseClass auth=new FirebaseClass(SignUp.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button signUp=(Button)findViewById(R.id.button3);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name=(EditText)findViewById(R.id.editText3);
                EditText email=(EditText)findViewById(R.id.editText4);
                EditText pass=(EditText)findViewById(R.id.editText5);
                EditText passC=(EditText)findViewById(R.id.editText6);
                EditText tid=(EditText)findViewById(R.id.editText7);

                if (Objects.equals(pass.getText().toString(), passC.getText().toString())){
                    Bundle bundle=new Bundle();
                    bundle.putString("tid",tid.getText().toString());
                    bundle.putString("pass",pass.getText().toString());
                    auth.verifyTicket(email.getText().toString(),name.getText().toString(),pass.getText().toString(),
                            tid.getText().toString(), MainActivity.class,bundle);
                }


            }
        });

        TextView redlogin = (TextView)findViewById(R.id.red_login);
        redlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
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
        if (id == R.id.action_sign_out) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
