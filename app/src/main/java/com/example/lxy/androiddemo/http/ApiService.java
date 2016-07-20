package com.example.lxy.androiddemo.http;

import com.example.lxy.androiddemo.entity.Repo;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by lxy on 2016/7/10.
 * https://api.github.com/users/JakeWharton/repos
 */
public interface ApiService {

    String BASE_URL = "https://api.github.com/";

    /**
     * ==========================get==========================
     */

    @GET("users/{user}/repos")
    Call<List<Repo>> getRepos(@Path("user") String user);

    @GET("/")
    Call<List<String>> getPages(@QueryMap Map<String,String> params);

    @GET("/{url}")
    Call<List<String>> search(@Path("url") String url,@QueryMap Map<String,String> params);

    /**
     * ==========================put==========================
     */

    @FormUrlEncoded
    @PUT("/")
    Call<String> getString(@Field("params") String params);

    @FormUrlEncoded
    @PUT("/")
    Call<String> getStrings(@FieldMap Map<String,String> params);

    /**
     * ==========================get==========================
     * ==========================RxJava==========================
     */

    @GET("users/{user}/repos")
    Observable<List<Repo>> getReposByRxJava(@Path("user") String user);
}
