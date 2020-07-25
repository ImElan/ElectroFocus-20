package elan.mit.electrofocus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import elan.mit.electrofocus.R;

public class SliderAdapter extends PagerAdapter
{

    private Context mContext;
    private LayoutInflater layoutInflater;

    public SliderAdapter(Context mContext)
    {
        this.mContext = mContext;
    }

    private int[] sliderImages = {R.drawable.slide_1,R.drawable.slide_2,R.drawable.slide_3,R.drawable.slide_4,R.drawable.slide_5};

    private String[] sliderHeadings = {"ELECTROFOCUS","WORKSHOPS","STAY UPDATED","EVENTS","VALUE FOR MONEY"};

    private String[] sliderDescriptions =
            {"An Inter college National level technical flamboyance With 25 events for elex and algo stream brain boxes and 8+ workshops.",
            "Grab your seat in our exciting workshops on emerging technologies and get closer to your dream job!",
                    "We'll keep you posted. Visit our home page to stay updated.",
                    "Attend our events and show off your talents!... Our events page explains everything you need to know about all of our events. You can also view your results and  the selection list for the next round .",
                    "Our goal is to give you the best experience through  challenging events and terrific workshops. We'll make sure that every penny you invest is well spent."
            };



    @Override
    public int getCount() {
        return sliderHeadings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView SlideImage = view.findViewById(R.id.slide_image);
        TextView SlideHeading = view.findViewById(R.id.slide_heading);
        TextView SlideDescription = view.findViewById(R.id.slide_description);

        SlideImage.setImageResource(sliderImages[position]);
        SlideHeading.setText(sliderHeadings[position]);
        SlideDescription.setText(sliderDescriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
