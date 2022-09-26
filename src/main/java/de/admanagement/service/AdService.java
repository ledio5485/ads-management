package de.admanagement.service;

import java.util.List;
import java.util.Optional;

import de.admanagement.api.Ad;
import de.admanagement.persistence.AdRepository;
import de.admanagement.persistence.AdEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdService {

    private final AdRepository adRepository;
    private final AdConverter adConverter;

    @Transactional
    public Ad create(Ad adData) {
        AdEntity adEntity = adRepository.save(adConverter.inbound(adData));
        return adConverter.outbound(adEntity);
    }

    public Optional<Ad> get(Long adId) {
        return adConverter.outbound(adRepository.findById(adId));
    }

    public List<Ad> list() {
        return adConverter.outbound(adRepository.findAll());
    }
}
