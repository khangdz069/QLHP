/*
 * @ (#) Order.java       1.0     3/31/2026
 *
 * Copyright (c) 2026 .
 * IUH.
 * All rights reserved.
 */
package obj.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

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

public class Order {
    @JsonProperty("")
    private String orderID;
    private LocalDate orderDate;
    private String customerName;
    private String employeeName;
    private Status status;

}
