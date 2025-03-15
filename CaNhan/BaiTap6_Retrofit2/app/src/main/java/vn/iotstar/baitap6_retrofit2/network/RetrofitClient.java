package vn.iotstar.baitap6_retrofit2.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://app.iotstar.vn:8081/appfoods/";

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

//    public static Retrofit getRetrofit() {
//        if (retrofit == null) {
//            // Tạo OkHttpClient để tắt cache
//            OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                    .addInterceptor(chain -> {
//                        Request request = chain.request().newBuilder()
//                                .addHeader("Cache-Control", "no-cache") // Tắt cache
//                                .build();
//                        return chain.proceed(request);
//                    })
//                    .connectTimeout(30, TimeUnit.SECONDS)
//                    .readTimeout(30, TimeUnit.SECONDS)
//                    .build();
//
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .client(okHttpClient) // Sử dụng OkHttpClient
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }
}
