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
import model.Album;


/**
 * Created by winhtaikaung on 5/11/16.
 */

public class GenereViewAdapter extends RecyclerView.Adapter<GenereViewAdapter.AlbumViewHolder> {

    // Horizontal View Adapter
    private Context mContext;
    private ArrayList<Album> mAlbumList;
    private AlbumViewListener albumViewListener;
    private int mOrientation;

    public GenereViewAdapter(Context context, ArrayList<Album> albums, int orientation, AlbumViewListener fh) {

        this.mContext = context;
        this.mAlbumList = albums;
        this.albumViewListener = fh;
        this.mOrientation = orientation;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;


        itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.genere_view_vertical, parent, false);


        return new GenereViewAdapter.AlbumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        Album album = this.mAlbumList.get(position);


        if (album != null) {
            holder.tvGenere.setText(TextUtils.isEmpty(album.getTitle()) ? "" : album.getTitle());

        }
    }

    private String converttoK(String num) throws NumberFormatException {

        if (!TextUtils.isEmpty(num)) {
            if (num.length() >= 4) {
                Long kVal = Long.valueOf(num);
                kVal = kVal / 1000;
                return String.valueOf(kVal) + "K";
            } else {
                return num;
            }
        }
        return "";
    }

    public interface AlbumViewListener {
        void onItemClickListener(String FilePath, int position, AlbumViewHolder vh);
    }

    @Override
    public int getItemCount() {
        return this.mAlbumList.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvGenere;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            tvGenere = (TextView) itemView.findViewById(R.id.tvGenere);

        }

        @Override
        public void onClick(View v) {

            final int position = getAdapterPosition();
            if (v instanceof ImageView) {
                // Tohandle if user click Image Vue


            } else {
                albumViewListener.onItemClickListener(String.valueOf(mAlbumList.get(position).getTitle()), position, this);
            }
        }
    }
}
