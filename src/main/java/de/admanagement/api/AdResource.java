package de.admanagement.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("ads")
public interface AdResource {
    @GetMapping(value = "{id}")
    ResponseEntity<Ad> get(@PathVariable("id") @NotNull Long adId);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Long createAd(@RequestBody @Valid Ad ad);

    @GetMapping
    List<Ad> list();
}
