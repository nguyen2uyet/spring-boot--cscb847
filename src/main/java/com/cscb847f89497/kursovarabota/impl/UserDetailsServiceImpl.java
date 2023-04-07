package com.cscb847f89497.kursovarabota.impl;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cscb847f89497.kursovarabota.dto.UserDTO;
import com.cscb847f89497.kursovarabota.entity.Role;
import com.cscb847f89497.kursovarabota.entity.User;
import com.cscb847f89497.kursovarabota.exception.UserAlreadyExistException;
import com.cscb847f89497.kursovarabota.repository.RoleRepository;
import com.cscb847f89497.kursovarabota.repository.StudentRepository;
import com.cscb847f89497.kursovarabota.repository.UserRepository;
import com.cscb847f89497.kursovarabota.security.MyUserDetails;
import com.cscb847f89497.kursovarabota.utils.Utils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }

    public void register(UserDTO userDTO) throws UserAlreadyExistException {

        // Let's check if user already registered with us
        if (checkIfUserExist(userDTO.getUsername())) {
            throw new UserAlreadyExistException("User already exists for this email");
        }
        User user = new User();
        ModelMapper mapper = new ModelMapper();
        user = mapper.map(userDTO, User.class);
        encodePassword(user, userDTO);
        user.setEnabled(true);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findOneByName("STUDENT"));
        user.setRoles(roles);
        studentRepository.save(Utils.mapUserDtoToStudent(userDTO));
        userRepository.save(user);
    }

    public boolean checkIfUserExist(String username) {
        return userRepository.findByUsername(username) != null ? true : false;
    }

    private void encodePassword(User userEntity, UserDTO user) {
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    }

}
