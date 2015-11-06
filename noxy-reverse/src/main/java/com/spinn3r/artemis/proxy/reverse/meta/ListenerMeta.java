package com.spinn3r.artemis.proxy.reverse.meta;

import com.spinn3r.artemis.proxy.reverse.checks.CheckDaemon;
import com.spinn3r.artemis.proxy.reverse.init.Listener;
import com.spinn3r.artemis.util.misc.ExecutorServices;
import org.littleshoot.proxy.HttpProxyServer;

import java.util.concurrent.ExecutorService;

/**
 *
 */
public class ListenerMeta {

    private Listener listener;

    private OnlineServerMetaIndexProvider onlineServerMetaIndexProvider;

    private CheckDaemon checkDaemon;

    private HttpProxyServer httpProxyServer;

    private ExecutorService executorService;

    public ListenerMeta(Listener listener, OnlineServerMetaIndexProvider onlineServerMetaIndexProvider, CheckDaemon checkDaemon, HttpProxyServer httpProxyServer, ExecutorService executorService) {
        this.listener = listener;
        this.onlineServerMetaIndexProvider = onlineServerMetaIndexProvider;
        this.checkDaemon = checkDaemon;
        this.httpProxyServer = httpProxyServer;
        this.executorService = executorService;
    }

    public Listener getListener() {
        return listener;
    }

    public OnlineServerMetaIndexProvider getOnlineServerMetaIndexProvider() {
        return onlineServerMetaIndexProvider;
    }

    public CheckDaemon getCheckDaemon() {
        return checkDaemon;
    }

    public HttpProxyServer getHttpProxyServer() {
        return httpProxyServer;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

}