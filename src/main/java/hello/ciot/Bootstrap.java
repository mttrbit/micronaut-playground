package hello.ciot;

import hello.ciot.core.services.DevicesService;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Bootstrap implements ApplicationEventListener<StartupEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

    @Inject
    DevicesService devicesService;

    @Override
    public void onApplicationEvent(StartupEvent event) {
        logEvent(devicesService.save("GPS Tracker", "17537524-f6a6-4320-851b-d4089346208e"));
        logEvent(devicesService.save("Smart Watch", "94fdd22f-6280-4428-bbf3-3c0b2dfd0de2"));
    }

    private void logEvent(Single<?> event) {
        event.subscribe(
                saved -> LOG.info("Saved device {}.", saved),
                throwable -> LOG.error("Error saving device.", throwable)
        );
    }
}
