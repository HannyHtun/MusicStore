package viewadapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devfest.musicstore.R;

import java.util.ArrayList;

import model.Genere;


/**
 * Created by winhtaikaung on 5/11/16.
 */

public class GenereViewAdapter extends RecyclerView.Adapter<GenereViewAdapter.GenereViewHolder> {

    // Horizontal View Adapter
    private Context mContext;
    private ArrayList<Genere> mAlbumList;
    private GenereViewListener genereViewListener;
    private int mOrientation;

    public GenereViewAdapter(Context context, ArrayList<Genere> albums, int orientation, GenereViewListener fh) {

        this.mContext = context;
        this.mAlbumList = albums;
        this.genereViewListener = fh;
        this.mOrientation = orientation;
    }

    @Override
    public GenereViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;


        itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.genere_view, parent, false);


        return new GenereViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GenereViewHolder holder, int position) {
        Genere genere = this.mAlbumList.get(position);

        if (genere != null) {
            holder.tvGenere.setText(TextUtils.isEmpty(genere.getTitle()) ? "" : genere.getTitle());

        }
    }

    public interface GenereViewListener {
        void onItemClickListener(String FilePath, int position, GenereViewHolder vh);
    }

    @Override
    public int getItemCount() {
        return this.mAlbumList.size();
    }

    public class GenereViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvGenere;

        public GenereViewHolder(View itemView) {
            super(itemView);
            tvGenere = (TextView) itemView.findViewById(R.id.tvGenere);
        }

        @Override
        public void onClick(View v) {

            final int position = getAdapterPosition();
            if (v instanceof ImageView) {
                // Tohandle if user click Image Vue


            } else {
                genereViewListener.onItemClickListener(String.valueOf(mAlbumList.get(position).getTitle()), position, this);
            }
        }
    }
}
