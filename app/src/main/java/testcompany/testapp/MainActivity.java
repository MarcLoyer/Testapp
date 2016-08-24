package testcompany.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

/**
 * This code was developed by following the tutorials here:
 *   https://firebase.google.com/docs/admob/android/quick-start
 *   https://firebase.google.com/docs/admob/android/interstitial
 *
 *   These are just the first two of a series of tutorials on adMob.
 *   The box on the left of the page has links to the others. In-app
 *   purchases and rewardVideos look interesting.
 */
public class MainActivity extends AppCompatActivity {
    InterstitialAd mInterstitialAd;
    Button mNewGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");
        // Note: The ad unit ID we're using above is one that returns test ads. Your apps will
        // have their own ad units, which you can create at google.com/admob. Note that while
        // we've hardcoded the ad unit ID into the activity class in this example, the ID values
        // can also be stored in string resource files.

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
       // AdRequest adRequest = new AdRequest.Builder().addTestDevice("need to get the device ID from log cat");
        mAdView.loadAd(adRequest);

        mNewGameButton = (Button) findViewById(R.id.retry_button);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        // Note: The ad unit ID we're using above is one that returns test ads. Your apps will
        // have their own ad units, which you can create at google.com/admob. Note that while
        // we've hardcoded the ad unit ID into the activity class in this example, the ID values
        // can also be stored in string resource files.

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                beginPlayingGame();
            }
        });

        requestNewInterstitial();

        mNewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    beginPlayingGame();
                }
            }
        });

        beginPlayingGame();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void beginPlayingGame() {
        // Play for a while, then display the New Game Button
    }
}
