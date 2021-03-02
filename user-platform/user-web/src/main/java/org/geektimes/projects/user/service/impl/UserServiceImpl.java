package org.geektimes.projects.user.service.impl;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.repository.UserRepository;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.sql.DBConnectionManager;

import java.sql.SQLException;

/**
 * @author zshuo
 * @date 2021/3/3
 **/
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new DatabaseUserRepository(new DBConnectionManager());
    }

    @Override
    public boolean register(User user) throws SQLException {

        boolean b = userRepository.save(user);
        System.out.println(b);
        for (User user1 : userRepository.getAll()) {
            System.out.println(user1);
        }
        return b;
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;
    }
}
