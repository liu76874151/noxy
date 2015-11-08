package com.spinn3r.noxy.discovery.support.init;

import com.google.inject.Inject;
import com.spinn3r.artemis.init.BaseService;
import com.spinn3r.artemis.init.Config;
import com.spinn3r.noxy.discovery.fixed.init.FixedDiscoveryService;
import com.spinn3r.noxy.discovery.zookeeper.init.ZKDiscoveryService;

/**
 *
 */
@Config( path = "discovery-listener-support.conf",
         required = true,
         implementation = DiscoveryListenerSupportConfig.class )
public class DiscoveryListenerSupportService extends BaseService {

    private final DiscoveryListenerSupportConfig discoveryListenerSupportConfig;

    @Inject
    DiscoveryListenerSupportService(DiscoveryListenerSupportConfig discoveryListenerSupportConfig) {
        this.discoveryListenerSupportConfig = discoveryListenerSupportConfig;
    }

    @Override
    public void init() {

        switch ( discoveryListenerSupportConfig.getProvider() ) {

            case ZOOKEEPER:
                include( ZKDiscoveryService.class );
                break;

            case FIXED:
                include( FixedDiscoveryService.class );

            default:
                throw new RuntimeException( "Unknown provider: " + discoveryListenerSupportConfig.getProvider() );

        }

    }

}
