package cr.ac.ucr.rickandmortyapi.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cr.ac.ucr.rickandmortyapi.R;

public class CharacterFragment extends Fragment {

    private AppCompatActivity activity;

    public CharacterFragment() {
        // Required empty public constructor
    }

    public static CharacterFragment newInstance() {
        CharacterFragment fragment = new CharacterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ToDo: inicializar variables que no depende de la vista(layout)
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //ToDo: Inicializar viariables que no depende de la vista. Todos los componentes visuales (textView,Btn, EditsText)

        View view = inflater.inflate(R.layout.fragment_character, container, false);

        RecyclerView rvCharacters = view.findViewById(R.id.rv_characters);

        //ArrayList --> Adapter <-- RecyclerView

        //ToDo: crear Adapter

        //ToDo: setAdapter

        //ToDo: setLayoutManager

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //ToDo: se hace la lógica
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;

    }
}