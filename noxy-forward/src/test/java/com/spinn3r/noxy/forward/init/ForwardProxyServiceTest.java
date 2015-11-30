package com.spinn3r.noxy.forward.init;

import com.google.inject.Inject;
import com.spinn3r.artemis.http.init.DebugWebserverReferencesService;
import com.spinn3r.artemis.http.init.DefaultWebserverReferencesService;
import com.spinn3r.artemis.http.init.WebserverService;
import com.spinn3r.artemis.init.BaseLauncherTest;
import com.spinn3r.artemis.init.MockHostnameService;
import com.spinn3r.artemis.init.MockVersionService;
import com.spinn3r.artemis.logging.init.ConsoleLoggingService;
import com.spinn3r.artemis.metrics.init.MetricsService;
import com.spinn3r.artemis.network.builder.DefaultHttpRequestBuilderService;
import com.spinn3r.artemis.network.builder.HttpRequestBuilder;
import com.spinn3r.artemis.network.builder.proxies.Proxies;
import com.spinn3r.artemis.time.init.UptimeService;
import com.spinn3r.noxy.discovery.support.init.MembershipSupportService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.net.Proxy;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

/**
 *
 */
public class ForwardProxyServiceTest extends BaseLauncherTest {

    @Inject
    HttpRequestBuilder httpRequestBuilder;

    @Override
    @Before
    public void setUp() throws Exception {

        super.setUp( MockHostnameService.class,
                     MockVersionService.class,
                     UptimeService.class,
                     MetricsService.class,
                     ConsoleLoggingService.class,
                     DefaultHttpRequestBuilderService.class,
                     MembershipSupportService.class,
                     ForwardProxyService.class,
                     DefaultWebserverReferencesService.class,
                     DebugWebserverReferencesService.class,
                     WebserverService.class );

    }

    @Test
    public void testRequestsWithProxyService() throws Exception {

        Proxy proxy = Proxies.create( String.format( "http://localhost:%s", 8080 ) );
        String contentWithEncoding = httpRequestBuilder.get( "http://cnn.com" ).withProxy( proxy ).execute().getContentWithEncoding();

        assertThat( contentWithEncoding, containsString( "CNN" ) );

    }

    @Test
    public void testRequestsWithProxyServiceUsingSSL() throws Exception {

        Proxy proxy = Proxies.create( String.format( "http://localhost:%s", 8080 ) );
        String contentWithEncoding = httpRequestBuilder.get( "https://www.google.com" ).withProxy( proxy ).execute().getContentWithEncoding();

        assertThat( contentWithEncoding, containsString( "<title>Google</title>" ) );

    }

    @Test
    public void testTestOnSecondaryServer() throws Exception {

        Proxy proxy = Proxies.create( String.format( "http://localhost:%s", 8081 ) );
        String contentWithEncoding = httpRequestBuilder.get( "http://cnn.com" ).withProxy( proxy ).execute().getContentWithEncoding();

        assertThat( contentWithEncoding, containsString( "CNN" ) );

    }

    @Test
    @Ignore
    public void testBulkRequests1() throws Exception {

        Proxy proxy = Proxies.create( String.format( "http://localhost:%s", 8081 ) );

        int nrRequest = 100;

        for (int i = 0; i < nrRequest; i++) {

            String contentWithEncoding = httpRequestBuilder.get( "http://cnn.com" ).withProxy( proxy ).execute().getContentWithEncoding();

            assertThat( contentWithEncoding, containsString( "CNN" ) );

        }

    }

    @Test
    @Ignore
    public void testBulkRequestsWithEcho() throws Exception {

        Proxy proxy = Proxies.create( String.format( "http://localhost:%s", 8081 ) );

        int nrRequest = 5000;

        for (int i = 0; i < nrRequest; i++) {

            String contentWithEncoding = httpRequestBuilder.get( "http://localhost:7000/echo?message=hello" ).withProxy( proxy ).execute().getContentWithEncoding();

            assertEquals( "hello", contentWithEncoding );

        }

    }


    @Test
    public void testHttpHeaders() throws Exception {

        Proxy proxy = Proxies.create( String.format( "http://localhost:%s", 8081 ) );
        String contentWithEncoding = httpRequestBuilder.get( "https://httpbin.org/get" ).withProxy( proxy ).execute().getContentWithEncoding();

        System.out.printf( "%s\n", contentWithEncoding );

    }

}