package com.example.lxy.androiddemo.mvp.presenter;

import com.example.lxy.androiddemo.http.ApiService;
import com.example.lxy.androiddemo.http.retrofit.RetrofitHttpClient;
import com.example.lxy.androiddemo.mvp.view.ELceView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lxy on 2016/7/25 22:59.
 */
public abstract class ELcePresenter<V extends ELceView<M>,M> extends MvpBasePresenter<V> {

    protected ApiService service;
    protected Subscriber<M> subscriber;

    public ELcePresenter() {
        service = RetrofitHttpClient.getIns().getService();
    }

    protected void unsubscribe() {
        if (subscriber != null && !subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }

        subscriber = null;
    }

    public void subscribe(Observable<M> observable) {

        if (isViewAttached()) {
            getView().showLoading();
        }

        unsubscribe();

        subscriber = new Subscriber<M>() {

            @Override public void onCompleted() {
                ELcePresenter.this.onCompleted();
            }

            @Override public void onError(Throwable e) {
                ELcePresenter.this.onError(e);
            }

            @Override public void onNext(M m) {
                ELcePresenter.this.onNext(m);
            }
        };

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    protected void onCompleted() {
        if (isViewAttached()) {
            getView().hideLoading();
        }
        unsubscribe();
    }

    protected void onError(Throwable e) {
        if (isViewAttached()) {
            getView().showError(e.getMessage());
            getView().hideLoading();
        }
        unsubscribe();
    }

    protected void onNext(M data) {
        if (isViewAttached()) {
            getView().setData(data);
        }
    }

    @Override public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            unsubscribe();
        }
    }

}
