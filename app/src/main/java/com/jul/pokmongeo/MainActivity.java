package com.jul.pokmongeo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.jul.pokmongeo.databinding.ActivityMainBinding;
import com.jul.pokmongeo.databinding.PokemonItemBindingImpl;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        showStartup();
    }
    public void showStartup() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        OnClickOnNoteListener listener = this::showNoteDetail;
        PokedexFragment fragment = new PokedexFragment();
        fragment.setOnClickOnNoteListener(listener);
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }
    public void showNoteDetail(Pokemon p){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        OnClickOnButtonListener listener = this::clickButtonReturn;
        PokemonInfo fragment = new PokemonInfo(p); // à changer avec le nouveau fragment
        fragment.setOnClickOnButtonListener(listener);
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }

    public void clickButtonReturn(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        OnClickOnNoteListener listener = this::showNoteDetail;
        PokedexFragment fragment = new PokedexFragment(); // à changer avec le nouveau fragment
        fragment.setOnClickOnNoteListener(listener);
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }
}


