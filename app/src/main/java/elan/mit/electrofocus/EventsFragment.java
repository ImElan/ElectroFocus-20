package elan.mit.electrofocus;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import elan.mit.electrofocus.R;


public class EventsFragment extends Fragment
{
    public EventsFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View mView = inflater.inflate(R.layout.fragment_events, container, false);

        //ROBOTICS

        LinearLayout mAllTerrain = mView.findViewById(R.id.all_terrain_id);
        mAllTerrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent AllTerrain = new Intent(getContext(),AllTerrain.class);
                startActivity(AllTerrain);
            }
        });
        LinearLayout mLineFollower = mView.findViewById(R.id.line_follower_id);
        mLineFollower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent LineFollower = new Intent(getContext(),LineFollower.class);
                startActivity(LineFollower);
            }
        });
        LinearLayout mRoboWar = mView.findViewById(R.id.robo_war);
        mRoboWar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent RoboWar = new Intent(getContext(),RoboWar.class);
                startActivity(RoboWar);
            }
        });
        LinearLayout mRoboSoccer = mView.findViewById(R.id.robo_soccer);
        mRoboSoccer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent RoboSoccer = new Intent(getContext(),RoboSoccer.class);
                startActivity(RoboSoccer);
            }
        });
        LinearLayout mRoboRace = mView.findViewById(R.id.robo_race);
        mRoboRace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent RoboRace = new Intent(getContext(),RoboRace.class);
                startActivity(RoboRace);
            }
        });


        // NON TECHNICAL

        LinearLayout mRubiksCube = mView.findViewById(R.id.rubiks_cube);
        mRubiksCube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent RubiksCube = new Intent(getContext(), RubiksCube.class);
                startActivity(RubiksCube);
            }
        });

        LinearLayout mFifa = mView.findViewById(R.id.fifa);
        mFifa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent Fifa = new Intent(getContext(), Fifa.class);
                startActivity(Fifa);
            }
        });

        LinearLayout mIplAuction = mView.findViewById(R.id.ipl_auction);
        mIplAuction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IplAuction = new Intent(getContext(),IplAuction.class);
                startActivity(IplAuction);
            }
        });

        LinearLayout mVoiceOver = mView.findViewById(R.id.voice_over);
        mVoiceOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent VoiceOver = new Intent(getContext(), VoiceOver.class);
                startActivity(VoiceOver);
            }
        });

        LinearLayout mBlueShirt = mView.findViewById(R.id.blue_shirt);
        mBlueShirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent BlueShirt = new Intent(getContext(), BlueShirtReview.class);
                startActivity(BlueShirt);
            }
        });

        LinearLayout mShipWreck = mView.findViewById(R.id.ship_wreck);
        mShipWreck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent ShipWreck  = new Intent(getContext(), ShipWreck.class);
                startActivity(ShipWreck );
            }
        });

        LinearLayout mJAM = mView.findViewById(R.id.jam);
        mJAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent JAM  = new Intent(getContext(), JAM.class);
                startActivity(JAM );
            }
        });


        // TECHNICAL


        LinearLayout mLiasTheLink = mView.findViewById(R.id.lias_the_link);
        mLiasTheLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent LiasTheLink = new Intent(getContext(),LiasTheLink.class);
                startActivity(LiasTheLink);
            }
        });

        LinearLayout mMuProsAndCons = mView.findViewById(R.id.mu_pros_and_cons);
        mMuProsAndCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent MuProsAndCons = new Intent(getContext(),MuProsAndCons.class);
                startActivity(MuProsAndCons);
            }
        });

        LinearLayout mWorkTheNetwork = mView.findViewById(R.id.work_the_network);
        mWorkTheNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent WorkTheNetwork = new Intent(getContext(), WorkTheNetwork.class);
                startActivity(WorkTheNetwork);
            }
        });

        LinearLayout mEDesign= mView.findViewById(R.id.e_design);
        mEDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent EDesign = new Intent(getContext(),E_Design.class);
                startActivity(EDesign);
            }
        });

        LinearLayout mProjectDisplay= mView.findViewById(R.id.project_display);
        mProjectDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent ProjectDisplay = new Intent(getContext(),ProjectDisplay.class);
                startActivity(ProjectDisplay);
            }
        });

        LinearLayout mCrypTech = mView.findViewById(R.id.cryp_tech);
        mCrypTech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent CrypTech = new Intent(getContext(), CrypTech.class);
                startActivity(CrypTech);
            }
        });

        LinearLayout mCStruct = mView.findViewById(R.id.c_struct);
        mCStruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent CStruct = new Intent(getContext(),CStruct.class);
                startActivity(CStruct);
            }
        });

        LinearLayout mBinaryBattle= mView.findViewById(R.id.binary_battle);
        mBinaryBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent BinaryBattle = new Intent(getContext(), BinaryBattle.class);
                startActivity(BinaryBattle);
            }
        });

        LinearLayout mCircuitology = mView.findViewById(R.id.circutology);
        mCircuitology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent Circuitology = new Intent(getContext(), Circuitology.class);
                startActivity(Circuitology);
            }
        });

        LinearLayout mPaperPresentation = mView.findViewById(R.id.paper_presentation);
        mPaperPresentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent PaperPresentation = new Intent(getContext(),PaperPresentation.class);
                startActivity(PaperPresentation);
            }
        });

        // ONLINE EVENT

        LinearLayout mPhonePhotoGraphy = mView.findViewById(R.id.phone_photography);
        mPhonePhotoGraphy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent PhonePhotoGraphy = new Intent(getContext(),PhonePhotoGraphy.class);
                startActivity(PhonePhotoGraphy);
            }
        });

        LinearLayout mQuiz = mView.findViewById(R.id.quiz);
        mQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent Quiz = new Intent(getContext(), Quiz.class);
                startActivity(Quiz);
            }
        });

        return mView;
    }
}
