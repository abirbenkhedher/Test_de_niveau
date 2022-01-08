package iset.dsi.projet_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText username,pwd;
    private Button loginbtn;
    private TextView inscription;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.usernamelogin);
        pwd = findViewById(R.id.pwdlogin);
        loginbtn = findViewById(R.id.cnxbtn);
        inscription = findViewById(R.id.inscription);
        mAuth = FirebaseAuth.getInstance();

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = username.getText().toString();
                String Pwd = pwd.getText().toString();
                if(TextUtils.isEmpty(userName) && TextUtils.isEmpty(Pwd)) {
                    Toast.makeText(LoginActivity.this, "champs vide !", Toast.LENGTH_SHORT).show();
                }else {
                    mAuth.signInWithEmailAndPassword(userName,Pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login avec succée", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this, "echec de login", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }




    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){
            Intent i =new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(i);
            this.finish();
        }
    }
    }