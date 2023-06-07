package com.aether89.a420_pc2_sh;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Utilisateurs> liste = new ArrayList<Utilisateurs>();
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabAjouter = findViewById(R.id.fab_Ajouter);
        fabAjouter.setOnClickListener(clickActivityAjouter);

        liste.add(new Utilisateurs("Albert","Whisker","aw@umbrella.com"));
        liste.add(new Utilisateurs("Robert", "Builder","bob@builder.com"));
        liste.add(new Utilisateurs("Carlos", "Diaz", "carl@gmail.com"));
        liste.add(new Utilisateurs("Dexter", "Einstein", "dex@lab.com"));

        adapter = new CustomAdapter(liste, new RecyclerCallback<Utilisateurs>() {
            @Override
            public void returnValue(Utilisateurs object) {

            }
        });
        adapter.submitList(new ArrayList<>(liste));
        RecyclerView recyclerViewMain = findViewById(R.id.recyclerVierMain);
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMain.setAdapter(adapter);

        adapter.setCallback(user -> {
            liste.remove(user);
            adapter.submitList(new ArrayList<>(liste));

        });

    }

    View.OnClickListener clickActivityAjouter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AjoutUtilisateur.class);
            mGetContent.launch(intent);
        }
    };

    ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        String nom = data.getStringExtra("nom");
                        String prenom = data.getStringExtra("prenom");
                        String courriel = data.getStringExtra("courriel");

                        liste.add(new Utilisateurs(prenom,nom,courriel));
                        adapter.submitList(new ArrayList<>(liste));
                    }
                }
            });

}