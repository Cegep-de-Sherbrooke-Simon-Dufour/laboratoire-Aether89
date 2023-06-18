package com.aether89.a420_pc2_sh.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.aether89.a420_pc2_sh.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
        private NavController navController;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            setSupportActionBar(findViewById(R.id.toolbar_main));

            NavHostFragment navHostFragment = (NavHostFragment)
                    getSupportFragmentManager().findFragmentById(R.id.fcv_main);
            if (navHostFragment != null) {
                navController = navHostFragment.getNavController();
            }

            NavigationUI.setupActionBarWithNavController(this, navController);
        }

        @Override
        public boolean onSupportNavigateUp() {
            navController.navigateUp();
            return super.onSupportNavigateUp();
        }
    }