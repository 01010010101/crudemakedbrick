package web.dao;
import web.model.User;
import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    void deleteUserById(int id);
    void saveNewUser(User user);
    void updateUserById(int id, User updatedUser);
    User getUserById(int id);
}

