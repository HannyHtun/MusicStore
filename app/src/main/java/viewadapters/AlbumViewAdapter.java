package viewadapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.devfest.musicstore.R;

import java.util.ArrayList;

import model.Album;



public class AlbumViewAdapter extends RecyclerView.Adapter<AlbumViewAdapter.AlbumViewHolder> {

    // Horizontal View Adapter
    private Context mContext;
    private ArrayList<Album> mAlbumList;
    private AlbumViewListener albumViewListener;
    private int mOrientation;

    public AlbumViewAdapter(Context context, ArrayList<Album> albums, int orientation, AlbumViewListener fh) {

        this.mContext = context;
        this.mAlbumList = albums;
        this.albumViewListener = fh;
        this.mOrientation = orientation;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        if (this.mOrientation == LinearLayoutManager.HORIZONTAL) {
            itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(R.layout.album_view_horizontal, parent, false);
        } else {
            itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(R.layout.album_view_vertical, parent, false);
        }

        return new AlbumViewAdapter.AlbumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        Album album = this.mAlbumList.get(position);


        if (album != null) {
            holder.tvAlbumTitle.setText(TextUtils.isEmpty(album.getTitle()) ? "" : album.getTitle());

            holder.tvFollowers.setText(converttoK(album.getFollowers()));

            if (album.getImgUrl() != null && !TextUtils.isEmpty(album.getImgUrl())) {
                holder.ivDefaultImage.setVisibility(View.GONE);
                Glide.with(mContext).load(album.getImgUrl()).centerCrop().into(holder.ivAlbumImage);
            } else {
                holder.ivAlbumImage.setVisibility(View.GONE);
                holder.ivDefaultImage.setBackgroundResource(R.drawable.ic_album);
            }


        }
    }

    @Override
    public void onViewRecycled(AlbumViewHolder holder) {
        super.onViewRecycled(holder);
        holder.cleanUp();
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
        private TextView tvAlbumTitle;
        private TextView tvFollowers;
        private ImageView ivDefaultImage;
        private ImageView ivAlbumImage;
        private FrameLayout folderContainerLayout;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            tvAlbumTitle = (TextView) itemView.findViewById(R.id.album_title);
            tvFollowers = (TextView) itemView.findViewById(R.id.tv_followers);
            ivDefaultImage = (ImageView) itemView.findViewById(R.id.iv_album_default_view);
            ivAlbumImage = (ImageView) itemView.findViewById(R.id.iv_album_view);

            folderContainerLayout = (FrameLayout) itemView.findViewById(R.id.frame_album_container);
            folderContainerLayout.setOnClickListener(this);
        }

        public void cleanUp() {
            Glide.clear(ivAlbumImage);
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
