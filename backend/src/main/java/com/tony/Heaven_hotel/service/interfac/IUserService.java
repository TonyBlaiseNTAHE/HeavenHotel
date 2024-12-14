package com.tony.Heaven_hotel.service.interfac;

import com.tony.Heaven_hotel.dto.LoginRequest;
import com.tony.Heaven_hotel.dto.Response;
import com.tony.Heaven_hotel.models.User;

public interface IUserService {

    Response register(User user);
    Response login(LoginRequest loginRequest);
    Response getAllUsers();
    Response getUSerBookingHistory(String userId);
    Response deleteUser(String userId);
    Response getUserById(String userId);
    Response getMyInfo(String email);
}
