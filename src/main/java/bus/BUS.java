/*
 * @ (#) BUS.java       1.0     4/1/2026
 *
 * Copyright (c) 2026 .
 * IUH.
 * All rights reserved.
 */
package bus;

import dao.DAO;
import map.Mapper;
import obj.entity.Product;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

import java.util.List;
import java.util.Map;

/*
 * @description:
 * @outhor: Khang
 * @date:   4/1/2026
 *version:  1.0
 */
public class BUS {
    DAO dao;
    Mapper mapper;

    public BUS(DAO dao, Mapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public List<Product> listProductsBySupplier(String companyName, int page, int size) {
        if(companyName.isBlank() || companyName.isEmpty()){
            throw new IllegalArgumentException("Khong duoc rong");
        }
        if (page<=0) {
            throw new IllegalArgumentException("page phai so duong");
        }
        if (size<=0) {
            throw new IllegalArgumentException("size phai so duong");
        }
        return dao.listProductsBySupplier(companyName,page,size);
    }


    //tìm theo mã hóa đơn

    //nếu hóa đơn tồn tại thì tìm
    public double calculateTotalOrder(String orderID) {
        if(!dao.kiemTraHoaDon(orderID)){
            return 0.0;
        }
        return dao.calculateTotalOrder(orderID);
    }
}