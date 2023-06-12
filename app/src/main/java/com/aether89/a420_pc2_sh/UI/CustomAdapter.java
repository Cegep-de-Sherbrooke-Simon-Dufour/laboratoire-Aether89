package com.aether89.a420_pc2_sh.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aether89.a420_pc2_sh.R;
import com.aether89.a420_pc2_sh.data.Utilisateurs;

import java.util.List;

public class CustomAdapter extends ListAdapter<Utilisateurs, CustomAdapter.ViewHolder> {
    protected RecyclerCallback<Utilisateurs> callback;
    private final UtilisateurViewModel utilisateurs;

    public class ViewHolder extends RecyclerView.ViewHolder {
        Utilisateurs utilisateur;
        private final TextView vhtv_userEmail;
        private final TextView vhtv_userFullname;

        public ViewHolder(@NonNull View view) {
            super(view);
            vhtv_userFullname = view.findViewById(R.id.tv_userFullname);
            vhtv_userEmail = view.findViewById(R.id.tv_userEmail);

            itemView.setOnClickListener(itemView -> {
                callback.returnValue(utilisateur);
            });
        }

        public void bind(Utilisateurs utilisateur) {
            this.utilisateur = utilisateur;
        }

        public void setUserFullname(String text) {
            vhtv_userFullname.setText(text);
        }

        public void setUserEmail(String text) {
            vhtv_userEmail.setText(text);
        }
    }

    public void setCallback(RecyclerCallback<Utilisateurs> callback) {
        this.callback = callback;
    }

    public CustomAdapter(UtilisateurViewModel utilisateurs, RecyclerCallback<Utilisateurs> callback) {
        super(new DiffUtil.ItemCallback<Utilisateurs>() {
            @Override
            public boolean areItemsTheSame(Utilisateurs oldItem, Utilisateurs newItem) {
                return oldItem.getCourriel().equals(newItem.getCourriel());
            }

            @Override
            public boolean areContentsTheSame(Utilisateurs oldItem, Utilisateurs newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.utilisateurs = utilisateurs;
        this.callback = callback;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<Utilisateurs> utilisateursList = getCurrentList(); // Get the list of items
        Utilisateurs item = utilisateursList.get(position); // Get the item at the specified position
        holder.bind(item);
        holder.setUserFullname(item.getNomComplet());
        holder.setUserEmail(item.getCourriel());
    }


}
