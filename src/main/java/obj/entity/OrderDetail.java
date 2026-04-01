/*
 * @ (#) OrderDetail.java       1.0     3/31/2026
 *
 * Copyright (c) 2026 .
 * IUH.
 * All rights reserved.
 */
package obj.entity;

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
public class OrderDetail {
    private Order order ;
    private Product product ;

    private int quantity;
    private double unitPrice;
    private double discount;
}
