package flynn.simplealbums.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import flynn.simplealbums.PhotoActivity;
import flynn.simplealbums.R;
import flynn.simplealbums.beans.PhotoModel;

public class GalleryAdapter extends RecyclerView.Adapter{

    private ArrayList<PhotoModel> photoList;
    private Context context;

    public GalleryAdapter(ArrayList<PhotoModel> photoList, Context context) {
        this.photoList = photoList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(LayoutInflater.from(context).inflate(R.layout.gallery_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PhotoViewHolder photoViewHolder = (PhotoViewHolder) holder;
        PhotoModel photoModel = photoList.get(position);
        Picasso.with(context).load(photoModel.getThumbnailUrl()).into(photoViewHolder.photo);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    private class PhotoViewHolder extends RecyclerView.ViewHolder{

        private ImageView photo;

        private PhotoViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.photo);

            photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String photoURL = photoList.get(getAdapterPosition()).getUrl();
                    Intent intent = new Intent(context,PhotoActivity.class);
                    intent.putExtra("photoURL",photoURL);
                    context.startActivity(intent);
                }
            });
        }
    }
}
