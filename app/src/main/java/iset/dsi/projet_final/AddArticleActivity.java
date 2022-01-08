package iset.dsi.projet_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddArticleActivity extends AppCompatActivity {

    private EditText nom,prix;
    private Button Addbtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference DatabaseReference;
    private String ArticleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        nom = findViewById(R.id.nomArticle);
        prix = findViewById(R.id.prixArticle);
        Addbtn = findViewById(R.id.addbtn);
        firebaseDatabase = firebaseDatabase.getInstance();
        DatabaseReference = firebaseDatabase.getReference("Articles");

        Addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nom.getText().toString();
                String price = prix.getText().toString();
                ArticleId = name;
                ArticleModel ArticleModel = new ArticleModel(ArticleId,name,price);

                DatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DatabaseReference.child(ArticleId).setValue(ArticleModel);
                        Toast.makeText(AddArticleActivity.this, "article ajouté avec succée", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(AddArticleActivity.this,MainActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddArticleActivity.this, "erreur est "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}