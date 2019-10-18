package com.android.popcorn.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


import com.android.popcorn.R;
import com.android.popcorn.databinding.ActivityHomeBinding;
import com.android.popcorn.fragments.MainFragment;
import com.android.popcorn.fragments.MyPickFragment;
import com.android.popcorn.fragments.MyReviewFragment;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding homeBinding;

    // 3개의 메뉴에 들어갈 Fragments
    private MainFragment homeFragment = new MainFragment();
    private MyPickFragment pickFragment = new MyPickFragment();
    private MyReviewFragment reviewFragment = new MyReviewFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        homeBinding.bottomNavigationView.setOnNavigationItemSelectedListener( naviClick );

        setDefaultFragment();

    }//onCreate()

    // bottomNavigationView의 item이 선택될 떄 호출 될 리스너 등록
    BottomNavigationView.OnNavigationItemSelectedListener naviClick = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.menu_home:

//                    transaction.replace(R.id.layout_main, homeFragment).commitAllowingStateLoss();
                    replaceFragment(homeFragment);

                    break;

                case R.id.menu_pick:
//                    transaction.replace(R.id.layout_main, pickFragment).commitAllowingStateLoss();
                    replaceFragment(pickFragment);
                    break;

                case R.id.menu_review:
//                    transaction.replace(R.id.layout_main, reviewFragment).commitAllowingStateLoss();
                    replaceFragment(reviewFragment);
                    break;


            }

            return true;
        }
    };//naviClick()

    // Fragment 고정된 초기 화면
    public void setDefaultFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.layout_main, homeFragment);
        transaction.commit();
    }

    // Fragment 화면 전환
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_main, fragment).commit();
    }

    //앱 권한 설정 감지자자
    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            //모든 권한의 수락이 완료된 경우 호출
            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:010-111-2222"));
            if (ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(i);
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {

        }

    };


    public void setPermission(){

        TedPermission.with(this )
                .setPermissionListener( permissionListener )
                .setPermissions( Manifest.permission.CALL_PHONE).check();//넣어야 하는 권한 전부 넣기 인터넷 제외

    }

}
