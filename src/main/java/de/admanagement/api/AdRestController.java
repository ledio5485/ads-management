package de.admanagement.api;

import java.util.Collection;

import de.admanagement.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ads")
@RequiredArgsConstructor
public class AdRestController implements Resource<Ad, Long> {

    private final AdService adService;

    @Override
    public ResponseEntity<Ad> findById(Long id) {
        return ResponseEntity.of(adService.get(id));
    }

    @Override
    public ResponseEntity<Ad> create(Ad ad) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adService.create(ad));
    }

    @Override
    public ResponseEntity<Collection<Ad>> findAll() {
        return ResponseEntity.ok(adService.list());
    }
}
