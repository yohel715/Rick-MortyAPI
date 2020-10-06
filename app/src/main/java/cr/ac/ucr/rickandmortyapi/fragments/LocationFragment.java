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

import cr.ac.ucr.rickandmortyapi.adapters.LocationsAdapter;
import cr.ac.ucr.rickandmortyapi.api.EpisodeService;
import cr.ac.ucr.rickandmortyapi.api.LocationService;
import cr.ac.ucr.rickandmortyapi.api.RetrofitBuilder;

import cr.ac.ucr.rickandmortyapi.models.Location;
import cr.ac.ucr.rickandmortyapi.models.LocationResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LocationFragment extends Fragment {

    private AppCompatActivity activity;
    private ArrayList<Location> locations;
    private static final String TAG = "LocationFragment";
    private LocationsAdapter locationsAdapter;

    boolean canLoad = true;
    int limit = 0;
    int page = 1;
    private ProgressBar pb_loading;
    private RecyclerView rvLocations;

    public LocationFragment() {
        // Required empty public constructor
    }

    public static LocationFragment newInstance() {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locations = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location, container, false);

        pb_loading = view.findViewById(R.id.pb_loading);
        rvLocations = view.findViewById(R.id.rv_characters);

        locationsAdapter = new LocationsAdapter(activity);
        rvLocations.setAdapter(locationsAdapter);
        rvLocations.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 3);

        rvLocations.setLayoutManager(linearLayoutManager);

        locationsAdapter.addLocations(locations);

        setUpRVScrollListener(rvLocations, linearLayoutManager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ToDo: se hace la lógica
        getLocationsInfo(page);
    }

    private void getLocationsInfo(int page) {

        canLoad = false;

        LocationService locationService = RetrofitBuilder.createService(LocationService.class);

        Call<LocationResponse> response = locationService.getLocations(page);

        response.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse( @NonNull Call<LocationResponse> call, @Nullable Response<LocationResponse> response) {
                if (response.isSuccessful()){

                    LocationResponse locationResponse = response.body();

                    ArrayList<Location> locations = locationResponse.getResults();

                    locationsAdapter.addLocations(locations);

                    showLocations(true);

                }else {
                    Log.e(TAG,"onError " + response.errorBody());
                }
                canLoad = true;
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
                canLoad = true;
                throw new RuntimeException(t);
            }

        });
    }

    private void setUpRVScrollListener(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //dy = + -> si hace escroll hacia abajo
                //dy = - -> si hace escroll hacia arriba
                if(dy > 0){

                    //Total items
                    int totalItems = linearLayoutManager.getItemCount();
                    //Items que ya se mostraron
                    int pastItems = linearLayoutManager.findFirstVisibleItemPosition();
                    //Items que se están mostrando
                    int visibleItems = linearLayoutManager.getChildCount();

                    Log.i(TAG, "totalItems" + totalItems);
                    Log.i(TAG, "pastItems" + pastItems);
                    Log.i(TAG, "visibleItems" + visibleItems);

                    if(canLoad){
                        if((page+ visibleItems) >= totalItems){
                            page++;
                            pb_loading.setVisibility(View.VISIBLE);
                            getLocationsInfo(page);
                        }
                    }
                }
            }
        });
    }

    private void showLocations(boolean setVisible){
        rvLocations.setVisibility(setVisible ? View.VISIBLE :View.GONE);
        pb_loading.setVisibility(!setVisible ? View.GONE: View.GONE);
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