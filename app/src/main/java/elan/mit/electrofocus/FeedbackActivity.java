package elan.mit.electrofocus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import elan.mit.electrofocus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FeedbackActivity extends AppCompatActivity
{
    private RadioButton radioButton;
    private DatabaseReference mFeedback;
    private ProgressDialog mRegProgress;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRegProgress = new ProgressDialog(this);

        mFeedback = FirebaseDatabase.getInstance().getReference().child("FeedBack");

        radioGroup = findViewById(R.id.radio_group);

        final TextInputLayout NameText = findViewById(R.id.name_EditText);
        final TextInputLayout CollegeText = findViewById(R.id.college_EditText);
        final TextInputLayout FeedbackText = findViewById(R.id.feedback_EditText);
        final TextInputLayout SuggestionText = findViewById(R.id.suggestions_EditText);


        Button SubmitButton = findViewById(R.id.submit_button);

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(isNetworkAvailable())
                {
                    String name = NameText.getEditText().getText().toString();
                    String college = CollegeText.getEditText().getText().toString();
                    String feedback = FeedbackText.getEditText().getText().toString();
                    String suggestion = SuggestionText.getEditText().getText().toString();

                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioId);

                    HashMap<String,String> FeedbackMap = new HashMap<>();
                    Log.d("EMPTY_CHECK",radioButton+"");
                    Log.d("EMPTY_CHECK",feedback);
                    if(!TextUtils.isEmpty(feedback) && radioButton != null)
                    {
                        mRegProgress.show();
                        mRegProgress.setTitle("Submitting Feedback");
                        mRegProgress.setMessage("Please wait...!");
                        mRegProgress.setCanceledOnTouchOutside(false);
                        mRegProgress.show();

                        String Experience = radioButton.getText().toString();
                        Log.d("EMPTY_CHECK",Experience);

                        FeedbackMap.put("name",name);
                        FeedbackMap.put("college",college);
                        FeedbackMap.put("feedback",feedback);
                        FeedbackMap.put("suggestions",suggestion);
                        FeedbackMap.put("user_experience",Experience);

                        if(TextUtils.isEmpty(name))
                        {
                            FeedbackMap.put("name","-------");
                        }
                        if(TextUtils.isEmpty(college))
                        {
                            FeedbackMap.put("college","-------");
                        }
                        if(TextUtils.isEmpty(suggestion))
                        {
                            FeedbackMap.put("suggestions","-------");
                        }

                        mFeedback.push().setValue(FeedbackMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    mRegProgress.dismiss();
                                    NameText.getEditText().setText("");
                                    FeedbackText.getEditText().setText("");
                                    CollegeText.getEditText().setText("");
                                    SuggestionText.getEditText().setText("");
                                    radioGroup.clearCheck();
                                    Toast.makeText(FeedbackActivity.this,"ThankYou For Your Feedback..!",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    mRegProgress.dismiss();
                                    Toast.makeText(FeedbackActivity.this,"Something went wrong try again later..!",Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                    else
                    {
                        Toast.makeText(FeedbackActivity.this,"Please fill out all the required fields",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(FeedbackActivity.this,"Please make sure your internet is on and then submit the feedback",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
