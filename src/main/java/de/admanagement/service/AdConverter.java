package de.admanagement.service;

import de.admanagement.api.Ad;
import de.admanagement.persistence.Category;
import de.admanagement.persistence.CustomerRepository;
import de.admanagement.persistence.AdEntity;
import de.admanagement.persistence.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdConverter implements Converter<Ad, AdEntity> {
    private final CustomerRepository customerRepository;

    @Override
    public Ad outbound(AdEntity adEntity) {
        Ad adData = new Ad();
        adData.setId(adEntity.getId());
        adData.setCategory(de.admanagement.api.Category.valueOf(adEntity.getCategory().name()));
        adData.setMake(adEntity.getMake());
        adData.setModel(adEntity.getModel());
        adData.setDescription(adEntity.getDescription());
        adData.setPrice(adEntity.getPrice());
        adData.setCustomerId(adEntity.getCustomerEntity().getId());

        return adData;
    }

    @Override
    public AdEntity inbound(Ad adData) {
        AdEntity adEntity = new AdEntity();
        adEntity.setId(adData.getId());
        adEntity.setCategory(Category.valueOf(adData.getCategory().name()));
        adEntity.setMake(adData.getMake());
        adEntity.setModel(adData.getModel());
        adEntity.setDescription(adData.getDescription());
        adEntity.setPrice(adData.getPrice());
        CustomerEntity customerEntity = customerRepository.findById(adData.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found."));
        adEntity.setCustomerEntity(customerEntity);

        return adEntity;
    }
}
