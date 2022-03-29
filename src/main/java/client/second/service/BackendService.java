package client.second.service;

import client.second.dto.UserDto;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BackendService {
    //    ENDPOINT
    private static final String REQUESTS_URL = "http://localhost:8080";
    private static final String REQUESTS_LOGIN = "/login?username=qwe&password=123";
    private static final String REQUESTS_ALLUSERS = "/rest/admin/all";

    private final RestTemplate restTemplate;

    public BackendService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
        ;
    }


    public UserDto getRequests() {
        final String urlForLogin = REQUESTS_URL + REQUESTS_LOGIN;
        RestTemplate client = new RestTemplate();
        UserDto userDto = new UserDto();

        ResponseEntity<UserDto> responseEntity = client.exchange(urlForLogin, HttpMethod.POST, null, UserDto.class);

        String set_cookie = responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);


        final String urlForAdmin = REQUESTS_URL + REQUESTS_ALLUSERS;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.COOKIE, set_cookie);
        HttpEntity<HttpHeaders> requestEntity = new HttpEntity<HttpHeaders>(null, headers);

        ResponseEntity<List> responseEntityForAdmin = client.exchange(urlForAdmin, HttpMethod.GET, requestEntity, List.class);
        System.out.println("responseEntityForAdmin " + responseEntityForAdmin.getBody());


        return responseEntity.getBody();
    }

}

