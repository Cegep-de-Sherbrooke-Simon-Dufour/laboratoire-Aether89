package com.aether89.a420_pc2_sh.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aether89.a420_pc2_sh.R;
import com.aether89.a420_pc2_sh.data.Utilisateurs;

import java.util.List;

public class CustomAdapter extends ListAdapter<Utilisateurs, CustomAdapter.ViewHolder> {
    private UtilisateurViewModel viewModel;


    protected CustomAdapter(UtilisateurViewModel viewModel) {
        super(new DiffUtil.ItemCallback<Utilisateurs>() {
            @Override
            public boolean areItemsTheSame(@NonNull Utilisateurs oldItem, @NonNull Utilisateurs newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Utilisateurs oldItem, @NonNull Utilisateurs newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Utilisateurs utilisateur);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView email;
        Utilisateurs utilisateur;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_userFullname);
            email = itemView.findViewById(R.id.tv_userEmail);

            itemView.setOnClickListener(v -> {
                if (utilisateur != null) {
                    viewModel.remove(utilisateur);
                }
            });
        }

        public void bind(Utilisateurs utilisateur) {
            this.utilisateur = utilisateur;
            name.setText(utilisateur.getNomComplet());
            email.setText(utilisateur.getCourriel());
        }
    }
}

