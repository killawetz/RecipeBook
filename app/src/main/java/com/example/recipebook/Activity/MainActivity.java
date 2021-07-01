package com.example.recipebook.Activity;

import android.os.Bundle;

import com.example.recipebook.R;
import com.example.recipebook.databinding.MainActivityBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.recipeListFragment, R.id.searchRecipeFragment, R.id.randomRecipeFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        //navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

   /* private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_search_item:
                    navController.navigate(R.id.navigation_search);
                    return true;
                case R.id.navigation_random_item:
                    navController.navigate(R.id.navigation_random);
                    return true;
                case R.id.navigation_list_item:
                    navController.navigate(R.id.navigation_list);
                    return true;
            }
            return false;
        }
    };*/


    /*public void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mega_container, fragment)// id вашего FrameLayout
                .addToBackStack(null)
                .commit();
    }*/

}