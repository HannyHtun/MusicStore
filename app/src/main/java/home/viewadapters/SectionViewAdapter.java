package home.viewadapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devfest.musicstore.R;

import java.util.ArrayList;

import model.Section;


/**
 * Created by winhtaikaung on 5/11/16.
 */

public class SectionViewAdapter extends RecyclerView.Adapter<SectionViewAdapter.SectionViewHolder> {

    // Horizontal View Adapter
    private Context mContext;
    private ArrayList<Section> mSections;
    private SectionViewListener sectionViewListener;
    private AlbumViewAdapter mAlbumlistViewAdapter;
    private LinearLayoutManager mAlbumListLinearLayoutManager;

    public SectionViewAdapter(Context context, ArrayList<Section> sections, SectionViewListener vh) {

        this.mContext = context;
        this.mSections = sections;
        this.sectionViewListener = vh;
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflating section view
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.section_view, parent, false);

        return new SectionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        Section section = this.mSections.get(position);


        if (section != null) {
            holder.tvSectionTitle.setText(TextUtils.isEmpty(section.getTitle()) ? "" : section.getTitle());
            mAlbumlistViewAdapter = new AlbumViewAdapter(mContext, section.getmRow().getAlbums(), new AlbumViewAdapter.AlbumViewListener() {

                @Override
                public void onItemClickListener(String FilePath, int position, AlbumViewAdapter.AlbumViewHolder vh) {

                }

            });
            //BIND Album View Adapter
            mAlbumListLinearLayoutManager = new LinearLayoutManager(mContext);
            if (section.getType().equals("HORIZONTAL")) {
                mAlbumListLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            } else if (section.getType().equals("GRID")) {
                // TODO implement GRID Layout
            } else if (section.getType().equals("VERTICAL")) {
                // TODO implement Vertical Layout manager
                mAlbumListLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            } else {
                // TODO Default Layout Manager
            }


            holder.albumRecyclerView.setLayoutManager(mAlbumListLinearLayoutManager);
            holder.albumRecyclerView.setAdapter(mAlbumlistViewAdapter);

        }


    }

    public interface SectionViewListener {
        void onItemClickListener(String FilePath, int position, SectionViewHolder vh);

        void onMenuClickListener(String FilePath, int position, SectionViewHolder vh);

    }

    @Override
    public int getItemCount() {
        return this.mSections.size();
    }

    // View Holder Class
    public class SectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvSectionTitle;
        private RecyclerView albumRecyclerView;

        public SectionViewHolder(View itemView) {
            super(itemView);
            tvSectionTitle = (TextView) itemView.findViewById(R.id.tv_section_title);
            albumRecyclerView = (RecyclerView) itemView.findViewById(R.id.albumListRecyclerView);

        }

        @Override
        public void onClick(View v) {

            final int position = getAdapterPosition();
            if (v instanceof ImageView) {
                // TODO Tohandle if user click Image Vue

            } else {
                sectionViewListener.onItemClickListener(String.valueOf(mSections.get(position).getTitle()), position, this);
            }
        }


    }
}
