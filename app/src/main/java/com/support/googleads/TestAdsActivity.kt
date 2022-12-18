package com.support.googleads

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener


class TestAdsActivity : AppCompatActivity(), RewardedVideoAdListener {

    // on below line we are creating
    // a variable for button.
    lateinit var rewardVideoBtn: ImageView

    // on below line we are creating
    // a variable for reward video ad.
    lateinit var rewardVideoAds: RewardedVideoAd

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_ads)

        // initializing variables of below line with their id.
        rewardVideoBtn = findViewById(R.id.img_hint)

        // on the below line we are initializing our mobile ads.
        MobileAds.initialize(this)

        // on below line we are initializing our reward video ad.
        rewardVideoAds = MobileAds.getRewardedVideoAdInstance(this)

        // on below line we are setting reward video ad listener
        rewardVideoAds.rewardedVideoAdListener = this

        // on below line we are adding click
        // listener for our reward video button.
        rewardVideoBtn.setOnClickListener {
            // on below line we are calling
            // method to display ads.
            displayAds()
        }
    }

    private fun displayAds() {
        // on below line we are creating a
        // variable for ad request.
        val request = AdRequest.Builder().build()

        // on below line we are setting test ad id and loading our ad.
        rewardVideoAds.loadAd("ca-app-pub-3940256099942544/5224354917", request)
        Toast.makeText(this, "reward ad show", Toast.LENGTH_SHORT).show()

        // on below line we are checking if the reward video ad is loaded.
        if (rewardVideoAds.isLoaded) {
            Toast.makeText(this, "show video ads", Toast.LENGTH_SHORT).show()
            // if ad is loaded we have to
            // simply show our reward video ad.
            rewardVideoAds.show()
        }
    }

    // on below line we are calling
    // function which is use to load ad.
    override fun onRewardedVideoAdLoaded() {
        // when ad is loaded we have
        // to simply show the ad.
        rewardVideoAds.show()
    }

    // below function is called when reward video ad is
    // opened and we are simply displaying a toast message.
    override fun onRewardedVideoAdOpened() {
        Toast.makeText(this@TestAdsActivity, "Ad opened..", Toast.LENGTH_LONG).show()
    }

    // below function is called when reward video is
    // started and we are displaying a toast message
    override fun onRewardedVideoStarted() {
        Toast.makeText(this@TestAdsActivity, "Video started..", Toast.LENGTH_LONG).show()
    }

    // below function is called when reward
    // video ad is closed by the user.
    override fun onRewardedVideoAdClosed() {
        Toast.makeText(this@TestAdsActivity, "Ad closed..", Toast.LENGTH_LONG).show()
    }

    // below function is called when user
    // is rewarded with the reward.
    override fun onRewarded(p0: RewardItem?) {
        Toast.makeText(this@TestAdsActivity, "User rewarded..", Toast.LENGTH_LONG).show()
    }

    // below method is called when user has
    // left reward video ad has left.
    override fun onRewardedVideoAdLeftApplication() {
        Toast.makeText(this@TestAdsActivity, "Ad left application..", Toast.LENGTH_LONG).show()
    }

    // below method is called when reward
    // video ad is failed to load.
    override fun onRewardedVideoAdFailedToLoad(p0: Int) {
        Toast.makeText(this@TestAdsActivity, "Fail to load ad..", Toast.LENGTH_LONG).show()
    }

    // below method is called when
    // reward video is completed.
    override fun onRewardedVideoCompleted() {
        Toast.makeText(this@TestAdsActivity, "Reward video completed..", Toast.LENGTH_LONG).show()
    }

}
