package cr.ac.ucr.rickandmortyapi.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import cr.ac.ucr.rickandmortyapi.R;
import cr.ac.ucr.rickandmortyapi.adapters.EpisodesAdapter;
import cr.ac.ucr.rickandmortyapi.api.EpisodeService;
import cr.ac.ucr.rickandmortyapi.api.RetrofitBuilder;
import cr.ac.ucr.rickandmortyapi.models.Episode;
import cr.ac.ucr.rickandmortyapi.models.EpisodeResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EpisodesFragment extends Fragment {

    private AppCompatActivity activity;
    private ArrayList<Episode> episodes;
    private static final String TAG = "EpisodesFragment";
    private EpisodesAdapter episodesAdapter;

    boolean canLoad = true;
    int limit = 0;
    int page = 1;
    private ProgressBar pb_loading;
    private RecyclerView rvEpisodes;

    public EpisodesFragment() {
        // Required empty public constructor
    }

    public static EpisodesFragment newInstance() {
        EpisodesFragment fragment = new EpisodesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        episodes = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_episodes, container, false);

        pb_loading = view.findViewById(R.id.pb_loading);
        rvEpisodes = view.findViewById(R.id.rv_episodes);

        episodesAdapter = new EpisodesAdapter(activity);
        rvEpisodes.setAdapter(episodesAdapter);
        rvEpisodes.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 3);

        rvEpisodes.setLayoutManager(linearLayoutManager);

        episodesAdapter.addEpisodes(episodes);

        setupRVScrollListener(rvEpisodes, linearLayoutManager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ToDo: se hace la lógica
        getEpisodesInfo(page);
    }

    private void getEpisodesInfo(int page) {
        canLoad = false;

        EpisodeService episodeService = RetrofitBuilder.createService(EpisodeService.class);

        Call<EpisodeResponse> response = episodeService.getEpisode(page);

        response.enqueue(new Callback<EpisodeResponse>() {
            @Override
            public void onResponse( @NonNull Call<EpisodeResponse> call, @Nullable Response<EpisodeResponse> response) {
                if (response.isSuccessful()){

                    EpisodeResponse episodeResponse = response.body();

                    ArrayList<Episode> episodes = episodeResponse.getResults();
                    Log.e(TAG,"episodes: " + response);
                    episodesAdapter.addEpisodes(episodes);

                    showEpisodes(true);

                }else {
                    Log.e(TAG,"onError " + response.errorBody());
                }
                canLoad = true;
            }

            @Override
            public void onFailure(Call<EpisodeResponse> call, Throwable t) {
                canLoad = true;
                throw new RuntimeException(t);
            }

        });
    }

    private void setupRVScrollListener(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // dy = + -> personaje 0 a 20
                // dy = - -> personaje 20 a 0
                if (dy > 0) {
                    // Total de items
                    int totalItems = linearLayoutManager.getItemCount();

                    // Items que ya se mostraron
                    int pastItems = linearLayoutManager.findFirstVisibleItemPosition();

                    // items que se estan mostrando
                    int visibleItems = linearLayoutManager.getChildCount();

                    if(canLoad){
                        if((pastItems + visibleItems) >= totalItems ){
                            page++;

                            pb_loading.setVisibility(View.VISIBLE);

                            getEpisodesInfo(page);
                        }
                    }
                }

            }
        });
    }

    private void showEpisodes(boolean setVisible){
        rvEpisodes.setVisibility(setVisible ? View.VISIBLE :View.GONE);
        pb_loading.setVisibility(!setVisible ? View.VISIBLE: View.GONE);
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