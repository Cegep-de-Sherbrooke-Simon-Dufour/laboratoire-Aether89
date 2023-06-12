package com.aether89.a420_pc2_sh.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ResourceCursorAdapter;
import android.widget.Toast;

import com.aether89.a420_pc2_sh.R;

public class AjoutUtilisateur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_utilisateur);

        Button btnAnnuler = findViewById(R.id.btn_Annuler);
        Button btnAjouter = findViewById(R.id.btn_Ajouter);

        btnAjouter.setOnClickListener(ClickAjouter);
        btnAnnuler.setOnClickListener(ClickAnnuler);
    }

    View.OnClickListener ClickAjouter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent resultat = new Intent();
            EditText nom = findViewById(R.id.et_Nom);
            EditText prenom = findViewById(R.id.et_Prenom);
            EditText courriel = findViewById(R.id.et_Courriel);

            String nomValue = nom.getText().toString();
            String prenomValue = prenom.getText().toString();
            String courrielValue = courriel.getText().toString();

            if (TextUtils.isEmpty(nomValue) || TextUtils.isEmpty(prenomValue) || TextUtils.isEmpty(courrielValue)) {

                Toast.makeText(getApplicationContext(), "Remplisser tout les champs!", Toast.LENGTH_SHORT).show();
            } else {
                resultat.putExtra("nom", nomValue);
                resultat.putExtra("prenom", prenomValue);
                resultat.putExtra("courriel", courrielValue);
                setResult(RESULT_OK, resultat);
                finish();
            }
        }
    };

    View.OnClickListener ClickAnnuler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}