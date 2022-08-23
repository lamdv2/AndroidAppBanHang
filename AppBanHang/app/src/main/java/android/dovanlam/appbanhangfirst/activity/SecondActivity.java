package android.dovanlam.appbanhangfirst.activity;

import android.content.Context;
import android.dovanlam.appbanhangfirst.R;
import android.dovanlam.appbanhangfirst.Retrofit.ApiBanHang;
import android.dovanlam.appbanhangfirst.Retrofit.RetrofitClient;
import android.dovanlam.appbanhangfirst.adapter.LoaiSpAdapter;
import android.dovanlam.appbanhangfirst.model.LoaiSp;
import android.dovanlam.appbanhangfirst.utils.Utils;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SecondActivity extends AppCompatActivity {
    //home
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listView_Second;
    DrawerLayout drawerLayout;
    LoaiSpAdapter loaiSpAdapter;
    List<LoaiSp> arrayLoaiSp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);

        AnhXa();
        actionBar();
        ActionViewFlipper();

        if(isConnected(this)){
            Toast.makeText(getApplicationContext(), "Good", Toast.LENGTH_LONG).show();
            ActionViewFlipper();
            getLoaiSanPham();
        }else{
            Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
        }
    }

    private void getLoaiSanPham() {

        compositeDisposable.add(apiBanHang.getLoaiSp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSpModel -> {
                            if(loaiSpModel.isSuccess()){
                                arrayLoaiSp = loaiSpModel.getResutl();
//                                Toast.makeText(getApplicationContext(), loaiSpModel.getResutl().get(1).getNameSp(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ));

    }

    private void ActionViewFlipper(){
        List<String> slider = new ArrayList<>();
        slider.add("https://cdn.tgdd.vn/2022/07/banner/18-w4-720-220-720x220.png");
        slider.add("https://cdn.tgdd.vn/2022/07/banner/18-poco40-720-220-720x220.png");
        slider.add("https://cdn.tgdd.vn/2022/06/banner/720-220-720x220-199.png");
        slider.add("https://cdn.tgdd.vn/2022/07/banner/720-220-720x220-33.png");

        for(int i = 0; i< slider.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(slider.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);

        Animation slider_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slider_in_right);
        Animation slider_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slider_out_right);
        viewFlipper.setInAnimation(slider_in);
        viewFlipper.setOutAnimation(slider_out);

    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarHome);
        viewFlipper = findViewById(R.id.viewflipperHome);
        recyclerView = findViewById(R.id.recyclerViewHome);
        navigationView = findViewById(R.id.navigationViewHome);
        listView_Second = findViewById(R.id.listviewHome);
        drawerLayout = findViewById(R.id.drawerlayoutHome);

        // khoi tao List
        arrayLoaiSp = new ArrayList<>();

        // khoi tao Adapter
        loaiSpAdapter = new LoaiSpAdapter(arrayLoaiSp, getApplicationContext());
        listView_Second.setAdapter(loaiSpAdapter);
    }

    private boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected())){
            return true;
        }
        else{
            return false;
        }
    }

    private void actionBar(){

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

}
