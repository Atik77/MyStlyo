package com.atik.mystlyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.tabs.TabLayout;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity  extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    GoogleApiClient mGoogleApiClient;
    private static final int SIGN_IN_CODE = 9001;
    String Check;
    ImageView loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        viewPager = (ViewPager) findViewById(R.id.viewpager);
        loc = (ImageView) findViewById(R.id.loc);
        setupViewPager(viewPager);





        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        findViewById(R.id.sign_out_button).setOnClickListener(MainActivity.this);



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        Intent intent = getIntent();
        String nameSurname = intent.getStringExtra("displayname");
        String imageUrl = intent.getStringExtra("imageurl");
        Check=intent.getStringExtra("check");



        TextView displayName = (TextView)findViewById(R.id.nameSurnameText);
        TextView gname = (TextView)findViewById(R.id.gname);
        gname.setText("" + nameSurname);
        displayName.setText("" + nameSurname);

        new DownloadImageTask((RoundedImageView) findViewById(R.id.imageView)).execute(imageUrl);


        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this,Location_act.class);
                startActivity(intent1);
            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.sign_out_button:
                signOut();
                break;

        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                        if (Check.equals("fb"))
                        {
                            LoginManager.getInstance().logOut();
                            finish();
                        }
                        else
                        {
                            Intent login = new Intent(MainActivity.this, Login_act.class);
                            startActivity(login);
                            finish();
                        }


                    }
                });
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, SIGN_IN_CODE);
    }

    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        RoundedImageView bmImage;

        public DownloadImageTask(RoundedImageView mImage) {
            this.bmImage = mImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap mBitmap = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                mBitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mBitmap;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Videofragment(), "Videos");
        adapter.addFragment(new Productfragment(), "Products");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}