package hello.ciot.adapters.persistence;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import hello.ciot.core.domain.Device;
import io.reactivex.Flowable;
import io.reactivex.Single;

import javax.inject.Singleton;

@Singleton
public class DeviceRepository {

    private final MongoClient mongoClient;

    public DeviceRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public Single<Device> save(String name, String hardwareUuid) {
        Device device = Device.builder().name(name).hardwareUuid(hardwareUuid).build();
        return Single
                .fromPublisher(getCollection().insertOne(device))
                .map(success -> device);
    }

    public Single<Device> delete(String name) {
        return Single.fromPublisher(getCollection()
                .findOneAndDelete(Filters.eq("name", name)));
    }

    public MongoCollection<Device> getCollection() {
        return mongoClient
                .getDatabase("micronaut")
                .getCollection("devices", Device.class);
    }

    public Single<Device> findOneByName(String name) {
        return Single.fromPublisher(getCollection()
                .find(Filters.eq("name", name))
                .limit(1));
    }

    public Flowable<Device> findAll() {
        return Flowable.fromPublisher(getCollection().find());
    }
}
