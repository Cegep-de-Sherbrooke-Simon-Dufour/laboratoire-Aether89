package com.aether89.a420_pc2_sh.UI;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aether89.a420_pc2_sh.data.UtilisateurRepository;
import com.aether89.a420_pc2_sh.data.Utilisateurs;

import java.util.List;

import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UtilisateurViewModel extends ViewModel {

    private UtilisateurRepository repository;
    private MutableLiveData<List<Utilisateurs>> utilisateursLiveData;

    @Inject
    public UtilisateurViewModel(UtilisateurRepository repository) {
        this.repository = repository;
        utilisateursLiveData = new MutableLiveData<>();
    }

    public void addUtilisateur(String firstname, String lastname, String courriel) {
        repository.addUtilisateur(new Utilisateurs(firstname, lastname, courriel));
        getUtilisateurs();
    }

    public void remove(Object utilisateur) {
        repository.remove((Utilisateurs) utilisateur);
        getUtilisateurs();
    }

    public LiveData<List<Utilisateurs>> getUtilisateurs() {
        return repository.getUtilisateursLiveData();
    }


}
