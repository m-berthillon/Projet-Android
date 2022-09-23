package com.jul.pokmongeo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jul.pokmongeo.databinding.PokedexFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PokedexFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        PokedexFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.pokedex_fragment,container,false);
        binding.pokemonList.setLayoutManager(new LinearLayoutManager(
                binding.getRoot().getContext()));

        List<Pokemon> pokemonList = new ArrayList<>();
        //Ouverture du fichier res/raw
        InputStreamReader isr = new InputStreamReader(getResources().openRawResource(R.raw.pokemons));
        // Ouverture du fichier dans assets
        // InputStreamReader isr =
        //new InputStreamReader(getResources().getAssets().open("pokemons.json"));
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder builder = new StringBuilder();
        String data = "";
        //lecture du fichier. data == null => EOF
        while(data != null) {
            try {
                data = reader.readLine();
                builder.append(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Traitement du fichier
        try {
            JSONArray array = new JSONArray(builder.toString());
            for (int i = 0; i < array.length(); i++) {
                Pokemon datapokemon = new Pokemon();
                JSONObject object = array.getJSONObject(i);
                String name = object.getString("name");
                String image = object.getString("image");
                String type1 = object.getString("type1");
                if (object.has("type2")) {
                    String type2 = object.getString("type2");
                    datapokemon.setType1(POKEMON_TYPE.valueOf(type2));
                }


                datapokemon.setOrder(i+1);
                datapokemon.setName(name);
                datapokemon.setFrontResource(getResources().getIdentifier(image,"drawable",binding.getRoot().getContext().getPackageName()));
                datapokemon.setType1(POKEMON_TYPE.valueOf(type1));
                pokemonList.add(datapokemon);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //int id = getResources().getIdentifier("nomDuDrawableSansExtension","drawable",binding.getRoot().getContext().getPackageName());
        PokemonListAdapter adapter = new PokemonListAdapter(pokemonList, listener);
        binding.pokemonList.setAdapter(adapter);

        return binding.getRoot();

    }
    private OnClickOnNoteListener listener;
    public void setOnClickOnNoteListener(OnClickOnNoteListener listener){
        this.listener=listener;
    }



}
