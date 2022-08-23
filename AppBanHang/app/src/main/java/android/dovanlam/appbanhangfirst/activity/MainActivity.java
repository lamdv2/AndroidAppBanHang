package android.dovanlam.appbanhangfirst.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.dovanlam.appbanhangfirst.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // main activity
    private EditText txtEmail2, txtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail2 = findViewById(R.id.txtEmail2);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_LONG).show();

                String email = txtEmail2.getText().toString();
                String password = txtPassword.getText().toString();



                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }

}