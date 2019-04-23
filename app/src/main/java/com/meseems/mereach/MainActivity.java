package com.meseems.mereach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.meseems.mereach.networking.ReachabilityServiceDummyImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView dummyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dummyTextView = findViewById(R.id.hello_world_textview);

        new ReachabilityServiceDummyImpl()
                /*
                Reachable domains: www.google.com.br, www.meseems.com.br
                Unreachable domains: www.meseems-not-reachable.com.br
                 */
                .isReachable("www.google.com.br")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isReachable) throws Exception {
                        if(isReachable) {
                            dummyTextView.setText("Is reachable!");
                        } else {
                            dummyTextView.setText("Not reachable... :(");
                        }
                    }
                });
    }
}
