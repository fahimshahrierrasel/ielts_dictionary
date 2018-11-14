package com.fahimshahrierrasel.dictionary.main.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fahimshahrierrasel.dictionary.R;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainer);

        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, mainFragment);
            transaction.commit();
        }

        mainPresenter = new MainPresenter(mainFragment);

    }

    public void setActionBarTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackStackChanged() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment instanceof MainFragment) {
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        }
    }
}
