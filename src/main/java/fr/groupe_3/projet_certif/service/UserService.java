package fr.groupe_3.projet_certif.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.groupe_3.projet_certif.dao.UserRepository;
import fr.groupe_3.projet_certif.entity.User;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();

    }

    public Optional<User> getUserByUserName(String userName) {

        return userRepository.findByUserName(userName);

    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }

    public void updateUser(String userName, User putUser) {
        userRepository.save(putUser);
    }

    public void patchUser(String userName, User patchUser) {
        Optional<User> optional = userRepository.findByUserName(userName);

        if (optional.isPresent()) {
            User userToPatch = optional.get();
            userToPatch.updateNotNull(patchUser);
            System.out.println(userToPatch);
            userRepository.save(userToPatch);
        }

    }

}
