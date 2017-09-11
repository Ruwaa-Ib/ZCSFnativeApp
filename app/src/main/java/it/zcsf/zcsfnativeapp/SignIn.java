package it.zcsf.zcsfnativeapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class SignIn extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //final FirebaseClass auth=new FirebaseClass(SignIn.this);
        final EditText id=(EditText)findViewById(R.id.editText);
        final EditText pass=(EditText)findViewById(R.id.editText2);
        Button login=(Button)findViewById(R.id.button);
        //Button signUp=(Button)findViewById(R.id.button2);
        TextView signUp = (TextView) findViewById(R.id.red_signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tid = id.getText().toString();
                String Pass = pass.getText().toString();
                if (!tid.matches("")&&!Pass.matches("")) {
                    final FirebaseClass auth = new FirebaseClass(SignIn.this);
                    Bundle bundle = new Bundle();
                    bundle.putString("tid", tid);
                    bundle.putString("pass", Pass);
                    auth.SignIn(MainActivity.class, Pass, tid);
                }else {
                    Toast.makeText(SignIn.this,"Please, Don't Leave Any Blank Space!",Toast.LENGTH_SHORT).show();
                }


            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SignIn.this,SignUp.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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
