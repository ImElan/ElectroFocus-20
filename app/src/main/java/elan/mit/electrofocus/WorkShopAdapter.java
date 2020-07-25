package elan.mit.electrofocus;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import elan.mit.electrofocus.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class WorkShopAdapter extends RecyclerView.ViewHolder
{
    public View mView;
    public WorkShopAdapter(@NonNull View itemView)
    {
        super(itemView);
        mView = itemView;
    }

    void setPhoto(final Context context,int position)
    {

        final ImageView PostImage = mView.findViewById(R.id.workshop_image_id);
        int id = R.color.imageBack;

        switch (position)
        {
            case 0:
                id = R.drawable.cyber;
                break;
            case 1:
                id = R.drawable.autonomous_cars;
                break;
            case 2:
                id = R.drawable.rf_5g;
                break;
            case 3:
                id = R.drawable.network_security;
                break;
            case 4:
                id = R.drawable.iot;
                break;
            case 5:
                id = R.drawable.evolution_of_5g;
                break;
            case 6:
                id = R.drawable.artificial_intelligence;
                break;
            case 7:
                id = R.drawable.computer_vision;
                break;
            case 8:
                id = R.drawable.arduino_new;
                break;
            case 9:
                id = R.drawable.wifi;
                break;
        }

        final int finalId = id;
        Picasso.with(context)
                .load(id)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(R.color.imageBack)
                .into(PostImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError()
                    {
                        Glide.with(context)
                                .load(finalId)
                                .placeholder(R.color.imageBack)
                                .into(PostImage);
                    }
                });
    }

    void setTitleHolder(String title)
    {
        TextView titleText = mView.findViewById(R.id.workshop_heading_id);
        titleText.setText(title);
    }

    void setDateHolder(String date)
    {
        TextView dateText = mView.findViewById(R.id.workshop_date_id);
        dateText.setText(date);
    }

    void setDescriptionHolder(String description)
    {
        TextView descriptionText = mView.findViewById(R.id.workshop_description_id);
        descriptionText.setText(description);
    }

    void setVenueHolder(String venue)
    {
        TextView venueText = mView.findViewById(R.id.workshop_venue_id);
        venueText.setText(venue);
    }

    void setFeeHolder(String fee)
    {
        TextView feeText = mView.findViewById(R.id.workshop_fee_id);
        feeText.setText(fee);
    }

}
