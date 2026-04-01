/*
 * @ (#) Product.java       1.0     3/31/2026
 *
 * Copyright (c) 2026 .
 * IUH.
 * All rights reserved.
 */
package obj.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * @description:
 * @outhor: Khang
 * @date:   3/31/2026
 *version:  1.0
 */
@lombok.AllArgsConstructor
@lombok.ToString
@lombok.Setter
@lombok.Getter
@lombok.Builder
@lombok.NoArgsConstructor
public class Product {
    @JsonProperty("product_id")
    private String productID;

    @JsonProperty("product_name")
    private String productName;
    private String unit;
    private double unitPrice;
    private int unitsInStock;
    private Supplier supplier;
}
