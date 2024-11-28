package com.rest.rest_example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(description = "Product information!")
public class ProductDto {
 @Schema(description = "id must be unique!")
 private Integer id;
 @Schema(description = "Name")
 private String name;
 @Schema(description = "price")
 private Integer price;
}
