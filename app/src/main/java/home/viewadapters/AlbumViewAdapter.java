package home.viewadapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.devfest.musicstore.R;

import java.util.ArrayList;

import model.Album;


/**
 * Created by winhtaikaung on 5/11/16.
 */

public class AlbumViewAdapter extends RecyclerView.Adapter<AlbumViewAdapter.AlbumViewHolder> {

    // Horizontal View Adapter
    private Context mContext;
    private ArrayList<Album> mAlbumList;
    private AlbumViewListener albumViewListener;

    public AlbumViewAdapter(Context context,ArrayList<Album> albums, AlbumViewListener fh) {

        this.mContext = context;
        this.mAlbumList = albums;
        this.albumViewListener = fh;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.album_view, parent, false);

        return new AlbumViewAdapter.AlbumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
         Album album =  this.mAlbumList.get(position);


        if(album != null){
            holder.tvAlbumTitle.setText(TextUtils.isEmpty(album.getTitle())?"":album.getTitle());;
            holder.tvFollowers.setText(TextUtils.isEmpty(album.getFollowers())?"":album.getFollowers());
            holder.ivAlbumImage.setBackgroundResource(R.drawable.ic_album);

        }



    }

    public interface AlbumViewListener {
        void onItemClickListener(String FilePath, int position, AlbumViewHolder vh);

        void onMenuClickListener(String FilePath, int position, AlbumViewHolder vh);

    }

    @Override
    public int getItemCount() {
        return this.mAlbumList.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvAlbumTitle;
        private TextView tvFollowers;
        private ImageView ivAlbumImage;
        private FrameLayout folderContainerLayout;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            tvAlbumTitle = (TextView) itemView.findViewById(R.id.album_title);
            tvFollowers = (TextView) itemView.findViewById(R.id.tv_followers);
            ivAlbumImage = (ImageView) itemView.findViewById(R.id.iv_album_view);

            folderContainerLayout = (FrameLayout) itemView.findViewById(R.id.frame_album_container);
            folderContainerLayout.setOnClickListener(this);
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
