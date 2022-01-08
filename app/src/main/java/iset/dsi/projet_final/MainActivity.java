package iset.dsi.projet_final;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvarticle;
    private FloatingActionButton Addbtn;
    private RelativeLayout RV;
    private ArrayList<ArticleModel> articleModelArrayList;
    private FirebaseDatabase FirebaseDatabase;
    private DatabaseReference DatabaseReference;
    private ArticleAdapter ArticleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvarticle = findViewById(R.id.idRVArticles);
        Addbtn = findViewById(R.id.ADD);
        FirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference = FirebaseDatabase.getReference("Article");
        articleModelArrayList = new ArrayList<>();
        ArticleAdapter = new ArticleAdapter(articleModelArrayList, this);
        rvarticle.setLayoutManager(new LinearLayoutManager(this));
        rvarticle.setAdapter(ArticleAdapter);
        Addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddArticleActivity.class));
            }
        });

         getAllArticle();
        }
    private void getAllArticle(){
        articleModelArrayList.clear();
        DatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                articleModelArrayList.add(snapshot.getValue(ArticleModel.class));
                ArticleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ArticleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                ArticleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ArticleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}