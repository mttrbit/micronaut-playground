package hello.ciot.adapters.exceptions;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;
import java.util.NoSuchElementException;

@Produces
@Singleton
@Requires(classes = {NoSuchElementException.class, ExceptionHandler.class})
public class NoSuchElementExceptionHandler implements ExceptionHandler<NoSuchElementException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, NoSuchElementException exception) {
        return HttpResponse.notFound();
    }
}
