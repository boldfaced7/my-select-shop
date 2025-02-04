package com.sparta.myselectshop.product.adapter.out.external;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.sparta.myselectshop.product.domain.Product.*;

@Slf4j(topic = "NAVER API")
@Component
@RequiredArgsConstructor
public class NaverSearchApiClient implements SearchLowestPriceByTitleClient {

    private final RestTemplate restTemplate;

    public Optional<LowestPrice> searchLowestPriceByTitle(String title) {
        var uri = generateUri(title);
        var request = generateRequest(uri);
        var response = executeRequest(request);
        return readFirstLowestPrice(response.getBody());
    }

    private URI generateUri(String query) {
        var uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/shop.json")
                .queryParam("dispaly", 15)
                .queryParam("query", query)
                .encode()
                .build()
                .toUri();
        log.info("uri = " + uri);
        return uri;
    }

    private RequestEntity<Void> generateRequest(URI uri) {
        return RequestEntity.get(uri)
                .header("X-Naver-Client-Id", "{Client-Id}")
                .header("X-Naver-Client-Secret", "{Client-Secret}")
                .build();
    }

    private ResponseEntity<String> executeRequest(RequestEntity<Void> request) {
        var response = restTemplate.exchange(request, String.class);
        log.info("NAVER API Status Code : " + response.getStatusCode());
        return response;
    }

    private Optional<LowestPrice> readFirstLowestPrice(String json) {
        JSONObject jsonObject = new JSONObject(json);
        List<Object> items = jsonObject.getJSONArray("items").toList();

        return items.stream()
                .findFirst()
                .map(item -> ((JSONObject) item).getInt("lprice"))
                .map(LowestPrice::new);
    }
}
