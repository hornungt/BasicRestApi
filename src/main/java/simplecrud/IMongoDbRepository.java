package simplecrud;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

public interface IMongoDbRepository {

    Single<User> save(User user);
    Single<List<User>> getAll();
    Maybe<User> getById(int id);
    Single<UpdateResult> updateById(int id, User user);
    Single<DeleteResult> deleteById(int id);
}
