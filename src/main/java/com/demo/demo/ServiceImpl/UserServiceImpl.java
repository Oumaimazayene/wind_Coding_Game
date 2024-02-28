package com.demo.demo.ServiceImpl;

import com.demo.demo.Repository.UserRepository;
import com.demo.demo.Service.UserService;
import com.demo.demo.dtos.UserDTo;
import com.demo.demo.entity.Role;
import com.demo.demo.entity.Soumet;
import com.demo.demo.entity.User;
import com.demo.demo.mappers.UserMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper ;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserDTo> getAllUser() {
        return userRepository.findAll().stream()
                .map(userMapper::ToUsers)
                .collect(Collectors.toList());
    }
    @Override
    public UserDTo updateUser(Long id, UserDTo userDTo) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userMapper.updateUserFromDTO(userDTo, existingUser.get());
            return userMapper.ToUsers(userRepository.save(existingUser.get()));
        }
        return null;    }

    @Override
    public void softDeleteUser(Long id) {
        try {
            Optional<User> optionalUser = userRepository.findById(id);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setIsdeleted(true);
                userRepository.save(user);
                System.out.println("User with ID: " + id + " soft-deleted successfully");
            } else {
                System.out.println("User with ID " + id + " not found");
                // Handle not found scenario
            }
        } catch (Exception e) {
            System.err.println("Error soft-deleting User");
            throw e;
        }
    }




}
