package Adapter;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.R;

import java.util.List;

import Model.VideoModel;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    private Context context;
    private List<VideoModel> videoList;
    private boolean isFav = false;

    public VideoAdapter(Context context, List<VideoModel> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.firebase.R.layout.single_video_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        VideoModel videoModel = videoList.get(position);
        holder.textVideoTitle.setText(videoModel.getTitle());
        holder.textVideoDescription.setText(videoModel.getDescription());
        holder.videoView.setVideoURI(Uri.parse(videoModel.getUrl()));
        holder.favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFav){
                    holder.favorites.setImageResource(com.example.firebase.R.drawable.ic_launcher_foreground);
                    isFav = true;
                }
                else {
                    holder.favorites.setImageResource(com.example.firebase.R.drawable.baseline_brightness_1_24);
                    isFav = true;
                }
            }
        });
        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                holder.videoProgressBar.setVisibility(View.GONE);
                mp.start();

                float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                float screenRatio = holder.videoView.getWidth() / (float) holder.videoView.getHeight();
                float scale = videoRatio / screenRatio;

                if (scale >= 1f) {
                    holder.videoView.setScaleX(scale);
                } else {
                    holder.videoView.setScaleY(1f / scale);
                }

            }
        });

        holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                holder.videoView.setMediaController(new MediaController(context));
                holder.videoView.requestFocus();
                mp.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (videoList != null) ? videoList.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private VideoView videoView;
        private ProgressBar videoProgressBar;
        private TextView textVideoTitle, textVideoDescription;
        private ImageView imPerson, favorites, imShare, imMore;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(com.example.firebase.R.id.videoView);
            videoProgressBar = itemView.findViewById(com.example.firebase.R.id.videoProgressBar);
            textVideoTitle = itemView.findViewById(com.example.firebase.R.id.textVideoTitle);
            textVideoDescription = itemView.findViewById(com.example.firebase.R.id.textVideoDescription);
            imPerson = itemView.findViewById(com.example.firebase.R.id.imPerson);
            favorites = itemView.findViewById(com.example.firebase.R.id.favorites);
            imShare = itemView.findViewById(com.example.firebase.R.id.imShare);
            imMore = itemView.findViewById(com.example.firebase.R.id.imMore);
        }
    }
}
