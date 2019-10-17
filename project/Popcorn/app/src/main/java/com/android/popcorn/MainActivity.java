package com.android.popcorn;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();
    // 4개의 메뉴에 들어갈 Fragment들
//    private Menu1Fragment homeFragment = new Menu1Fragment();
    private Menu2Fragment pickFragment = new Menu2Fragment();
    private Menu3Fragment reviewFragment = new Menu3Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Fragment fragment = new Menu1Fragment();

        Intent i = new Intent();
        final Bundle bundle = i.getExtras();
        fragment.setArguments(bundle);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        // 첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment).commitAllowingStateLoss();



        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.menu_home:

                        transaction.replace(R.id.frame_layout, fragment).commitAllowingStateLoss();

                        break;

                    case R.id.menu_pick:
                        transaction.replace(R.id.frame_layout, pickFragment).commitAllowingStateLoss();
                        break;

                    case R.id.menu_review:
                        transaction.replace(R.id.frame_layout, reviewFragment).commitAllowingStateLoss();
                        break;


                }

                return true;
            }
        });

    }


    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment).commit();
    }

    //앱 권한 설정 감지자자
    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            //모든 권한의 수락이 완료된 경우 호출
            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:010-111-2222"));
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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
