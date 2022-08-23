package android.dovanlam.appbanhangfirst.Retrofit;

import android.dovanlam.appbanhangfirst.model.LoaiSpModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ApiBanHang {
    @GET("getLoaiSp.php")
    Observable<LoaiSpModel> getLoaiSp();

}
