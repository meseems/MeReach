package com.meseems.mereach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.meseems.mereach.networking.ReachabilityServiceDummyImpl;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView dummyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dummyTextView = (TextView) findViewById(R.id.hello_world_textview);

        new ReachabilityServiceDummyImpl()
                /*
                Reachable domains: www.google.com.br, www.meseems.com.br
                Unreachable domains: www.meseems-not-reachable.com.br
                 */
                .isReachable("www.google.com.br")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {

                    @Override
                    public void call(Boolean isReachable) {
                        if(isReachable) {
                            dummyTextView.setText("Is reachable!");
                        } else {
                            dummyTextView.setText("Not reachable... :(");
                        }
                    }
                });
    }
}
