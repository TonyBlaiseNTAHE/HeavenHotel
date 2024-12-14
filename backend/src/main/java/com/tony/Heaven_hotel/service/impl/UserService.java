package com.tony.Heaven_hotel.service.impl;

import com.tony.Heaven_hotel.dto.LoginRequest;
import com.tony.Heaven_hotel.dto.Response;
import com.tony.Heaven_hotel.dto.UserDTO;
import com.tony.Heaven_hotel.models.User;
import com.tony.Heaven_hotel.Exception.OurException;
import com.tony.Heaven_hotel.repository.UserRepository;
import com.tony.Heaven_hotel.service.interfac.IUserService;
import com.tony.Heaven_hotel.utils.JWTUtils;
import com.tony.Heaven_hotel.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public Response register(User user) {
        Response response = new Response();

        try {
            if (user.getRole() == null || user.getRole().isBlank()) {
                user.setRole("USER");
            }
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new OurException(user.getEmail() + " " + "Already Exists");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(savedUser);

            response.setStatusCode(200);
            response.setUser(userDTO);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Saving a User" + e.getMessage());

        }
        return response;
    }

    @Override
    public Response login(LoginRequest loginRequest) {
        Response response = new Response();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new OurException("User Not Found"));
            var token = jwtUtils.generateToken(user);

            response.setToken(token);
            response.setExpirationTime("7 days");
            response.setRole(user.getRole());
            response.setMessage("successful");
            response.setStatusCode(200);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Logging in " + e.getMessage());

        }
        return response;
    }

    @Override
    public Response getAllUsers() {
        Response response = new Response();

        try {
            List<User> userList = userRepository.findAll();
            List<UserDTO> userDTOList = Utils.mapUserListEntityToUserListDTO(userList);

            response.setUserList(userDTOList);
            response.setMessage("successful");
            response.setStatusCode(200);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());

        }
        return response;
    }

    @Override
    public Response getUSerBookingHistory(String userId) {
        Response response = new Response();

        try {
            User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(()-> new OurException("User Not Found"));
            UserDTO userDTO = Utils.mapUserEntityToUserDTOPlusUserBookingsAndRoom(user);

            response.setMessage("successful");
            response.setStatusCode(200);
            response.setUser(userDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting user bookings in " + e.getMessage());

        }
        return response;
    }

    @Override
    public Response deleteUser(String userId) {
        Response response = new Response();

        try {
            userRepository.findById(Long.valueOf(userId)).orElseThrow(()-> new OurException("User Not Found"));
            userRepository.deleteById(Long.valueOf(userId));

            response.setMessage("successful");
            response.setStatusCode(200);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error deleting a user " + e.getMessage());

        }
        return response;
    }

    @Override
    public Response getUserById(String userId) {
        Response response = new Response();

        try {
            User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(()-> new OurException("User Not Found"));
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);

            response.setMessage("successful");
            response.setStatusCode(200);
            response.setUser(userDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting a user by id " + e.getMessage());

        }
        return response;
    }

    @Override
    public Response getMyInfo(String email) {
        Response response = new Response();

        try {
            User user = userRepository.findByEmail(email).orElseThrow(()-> new OurException("User Not Found"));
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);

            response.setMessage("successful");
            response.setStatusCode(200);
            response.setUser(userDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting a user info " + e.getMessage());

        }
        return response;
    }
//    @Override
//    public Response forgotPassword(String email) {
//        Response response = new Response();
//        try {
//            // Check if user exists
//            User user = userRepository.findByEmail(email)
//                    .orElseThrow(() -> new OurException("User with email " + email + " not found"));
//
//            // Generate reset token (UUID)
//            String resetToken = UUID.randomUUID().toString();
//
//            // Save the token to the user's entity or a separate table (choose based on your design)
//            user.setResetToken(resetToken);
//            userRepository.save(user);
//
//            // Send email with reset link
//            String resetLink = "http://localhost:8080/users/reset-password?token=" + resetToken;
//            // Use your email utility to send the email
//            emailService.sendEmail(user.getEmail(), "Password Reset Request",
//                    "Click the link to reset your password: " + resetLink);
//
//            response.setMessage("Password reset link has been sent to your email");
//            response.setStatusCode(200);
//        } catch (OurException e) {
//            response.setStatusCode(404);
//            response.setMessage(e.getMessage());
//        } catch (Exception e) {
//            response.setStatusCode(500);
//            response.setMessage("Error in forgot password process: " + e.getMessage());
//        }
//        return response;
//    }
//    @Override
//    public Response resetPassword(String token, String newPassword) {
//        Response response = new Response();
//        try {
//            // Find user by reset token
//            User user = userRepository.findByResetToken(token)
//                    .orElseThrow(() -> new OurException("Invalid reset token"));
//
//            // Reset the password
//            user.setPassword(passwordEncoder.encode(newPassword));
//            user.setResetToken(null); // Invalidate the token after use
//            userRepository.save(user);
//
//            response.setMessage("Password has been reset successfully");
//            response.setStatusCode(200);
//        } catch (OurException e) {
//            response.setStatusCode(404);
//            response.setMessage(e.getMessage());
//        } catch (Exception e) {
//            response.setStatusCode(500);
//            response.setMessage("Error resetting password: " + e.getMessage());
//        }
//        return response;
//    }


}
