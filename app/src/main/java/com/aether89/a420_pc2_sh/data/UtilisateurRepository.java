package com.aether89.a420_pc2_sh.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UtilisateurRepository {
        @Inject
        public UtilisateurRepository() {}

        private ArrayList<Utilisateurs> utilisateurs = new ArrayList<>();

        private final MutableLiveData<List<Utilisateurs>> utilisateursLiveData = new MutableLiveData<>(utilisateurs);


    public void addUtilisateur(Utilisateurs utilisateur) {
            utilisateurs.add(utilisateur);
            utilisateursLiveData.setValue(utilisateurs);
        }

        public void remove(Utilisateurs utilisateur) {
            utilisateurs.remove(utilisateur);
        }

    public MutableLiveData<List<Utilisateurs>> getUtilisateursLiveData() {
        return utilisateursLiveData;
    }
    }
