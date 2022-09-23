package com.jul.pokmongeo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jul.pokmongeo.databinding.PokedexFragmentBinding;
import com.jul.pokmongeo.databinding.PokemonInfoBinding;

public class PokemonInfo extends Fragment{
    private Pokemon pokemon;
    OnClickOnButtonListener listener;

    public PokemonInfo(Pokemon p) {
        this.pokemon = p;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        PokemonInfoBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.pokemon_info,container,false);
        PokemonViewModel viewModel = new PokemonViewModel();
        binding.setPokemonInfoModel(viewModel);
        binding.getPokemonInfoModel().setPokemon(pokemon);

        Button button = (Button) binding.getRoot().findViewById(R.id.returnButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onClickOnButton();
                }
            }
        });


        return binding.getRoot();

    }
    public void setOnClickOnButtonListener(OnClickOnButtonListener listener){
        this.listener=listener;
    }
}