package com.spring.notebook.service;

import java.security.SecureRandom;
import java.util.Map;	
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.spring.notebook.entity.PrincipalDetails;
import com.spring.notebook.entity.Roles;
import com.spring.notebook.entity.Users;
import com.spring.notebook.oauth.GoogleUserInfo;
import com.spring.notebook.oauth.KakaoUserInfo;
import com.spring.notebook.oauth.NaverUserInfo;
import com.spring.notebook.oauth.OAuth2UserInfo;
import com.spring.notebook.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info("getAttirbutes : {}", oAuth2User.getAttributes());
		
		OAuth2UserInfo oAuth2UserInfo = null;
		
		String provider = userRequest.getClientRegistration().getRegistrationId();	
		
		if (provider.equals("google")) {
			
			log.info("구글 로그인 요청");
			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
			
		} else if (provider.equals("naver")) {
			
			log.info("네이버 로그인 요청");
			oAuth2UserInfo = new NaverUserInfo( (Map)oAuth2User.getAttributes().get("response"));
			
		} else if (provider.equals("kakao")) {
			
			log.info("카카오 로그인 요청");
			oAuth2UserInfo = new KakaoUserInfo( (Map)oAuth2User.getAttributes());
		}

		String email = oAuth2UserInfo.getEmail();
		
		Optional<Users> optionalUsers = userRepository.findByEmail(email);
		Users users = null;
		
		if (optionalUsers.isEmpty()) {
			
			//식별자 생성
			String shortId = generateShortId();
			
			users = Users.builder()
					.email(email)
					.userUrl(shortId)
					.roles(Roles.USER)
					.build();
			userRepository.save(users);
		} else {
			users = optionalUsers.get();
		}
		
		return new PrincipalDetails(users, oAuth2User.getAttributes());
	}
	
	private String generateShortId() {
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int ID_LENGTH = 8;

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(ID_LENGTH);

        for (int i = 0; i < ID_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
