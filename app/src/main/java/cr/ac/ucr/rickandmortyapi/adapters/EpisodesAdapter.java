package cr.ac.ucr.rickandmortyapi.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cr.ac.ucr.rickandmortyapi.R;
import cr.ac.ucr.rickandmortyapi.models.Episode;

public class EpisodesAdapter  extends RecyclerView.Adapter<EpisodesAdapter.ViewHolder> implements EpisodeItemClickListener {

    private Context context;
    private ArrayList<Episode> episodes;

    public EpisodesAdapter(Context context, ArrayList<Episode> episodes) {
        this.context = context;
        this.episodes = episodes;
    }

    public EpisodesAdapter(Context context) {
        this.context = context;
        this.episodes = new ArrayList<>();
    }

    // Carga el layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_episodes, parent, false);
        return new EpisodesAdapter.ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Episode episode = episodes.get(position);

        holder.tvName.setText(episode.getName());
        holder.tvAirDate.setText(episode.getAir_date());
        holder.tvEpisode.setText(episode.getEpisode());
    }


    @Override
    public int getItemCount() {
        return episodes != null ? episodes.size() : 0;
    }

    public void addEpisodes(ArrayList<Episode> episodes) {
        this.episodes.addAll(episodes);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view, int position) {
        //ToDo: cargar el activity
    }

    // Esta clase se encarga de obtener los elementos del layout
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cvEpisodeCard;

        private final TextView tvName;
        private final TextView tvAirDate;
        private final TextView tvEpisode;


        private EpisodeItemClickListener listener;

        public ViewHolder(@NonNull View view, EpisodeItemClickListener listener) {
            super(view);

            this.listener = listener;

            cvEpisodeCard = view.findViewById(R.id.cv_EpisodeCard);

            tvName = view.findViewById(R.id.tv_name);
            tvAirDate = view.findViewById(R.id.tv_air_date);
            tvEpisode = view.findViewById(R.id.tv_episode);

            cvEpisodeCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getLayoutPosition());
        }
    }
}

interface EpisodeItemClickListener {
    void onClick(View view, int position);
}
