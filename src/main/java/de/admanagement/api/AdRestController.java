package de.admanagement.api;

import java.util.List;
import de.admanagement.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdRestController implements AdResource {

    private final AdService adService;

    @Override
    public ResponseEntity<Ad> get(Long adId) {
        return ResponseEntity.of(adService.get(adId));
    }

    @Override
    public Long createAd(Ad ad) {
        return adService.create(ad).getId();
    }

    @Override
    public List<Ad> list() {
        return adService.list();
    }
}
