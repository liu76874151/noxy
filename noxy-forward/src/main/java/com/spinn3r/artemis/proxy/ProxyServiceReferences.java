package com.spinn3r.artemis.proxy;

import com.spinn3r.artemis.http.init.DefaultWebserverReferencesService;
import com.spinn3r.artemis.http.init.WebserverService;
import com.spinn3r.artemis.init.ServiceReferences;
import com.spinn3r.artemis.init.services.HostnameService;
import com.spinn3r.artemis.init.services.PropertiesService;
import com.spinn3r.artemis.init.services.VersionService;
import com.spinn3r.artemis.logging.init.LoggingService;
import com.spinn3r.artemis.metrics.init.GlobalMetricsService;
import com.spinn3r.artemis.metrics.init.MetricsService;
import com.spinn3r.artemis.proxy.init.ProxyService;
//import com.spinn3r.artemis.sequence.zookeeper.init.ZKGlobalMutexService;
import com.spinn3r.artemis.sequence.none.init.NoGlobalMutexService;
import com.spinn3r.artemis.time.init.SystemClockService;
import com.spinn3r.artemis.time.init.UptimeService;

/**
 *
 */
public class ProxyServiceReferences extends ServiceReferences {

    public ProxyServiceReferences() {

        add( SystemClockService.class );
        add( HostnameService.class );
        add( VersionService.class );
        add( LoggingService.class );
        add( NoGlobalMutexService.class );
        add( GlobalMetricsService.class );
        add( UptimeService.class );
        add( DefaultWebserverReferencesService.class );
        add( ProxyService.class );
        add( WebserverService.class );

    }

}