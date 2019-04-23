package com.meseems.mereach.networking;

import io.reactivex.Single;

/**
 * Created by nickmm on 8/23/16.
 */
public interface ReachabilityService {
    Single<Boolean> isReachable(String serverUrl);
}
