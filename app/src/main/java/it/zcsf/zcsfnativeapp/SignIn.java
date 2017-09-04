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

    Boolean e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        final EditText id=(EditText)findViewById(R.id.editText);
        final EditText pass=(EditText)findViewById(R.id.editText2);
        Button login=(Button)findViewById(R.id.button);
        //Button signUp=(Button)findViewById(R.id.button2);
        TextView signUp = (TextView) findViewById(R.id.red_signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idd = id.getText().toString();
                String Pass = pass.getText().toString();
                Clients clients = new Clients(SignIn.this);
                try {
                    clients.open();
                    e= clients.login(idd, Pass);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Toast.makeText(SignIn.this,e.toString(),Toast.LENGTH_SHORT).show();
                if (e) {
                    Intent intent =new Intent(SignIn.this,MainActivity.class);
                    String data=clients.getData(id.getText().toString());
                    intent.putExtra("Data",data);
                    clients.close();
                    startActivity(intent);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
