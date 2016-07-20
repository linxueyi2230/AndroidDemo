package com.example.lxy.androiddemo.activity.rxjava;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lxy.androiddemo.R;
import com.example.lxy.androiddemo.activity.base.BaseSwipeBackActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by lxy on 2016/7/8.
 * @see http://blog.csdn.net/lzyzsd/article/details/44891933
 */
public class RxJavaActivity extends BaseSwipeBackActivity {

    @BindView(R.id.tv_rxjava) TextView tvRxjava;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_rxjava;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitData() {

    }

    @OnClick(R.id.btn_test)
    protected void onRxJavaClick(){
        rxjavaFlatMap();
    }

    private void rxjava1(){
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello world");
            }
        });

        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                tvRxjava.setText(s);
            }
        });
    }

    private void rxjavaJust(){
        Observable<String> observable = Observable.just("Hello RxJava");
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                tvRxjava.setText(s);
            }
        });
    }

    private void rxjavaMap(){
        Observable<String> observable = Observable.just("Hello RxJava").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return String.format("%s map",s);
            }
        });

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                tvRxjava.setText(s);
            }
        });

    }

    private void rxjavaFrom(){
        final Observable<List<String>> observable = Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                List<String> list = new ArrayList<>();
                list.add("Hello");
                list.add("RxJava");
                list.add("From");
                subscriber.onNext(list);
            }
        });

        Subscriber<List<String>> subscriber = new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> strings) {
//                StringBuilder builder = new StringBuilder();
//                for (String s:strings){
//                    builder.append(s).append(" ");
//                }
//                tvRxjava.setText(builder.toString());
                //from代替foreach
                final StringBuilder builder = new StringBuilder();
                Observable.from(strings).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        builder.append(s).append(" ");
                    }
                });

                tvRxjava.setText(builder.toString());
            }
        };

        observable.subscribe(subscriber);
    }

    private void rxjavaFlatMap(){

        final StringBuilder builder = new StringBuilder();

        final Observable<List<String>> observable = Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                List<String> list = new ArrayList<>();
                list.add("Hello");
                list.add("RxJava");
                list.add("FlatMap");
                subscriber.onNext(list);
            }
        });

        observable.flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                builder.append(s).append(" ");
            }
        });

        tvRxjava.setText(builder.toString());

    }

}
