package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.*;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserServiceImpl userService;

    public CustomOAuth2UserService(UserServiceImpl userService){
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("Here: " + oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if(registrationId.equals("naver")){
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if(registrationId.equals("google")){
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else{
            return null;
        }

        String username = oAuth2Response.getProviderId()+"@"+oAuth2Response.getProvider()+".com";
        System.out.println("username: "+username);
        UserDTO existData = userService.getUserById(username);

        if(existData == null){
            UserDTO user = new UserDTO();
            user.setUserId(username);
            user.setUsername(oAuth2Response.getName());
            user.setPassword("OAuthUser");
            user.setRole("ROLE_USER");

            userService.insertUser(user);

            return new CustomOAuth2User(user);
        }
        else{
            existData.setUsername(oAuth2Response.getName());

            userService.updateUser(existData);

            return new CustomOAuth2User(existData);
        }
    }
}
