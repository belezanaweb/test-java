package br.com.blz.testjava.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class WarehouseResponseDTO {
    private String locality;
    private Integer quantity;
    private String type;
}
