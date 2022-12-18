package com.example.ingemann;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ingemann.Fragments.CreateInvoiceFragment;
import com.example.ingemann.Fragments.ViewInvoicesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private CreateInvoiceFragment createInvoiceFragment = new CreateInvoiceFragment();
    private ViewInvoicesFragment viewInvoicesFragment = new ViewInvoicesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bindUI();

    }

    private void bindUI(){
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        loadFragment(createInvoiceFragment);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.createInvoiceFragment:
                    loadFragment(createInvoiceFragment);
                    return true;
                case R.id.viewInvoicesFragment:
                    loadFragment(viewInvoicesFragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}