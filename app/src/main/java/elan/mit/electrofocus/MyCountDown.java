package elan.mit.electrofocus;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;

public class MyCountDown extends CountDownTimer
{
    private Context mContext;
    private ShimmerFrameLayout layout;

    MyCountDown(ShimmerFrameLayout layout, Context mContext, long millisInFuture, long countDownInterval)
    {
        super(millisInFuture, countDownInterval);
        Log.d("SHIMMER_CHECK","Inside CountDown Method");
        this.mContext = mContext;
        this.layout = layout;
        start();
    }
    @Override
    public void onTick(long millisUntilFinished)
    {

    }

    @Override
    public void onFinish()
    {
//        Toast.makeText(mContext,"Over",Toast.LENGTH_LONG).show();
        layout.stopShimmer();
        layout.setVisibility(View.GONE);
    }
}
