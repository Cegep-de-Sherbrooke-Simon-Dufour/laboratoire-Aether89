package com.aether89.a420_pc2_sh.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aether89.a420_pc2_sh.R;
import com.aether89.a420_pc2_sh.data.Utilisateurs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class fragment_liste_utilisateurs extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_liste_utilisateurs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UtilisateurViewModel viewModel = new ViewModelProvider(requireActivity()).get(UtilisateurViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        FloatingActionButton button = view.findViewById(R.id.fab_Ajouter);

        CustomAdapter adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getUtilisateurs().observe(getViewLifecycleOwner(), new Observer<List<Utilisateurs>>() {
            @Override
            public void onChanged(List<Utilisateurs> utilisateurs) {
                adapter.submitList(utilisateurs);
            }
        });

        button.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_fragment_liste_utilisateurs_to_fragment_ajout_Utilisateur);
        });
    }
}