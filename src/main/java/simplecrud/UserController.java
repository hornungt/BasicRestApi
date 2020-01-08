package simplecrud;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import io.micronaut.http.annotation.*;
import io.reactivex.Maybe;
import io.reactivex.Single;

import javax.inject.Inject;
import java.util.List;

@Controller("/users")
public class UserController {

    private IMongoDbRepository mongoRepo;

    @Inject
    public UserController(IMongoDbRepository mongoRepo) {
        this.mongoRepo = mongoRepo;
    }

    //C
    @Post
    public Single<User> createUser(@Body User user){
        return mongoRepo.save(user);
    }

    //C (all)
    @Get
    public Single<List<User>> getAllUsers(){
        return mongoRepo.getAll();
    }

    //R
    @Get("/{id}")
    public Maybe<User> getUser(int id){
        return mongoRepo.getById(id);
    }

    //U
    @Put("/{id}")
    public Single<UpdateResult> updateUser(int id, @Body User newUserData){
        return mongoRepo.updateById(id, newUserData);
    }

    //D
    @Delete("/{id}")
    public Single<DeleteResult> deleteUser(int id){
        return mongoRepo.deleteById(id);
    }
}
