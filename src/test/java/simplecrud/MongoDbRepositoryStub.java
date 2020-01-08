package simplecrud;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import io.micronaut.context.annotation.Primary;
import io.reactivex.Maybe;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Primary
public class MongoDbRepositoryStub implements IMongoDbRepository {

    private ArrayList<User> users;

    @Override
    public Single<User> save(User user) {
        resetMockData();

        users.add(user);
        return Single.just(user);
    }

    @Override
    public Single<List<User>> getAll() {
        resetMockData();

        return Single.just(users);
    }

    @Override
    public Maybe<User> getById(int id) {
        resetMockData();

        for (User user : users) {
            if (user.getIdNum() == id)
                return Maybe.just(user);
        }
        return Maybe.just(null);
    }

    @Override
    public Single<UpdateResult> updateById(int id, User user) {
        resetMockData();

        for (User u : users){
            if (u.getIdNum() == id){
                users.set(users.indexOf(u), user);
                return Single.just(UpdateResult.acknowledged(1, 1L, null));
            }
        }
        return Single.just(UpdateResult.acknowledged(0, 0L, null));
    }

    @Override
    public Single<DeleteResult> deleteById(int id) {
        resetMockData();

        for (User user : users){
            if (user.getIdNum() == id){
                users.remove(user);
                return Single.just(DeleteResult.acknowledged(1));
            }
        }
        return Single.just(DeleteResult.acknowledged(0));
    }

    // resets the mock users for testing purposes
    private void resetMockData(){
        users = new ArrayList<User>()
        {
            {
                add(new User("alpha", 1));
                add(new User("beta", 2));
                add(new User("charlie", 3));
            }
        };
    }
}
