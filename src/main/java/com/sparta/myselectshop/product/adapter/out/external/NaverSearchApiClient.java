package com.sparta.myselectshop.product.adapter.out.external;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "NAVER API")
@Component
@RequiredArgsConstructor
public class NaverSearchApiClient {

    private final RestTemplate restTemplate;

    @Value("${openapi.naver.client-id}")
    private String clientId;
    @Value("${openapi.naver.client-secret}")
    private String clientSecret;

    public static List<Item> fromJsonToItems(String responseStr) {
        JSONArray items = new JSONObject(responseStr).getJSONArray("items");
        List<Item> itemList = new ArrayList<>();
        for (Object o : items) {
            itemList.add(new Item((JSONObject) o));
        }
        return itemList;
    }

    public List<Item> searchProductsByTitle(String query) {
        URI uri = getUri(query);
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        log.info("status code : {}", responseEntity.getStatusCode());
        return fromJsonToItems(responseEntity.getBody());
    }

    private URI getUri(String query) {
        return UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/shop.json")
                .queryParam("display", 15)
                .queryParam("query", query)
                .encode()
                .build()
                .toUri();
    }


    public record Item(
            @NotBlank String title,
            @NotBlank String link,
            @NotBlank String image,
            @NotNull int lprice
    ) {
        public Item(JSONObject object) {
            this(
                    object.getString("title"),
                    object.getString("link"),
                    object.getString("image"),
                    object.getInt("lprice")
            );
        }
    }
}
