package com.aether89.a420_pc2_sh.UI;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aether89.a420_pc2_sh.R;
import com.aether89.a420_pc2_sh.UtilisateurApplication;
import com.aether89.a420_pc2_sh.data.Utilisateurs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    CustomAdapter adapter;
    UtilisateurViewModel liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        liste = new ViewModelProvider(this).get(UtilisateurViewModel.class);

        FloatingActionButton fabAjouter = findViewById(R.id.fab_Ajouter);
        fabAjouter.setOnClickListener(clickActivityAjouter);

        adapter = new CustomAdapter(liste, new RecyclerCallback<Utilisateurs>() {
            @Override
            public void returnValue(Utilisateurs object) {

            }
        });

        RecyclerView recyclerViewMain = findViewById(R.id.recyclerVierMain);
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMain.setAdapter(adapter);


        adapter.setCallback(user -> {
            liste.remove(user);
            adapter.notifyDataSetChanged();
        });


        liste.getUtilisateurs().observe(this, new Observer<List<Utilisateurs>>() {
            @Override
            public void onChanged(List<Utilisateurs> utilisateurs) {
                adapter.submitList(utilisateurs);
                adapter.notifyDataSetChanged();

            }
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

                        liste.addUtilisateur(prenom, nom, courriel);
                        adapter.notifyDataSetChanged();

                    }
                }
            });

}