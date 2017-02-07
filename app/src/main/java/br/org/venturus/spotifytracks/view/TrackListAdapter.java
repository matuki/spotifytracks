package br.org.venturus.spotifytracks.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.org.venturus.spotifytracks.R;
import br.org.venturus.spotifytracks.model.Image;
import br.org.venturus.spotifytracks.model.Track;

/**
 * Created by vntpamc on 2/2/2017.
 */

public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.ViewHolder> {

    private List<Track> mDataset;
    private Picasso mPicasso;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mTrackCoverImageView;
        public TextView mTrackNameTextView;
        public TextView mTrackArtistTextView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            mTrackCoverImageView = (ImageView) cardView.findViewById(R.id.track_cover_image_view);
            mTrackNameTextView = (TextView) cardView.findViewById(R.id.track_name_text_view);
            mTrackArtistTextView = (TextView) cardView.findViewById(R.id.track_artist_text_view);
        }
    }

    public TrackListAdapter(List<Track> dataset) {
        mDataset = dataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView trackCardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.track_card_layout, parent, false);
        ViewHolder vh = new ViewHolder(trackCardView);

        if (mPicasso == null) {
            mPicasso = Picasso.with(parent.getContext());
        }

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Track track = mDataset.get(position);

        String imageUrl = track.getAlbum().getImages().get(0).getUrl();
        mPicasso.load(imageUrl).into(holder.mTrackCoverImageView);

        holder.mTrackNameTextView.setText(track.getName());
        holder.mTrackArtistTextView.setText(track.getArtists().get(0).getName());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
