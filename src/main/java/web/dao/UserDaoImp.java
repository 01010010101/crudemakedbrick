package web.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private final EntityManager entityManager;
    @Autowired
    public UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<User> getAllUsers() {
        String jpql = "SELECT c FROM User c";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        return query.getResultList();
    }
    @Override
    public void deleteUserById(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
    @Override
    public void saveNewUser(User user) {
        entityManager.persist(user);
    }
    @Override
    public void updateUserById(int id, User updatedUser) {
        entityManager.merge(updatedUser);
    }
    @Override
    public User getUserById(int id) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}

