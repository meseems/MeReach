package com.meseems.mereach.networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by nickmm on 8/23/16.
 */
public class ReachabilityServiceDummyImpl implements ReachabilityService {

    public Observable<Boolean> isReachable(final String serverUrl) {

        // Using Observable.create
        // https://github.com/ReactiveX/RxJava/wiki/Creating-Observables
        return Observable.create(new Observable.OnSubscribe<Boolean>() {

            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                // Method called when subscribed

                // onNext could be called inside an asynchronous method too.
                subscriber.onNext(checkReachability(serverUrl, 1000));
                subscriber.onCompleted();
            }
        });
    }

    private boolean checkReachability(String url, int timeout) {
        // Tip: You could use InetAddress, but is not reliable according to this post:
        // http://stackoverflow.com/questions/9922543/why-does-inetaddress-isreachable-return-false-when-i-can-ping-the-ip-address
        // What about an implementation using sockets?
        // // Using example in: http://stackoverflow.com/questions/9922543/why-does-inetaddress-isreachable-return-false-when-i-can-ping-the-ip-address
        return url.contains("google.com.br") || url.contains("www.meseems.com.br");
    }
}
