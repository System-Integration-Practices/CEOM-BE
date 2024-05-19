package com.example.ceom.service.auth;

import com.example.ceom.components.JwtTokenUtils;
import com.example.ceom.dto.UserDTO;
import com.example.ceom.exceptions.DataNotFoundException;
import com.example.ceom.exceptions.PermissionDenyException;
import com.example.ceom.model.auth.Role;
import com.example.ceom.model.auth.User;
import com.example.ceom.repository.auth.RoleRepository;
import com.example.ceom.repository.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    @Override
    @Transactional
    public User createUser(UserDTO userDTO) throws Exception {
        //register user
        String userName = userDTO.getUserName();
        // Kiểm tra xem số điện thoại đã tồn tại hay chưa
        if(userRepository.existsByUserName(userName)) {
            throw new DataIntegrityViolationException("Username already exists");
        }
        Role role =roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Role does not exist"));
        if(role.getName().toUpperCase().equals(Role.ADMIN)) {
            throw new PermissionDenyException("You cannot register an admin account");
        }
        //convert from userDTO => user
        User newUser = User.builder()
                .fullName(userDTO.getFullName())
                .userName(userDTO.getUserName())
                .password(userDTO.getPassword())
                .active(true)
                .build();

        newUser.setRole(role);

        String password = userDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encodedPassword);
        return userRepository.save(newUser);
    }

    @Override
    public String login(
            String userName,
            String password,
            Long roleId
    ) throws Exception {
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if(optionalUser.isEmpty()) {
            throw new DataNotFoundException("Wrong username or password");
        }
        //return optionalUser.get();//muốn trả JWT token ?
        User existingUser = optionalUser.get();
        //check password
        if(!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new BadCredentialsException("Password does not match");
        }
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if(optionalRole.isEmpty() || !roleId.equals(existingUser.getRole().getId())) {
            throw new DataNotFoundException("Role does not exist");
        }
        if(!optionalUser.get().isActive()) {
            throw new DataNotFoundException("User is locked");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userName, password,
                existingUser.getAuthorities()
        );

        //authenticate with Java Spring security
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(existingUser);
    }

    @Override
    public User getUserDetailsFromToken(String token) throws Exception {
        if(jwtTokenUtil.isTokenExpired(token)) {
            throw new Exception("Token is expired");
        }
        String userName = jwtTokenUtil.extractUserName(token);
        Optional<User> user = userRepository.findByUserName(userName);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("User not found");
        }
    }
}








