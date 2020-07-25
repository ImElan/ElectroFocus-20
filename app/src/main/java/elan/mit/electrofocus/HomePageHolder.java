package elan.mit.electrofocus;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class HomePageHolder extends RecyclerView.ViewHolder
{
    private View mView;

    public HomePageHolder(@NonNull View itemView)
    {
        super(itemView);
        Log.d("SEQUENCE_CHECK","Home Page Holder");
        mView = itemView;
    }
    void setImage(final String link,final Context context)
    {
        Log.d("IMAGE_LOG",link);
        Log.d("SEQUENCE_CHECK","Home Page Holder Set Image Method");
        final ImageView PostImage = mView.findViewById(R.id.post_image_id);
        Picasso.with(context)
                .load(link)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(R.color.imageBack)
                .into(PostImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError()
                    {
                        Picasso.with(context)
                                .load(link)
                                .placeholder(R.color.imageBack)
                                .into(PostImage);
                    }
                });
    }
    void setStory(String story)
    {
        TextView storyText = mView.findViewById(R.id.story_id);
        storyText.setText(story);
    }
    void setCaption(String caption)
    {
        TextView captionText = mView.findViewById(R.id.caption_id);
        captionText.setText(caption);
    }

    void setHomeTime(String time)
    {
        TextView timeText = mView.findViewById(R.id.home_time_id);
        timeText.setText(time);
    }

    void setHomeDate(String date)
    {
        TextView timeText = mView.findViewById(R.id.home_date_id);
        timeText.setText(date);
    }
}
