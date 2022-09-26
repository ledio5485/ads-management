package de.admanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface Converter<DTO, Model> {
    Model inbound(DTO dto);

    DTO outbound(Model model);

    default Optional<DTO> outbound(Optional<Model> model) {
        return model.map(this::outbound);
    }

    default List<DTO> outbound(List<Model> models) {
        return models.stream()
                .map(this::outbound)
                .collect(Collectors.toList());
    }
}
