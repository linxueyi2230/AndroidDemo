package com.example.lxy.androiddemo.http.retrofit;


import android.util.Log;

import com.e.library.utils.EGsonUtils;
import com.example.lxy.androiddemo.activity.base.DemoApp;
import com.example.lxy.androiddemo.http.ApiService;
import com.example.lxy.androiddemo.http.Response;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by lxy on 2016/7/10.
 */
public class RetrofitHttpClient {

    private static volatile RetrofitHttpClient instance;
    private Retrofit retrofit;
    private ApiService service;

    public static RetrofitHttpClient getIns(){
        if (instance == null){
            synchronized (RetrofitHttpClient.class){
                if (instance == null){
                    instance = new RetrofitHttpClient();
                }
            }
        }

        return instance;
    }

    private RetrofitHttpClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("RetrofitHttpClient",message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        File file = new File(DemoApp.getContext().getCacheDir(),"cache");
        Cache cache = new Cache(file,1024*1024*5);//5mb

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(EGsonUtils.getGson()))
                .addConverterFactory(new ResponseFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .validateEagerly(true)
                .client(client)
                .build();
    }


    public <T> T createService(Class<T> clz) {
        return retrofit.create(clz);
    }

    public ApiService getService(){
        if (service == null){
            service = createService(ApiService.class);
        }
        return service;
    }

    public static class ResponseFactory extends Converter.Factory{
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            return new ReponseConverter();
        }
    }

    public static class ReponseConverter implements Converter<ResponseBody,Response>{
        @Override
        public Response convert(ResponseBody value) throws IOException {
            return new Response(value.string());
        }
    }
}
