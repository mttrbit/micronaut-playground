package hello.ciot.core.services;

import io.micronaut.http.HttpRequest;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class TraceService {

    private static final Logger LOG = LoggerFactory.getLogger(TraceService.class);

    public Flowable<Boolean> trace(HttpRequest<?> request) {
        return Flowable.fromCallable(() -> {
            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("Tracing request: %s %s", request.getMethod(), request.getUri()));
            }
            // trace logic here, potentially performing I/O
            return true;
        }).subscribeOn(Schedulers.io());
    }

}
