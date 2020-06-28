import hello.ciot.core.domain.Device
import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class DevicesControllerSpec extends Specification {

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    @Shared
    @AutoCleanup
    RxHttpClient rxClient = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())

    void 'test save a document from a POJO'() {
        given:
        Device d = Device.builder()
                .name('GPS Tracker Pro')
                .hardwareUuid("17537524-f6a6-4320-851b-d4089346208e")
                .build()

        when:
        HttpRequest request = HttpRequest.POST('/devices', d).basicAuth("admin", "admin")
        String response = rxClient.toBlocking().retrieve(request)

        then:
        response == '{"name":"GPS Tracker Pro","hardwareUuid":"17537524-f6a6-4320-851b-d4089346208e"}'

        when:
        request = HttpRequest.GET('/devices').basicAuth("admin", "admin")
        List<Device> devices = rxClient.toBlocking().retrieve(request, Argument.listOf(Device))

        then:
        devices.size() == 3
    }
}