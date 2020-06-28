package hello.ciot.core.domain;

import io.micronaut.core.annotation.Introspected;
import lombok.Builder;

@Builder
@Introspected
public class Device {
    private String name;
    private String hardwareUuid;

    public Device() {}

    public Device(String name, String hardwareUuid) {
        this.name = name;
        this.hardwareUuid = hardwareUuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHardwareUuid() {
        return hardwareUuid;
    }

    public void setHardwareUuid(String hardwareUuid) {
        this.hardwareUuid = hardwareUuid;
    }

    @Override
    public String toString() {
        return "Device{" +
                "name='" + name + '\'' +
                ", hardwareUuid='" + hardwareUuid + '\'' +
                '}';
    }
}
