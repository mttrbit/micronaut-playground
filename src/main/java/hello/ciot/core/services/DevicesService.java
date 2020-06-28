package hello.ciot.core.services;

import edu.umd.cs.findbugs.annotations.NonNull;
import hello.ciot.adapters.persistence.DeviceRepository;
import hello.ciot.core.domain.Device;
import io.reactivex.Flowable;
import io.reactivex.Single;

import javax.inject.Singleton;
import javax.validation.constraints.NotEmpty;

@Singleton
public class DevicesService {

    private final DeviceRepository deviceRepository;

    public DevicesService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Single<Device> findByName(@NonNull @NotEmpty String name) {
        return deviceRepository.findOneByName(name);
    }

    public Flowable<Device> findAll() {
        return deviceRepository.findAll();
    }

    public Single<Device> save(@NonNull @NotEmpty String name, @NonNull @NotEmpty String hardwareUuid) {
        return deviceRepository.save(name, hardwareUuid);
    }

    public Single<Device> delete(@NonNull @NotEmpty String name) {
        return deviceRepository.delete(name);
    }
}
