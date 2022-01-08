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

public class RegisterActivity extends AppCompatActivity {

    private EditText username,pwd,cnfpwd;
    private TextView connexion;
    private Button connexionbtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        pwd = findViewById(R.id.pwd);
        cnfpwd = findViewById(R.id.cnfpwd);
        connexion = findViewById(R.id.conexion);
        connexionbtn = findViewById(R.id.connexionbtn);
        mAuth = FirebaseAuth.getInstance();
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });


        connexionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = username.getText().toString();
                String Pwd = pwd.getText().toString();
                String Cnfpwd = cnfpwd.getText().toString();

                if(!Pwd.equals(Cnfpwd)){
                    Toast.makeText(RegisterActivity.this, "les deux mots de passe differents", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(userName) && TextUtils.isEmpty(Pwd) && TextUtils.isEmpty(Cnfpwd)){
                    Toast.makeText(RegisterActivity.this, "champs vide !", Toast.LENGTH_SHORT).show();
                }else {
                    mAuth.createUserWithEmailAndPassword(userName,Pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "utilisateur enregistré", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(i);
                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this, "echec d'enregistrer l'utilisateur", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }



            }
        });

    }
}