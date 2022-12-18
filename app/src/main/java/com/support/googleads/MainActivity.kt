package com.support.googleads
//library for Button, View and Toast
// important library for Google adMob

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class MainActivity : AppCompatActivity() {
    //creating Object of RewardedAd
    private var rewardedAd: RewardedAd? = null

    //creating Object of Buttons
    private var loadAdBtn: Button? = null
    private var NextBtn:Button?=null
   // private var showAdBtn: Button? = null

    //creating Object of RewardedAdLoadCallback
    var rewardedAdLoadCallback: RewardedAdLoadCallback? = null

    //creating Object of RewardedAdCallback
    var rewardedAdCallback: RewardedAdCallback? = null
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  MobileAds.initialize(this, "ca-app-pub-4761500786576152~8215465788");
        //initializing the Google Admob SDK
//        MobileAds.initialize(this) { initializationStatus -> //Showing a simple Toast Message to the user when The Google AdMob Sdk Initialization is Completed
//            Toast.makeText(this@MainActivity, "AdMob Sdk Initialize $initializationStatus", Toast.LENGTH_LONG).show()
//        }

        //Initializing the RewardedAd  objects
        rewardedAd = RewardedAd(this, "ca-app-pub-3940256099942544/5224354917")

        // Initializing the Button  objects to their respective views from activity_main.xml file
        loadAdBtn = findViewById<View>(R.id.loadRewardedBtn) as Button
        NextBtn=findViewById<View>(R.id.NextBtn) as Button
       // showAdBtn = findViewById<View>(R.id.showRewardedBtn) as Button

        //OnClickListener listeners for loadAdBtn and showAdBtn buttons
        loadAdBtn!!.setOnClickListener { //calling the loadRewardedAd method to load  the Rewarded Ad
            loadRewardedAd()
            showRewardedAd()
        }
        NextBtn!!.setOnClickListener{
            val intent=Intent(this,TestAdsActivity::class.java)
              startActivity(intent)
        }
//        showAdBtn!!.setOnClickListener { //calling the showRewardedAd method to show the Rewarded Ad
//
//        }

        // creating  RewardedAdLoadCallback for Rewarded Ad with some 2 Override methods
        rewardedAdLoadCallback = object : RewardedAdLoadCallback() {
            override fun onRewardedAdLoaded() {
                // Showing a simple Toast message to user when Rewarded Ad Failed to Load
                Toast.makeText(this@MainActivity, "Rewarded Ad is Loaded", Toast.LENGTH_LONG).show()
            }

            override fun onRewardedAdFailedToLoad(adError: LoadAdError?) {
                // Showing a simple Toast message to user when Rewarded Ad Failed to Load
                Toast.makeText(this@MainActivity, "Rewarded Ad is Loaded", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loadRewardedAd() {
        // Creating  an Ad Request
        val adRequest = AdRequest.Builder().build()

        // load Rewarded Ad with the Request
        rewardedAd!!.loadAd(adRequest, rewardedAdLoadCallback)

        // Showing a simple Toast message to user when Rewarded an ad is Loading
        Toast.makeText(this@MainActivity, "Rewarded Ad is loading ", Toast.LENGTH_LONG).show()
    }

    private fun showRewardedAd() {
        if (rewardedAd!!.isLoaded()) {

            //creating the Rewarded Ad Callback and showing the user appropriate message
            rewardedAdCallback = object : RewardedAdCallback() {
               override fun onRewardedAdOpened() {
                    // Showing a simple Toast message to user when Rewarded Ad is opened
                    Toast.makeText(this@MainActivity, "Rewarded Ad is Opened", Toast.LENGTH_LONG)
                        .show()
                }

               override fun onRewardedAdClosed() {
                    // Showing a simple Toast message to user when Rewarded Ad is closed
                    Toast.makeText(this@MainActivity, "Rewarded Ad Closed", Toast.LENGTH_LONG)
                        .show()
                }

              override  fun onUserEarnedReward(reward: RewardItem) {
                    // Showing a simple Toast message to user when user earned the reward by completely watching the Rewarded Ad
                    Toast.makeText(
                        this@MainActivity,
                        "You won the reward :" + reward.amount,
                        Toast.LENGTH_LONG
                    ).show()
                }

              override  fun onRewardedAdFailedToShow(adError: AdError) {
                    // Showing a simple Toast message to user when Rewarded Ad Failed to Show
                    Toast.makeText(
                        this@MainActivity,
                        "Rewarded Ad failed to show due to error:$adError", Toast.LENGTH_LONG
                    ).show()
                }
            }

            //showing the ad Rewarded Ad if it is loaded
            rewardedAd!!.show(this@MainActivity, rewardedAdCallback)

            // Showing a simple Toast message to user when an Rewarded ad is shown to the user
            Toast.makeText(
                this@MainActivity,
                "Rewarded Ad  is loaded and Now showing ad  ",
                Toast.LENGTH_LONG
            ).show()
        } else {
            //Load the Rewarded ad if it is not loaded
            loadRewardedAd()

            // Showing a simple Toast message to user when Rewarded ad is not loaded
            Toast.makeText(this@MainActivity, "Rewarded Ad is not Loaded ", Toast.LENGTH_LONG)
                .show()
        }
    }
}