package com.aether89.a420_pc2_sh.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.aether89.a420_pc2_sh.R;
public class fragment_ajout_Utilisateur extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ajout__utilisateur, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UtilisateurViewModel viewModel = new ViewModelProvider(requireActivity()).get(UtilisateurViewModel.class);

        Button btnAdd = view.findViewById(R.id.btn_Ajouter);
        Button btnCancel = view.findViewById(R.id.btn_Annuler);

        btnAdd.setOnClickListener(v -> {
            EditText nom = v.findViewById(R.id.et_Nom);
            EditText prenom = v.findViewById(R.id.et_Prenom);
            EditText courriel = v.findViewById(R.id.et_Courriel);

            String nomValue = nom.getText().toString();
            String prenomValue = prenom.getText().toString();
            String courrielValue = courriel.getText().toString();

            viewModel.addUtilisateur(prenomValue, nomValue, courrielValue);
            Navigation.findNavController(view).navigateUp();
        });
    }
}
