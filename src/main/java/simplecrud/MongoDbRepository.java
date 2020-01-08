package simplecrud;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Singleton
public class MongoDbRepository implements IMongoDbRepository {

    private final MongoClient client;

    public MongoDbRepository(MongoClient client){
        this.client = client;
    }

    /**
     * Create
     * @param user the user to add
     * @return Single value containing the one user that is created
     */
    public Single<User> save(User user) {
        return Single.fromPublisher(getUserCollection().insertOne(user)).map(success -> user);
    }

    /**
     * Read (all)
     * @return Single value containing List of all Users
     */
    public Single<List<User>> getAll() {
        return Flowable.fromPublisher(getUserCollection().find()).toList();
    }

    /**
     * Read (one)
     * @param id of the user
     * @return a Maybe that contains the user or a failure if the corresponding id is not found
     */
    public Maybe<User> getById(int id) {
        return Flowable.fromPublisher(getUserCollection().find(eq("idNum", id))).firstElement();
    }

    /**
     * Update
     * @param id of the user
     * @param user new user data to rewrite
     * @return a Single value containing the result of the update
     */
    public Single<UpdateResult> updateById(int id, User user){
        return Single.fromPublisher(getUserCollection().replaceOne(eq("idNum", id), user));
    }

    /**
     * Delete
     * @param id of the user
     * @return a Single value containing the result of the delete
     */
    public Single<DeleteResult> deleteById(int id) {
        return Single.fromPublisher(getUserCollection().deleteOne(eq("idNum", id)));
    }

    private MongoCollection<User> getUserCollection(){
        return client.getDatabase("micronaut-poc").getCollection("users", User.class);
    }
}
