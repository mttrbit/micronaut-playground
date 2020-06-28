package hello.ciot.adapters.web;

import hello.ciot.core.domain.Device;
import hello.ciot.core.services.DevicesService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/devices")
public class DevicesController {

    private final DevicesService devicesService;

    public DevicesController(DevicesService devicesService) {
        this.devicesService = devicesService;
    }

    @Get(value = "/{name}", produces = MediaType.APPLICATION_JSON)
    @ExecuteOn(TaskExecutors.IO)
    public Single<Device> byName(String name) {
        return devicesService.findByName(name);
    }

    @Post(produces = MediaType.APPLICATION_JSON)
    public Single<HttpResponse<Device>> save(String name, String hardwareUuid) {
        return devicesService
                .save(name, hardwareUuid)
                .map(HttpResponse::created);
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    @ExecuteOn(TaskExecutors.IO)
    public Flowable<Device> index() {
        return devicesService.findAll();
    }

    @Delete(value = "/{name}")
    public Single<HttpResponse<Device>> delete(String name) {
        return devicesService.delete(name).map(__ -> HttpResponse.noContent());
    }
}