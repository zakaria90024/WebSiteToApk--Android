package com.androwep.androweptutorials.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.androwep.androweptutorials.app.Config;
import com.androwep.androweptutorials.utils.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessaging;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.app.ProgressDialog;
import android.net.Uri;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import com.androwep.androweptutorials.R;
import com.androwep.androweptutorials.databinding.ActivityHomeBinding;
import com.androwep.androweptutorials.homefragment.MainFragment;
import com.androwep.androweptutorials.java.JavaActivity;

import com.google.android.material.navigation.NavigationView;
import com.startapp.android.publish.ads.splash.SplashConfig;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.startapp.android.publish.adsCommon.VideoListener;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    ActionBarDrawerToggle mToogle;
    Toolbar mToolbar;
    ActivityHomeBinding mBinding;
    FragmentManager manager;
    MainFragment mainFragment;
    ProgressDialog progressDialog;
    private StartAppAd startAppAd = new StartAppAd(this);
    //AboutUsFragment aboutUsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

//        //for startapps ads
        StartAppSDK.init(this, "201848149", true);
        StartAppAd.showSplash(this, savedInstanceState,
                new SplashConfig()
                        .setTheme(SplashConfig.Theme.GLOOMY)
                        .setAppName("AndroWep")
                        .setLogo(R.drawable.androwep_logo)   // resource ID
                        .setOrientation(SplashConfig.Orientation.PORTRAIT)
        );

        startAppAd.loadAd(StartAppAd.AdMode.REWARDED_VIDEO);
        startAppAd.setVideoListener(new VideoListener() {
            @Override
            public void onVideoCompleted() {
                // Grant user with the reward
            }
        });


        setupNavigationView();
//      setSupportActionBar(mToolbar);
        manager = getSupportFragmentManager();

        mainFragment = new MainFragment();
//        aboutUsFragment=new AboutUsFragment();
        manager.beginTransaction().replace(mBinding.appbarMain.changeLayout.getId(), mainFragment).commit();

        //for title color
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#17BC1E' size='15px'>AndroWep-Tutorial</font>"));

//      for notification
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "One Notification Received\n"+message, Toast.LENGTH_LONG).show();

                }
            }
        };

        displayFirebaseRegId();

        //for progressbar
        setProgressDialog();
        progressbar();

    }

    private void displayFirebaseRegId() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId)){
            //txtRegId.setText("Firebase Reg Id: " + regId);
        }

        else{
            //txtRegId.setText("Firebase Reg Id is not received yet!");
        }

    }
    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }


    private void progressbar() {
        final ProgressBar pb = mBinding.appbarMain.progressBar;
//        pb.setVisibility(View.VISIBLE);
//
//        progressStatus = 0;
//
//                /*
//                    A Thread is a concurrent unit of execution. It has its own call stack for
//                    methods being invoked, their arguments and local variables. Each application
//                    has at least one thread running when it is started, the main thread,
//                    in the main ThreadGroup. The runtime keeps its own threads
//                    in the system thread group.
//                */
//        // Start the lengthy operation in a background thread
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (progressStatus < 100) {
//                    // Update the progress status
//                    progressStatus += 1;
//
//                    // Try to sleep the thread for 20 milliseconds
//                    try {
//                        Thread.sleep(20);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    // Update the progress bar
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            pb.setProgress(progressStatus);
//
//                        }
//                    });
//                }
//            }
//        }).start(); // Start the operation
//    }

    }


    //for first load Webview
    public  void setProgressDialog () {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..."); // Setting Message
//        progressDialog.setTitle("Please Wait"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(8000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId()==R.id.menu_refresh)
        {
            setupNavigationView();
            Intent intent = getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            finish();
            startActivity(intent);
        }

        else if(menuItem.getItemId()==R.id.menu_page)
        {
            //Way one
            String facebookId = "fb://page/2355169984542712";
            String urlPage = "http://www.facebook.com/androwepcom";

            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId)));
            } catch (Exception e) {

                //Open url web page.
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
            }

            //Way two
//            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
//            String facebookUrl = getFacebookPageURL(this);
//            facebookIntent.setData(Uri.parse(facebookUrl));
//            startActivity(facebookIntent);
        }

        else if(menuItem.getItemId()==R.id.menu_about)
        {
            Toast.makeText(this,"About Us",Toast.LENGTH_LONG).show();
            manager.beginTransaction().replace(mBinding.appbarMain.changeLayout.getId(),mainFragment).commit();
        }
        else if(menuItem.getItemId()==R.id.menu_rateus)
        {
            Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getBaseContext().getPackageName()));
            startActivity(rateIntent);

        }
        else if(menuItem.getItemId()==R.id.menu_codedit)
        {
            Intent intent = new Intent(HomeActivity.this, JavaActivity.class);
            startActivity(intent);
            finish();
        }


        mDrawerLayout.closeDrawers();
        return true;
    }

    //way two for facebook
//    public static String FACEBOOK_URL = "https://www.facebook.com/androwepcom";
//    public static String FACEBOOK_PAGE_ID = "fb://page/2355169984542712";
//
//    //method to get the right URL to use in the intent
//    public String getFacebookPageURL(Context context) {
//        PackageManager packageManager = context.getPackageManager();
//        try {
//            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
//            if (versionCode >= 3002850) { //newer versions of fb app
//                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
//            } else { //older versions of fb app
//                return "fb://page/" + FACEBOOK_PAGE_ID;
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            return FACEBOOK_URL; //normal web url
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId() == R.id.menu_refresh){

            setupNavigationView();
            Intent intent = getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            finish();
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationView() {

        mDrawerLayout = mBinding.drawerlayout;
        mNavigationView = mBinding.navigationview;
        mToolbar = mBinding.appbarMain.toolbar;
        setSupportActionBar(mToolbar);
        mToogle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.appbar_menu_main,menu);
        return true;
    }


}
