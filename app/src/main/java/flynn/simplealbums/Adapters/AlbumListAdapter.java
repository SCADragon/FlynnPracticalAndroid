package flynn.simplealbums.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import flynn.simplealbums.GalleryActivity;
import flynn.simplealbums.R;
import flynn.simplealbums.beans.AlbumModel;

/**
 * Created by gavin on 2017-11-14.
 */

public class AlbumListAdapter extends RecyclerView.Adapter {

    private ArrayList<AlbumModel> albumList;
    private Context context;

    public AlbumListAdapter(ArrayList<AlbumModel> albumList, Context context) {
        this.albumList = albumList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlbumListAdapter.AlbumViewHolder(LayoutInflater.from(context).inflate(R.layout.album_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlbumModel albumModel = albumList.get(position);
        AlbumViewHolder albumViewHolder = (AlbumViewHolder) holder;
        albumViewHolder.album_name.setText(albumModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }


    public class AlbumViewHolder extends RecyclerView.ViewHolder{

        private TextView album_name;
        private LinearLayout album_over_all_layout;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            album_name = (TextView) itemView.findViewById(R.id.album_name);
            album_over_all_layout = (LinearLayout) itemView.findViewById(R.id.album_over_all_layout);

            album_over_all_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer albumid = albumList.get(getAdapterPosition()).getId();
                    Intent intent = new Intent(context,GalleryActivity.class);
                    intent.putExtra("albumid",albumid);
                    context.startActivity(intent);
                }
            });

        }
    }
}
