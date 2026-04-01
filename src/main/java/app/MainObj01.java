/*
 * @ (#) MainObj01.java       1.0     4/1/2026
 *
 * Copyright (c) 2026 .
 * IUH.
 * All rights reserved.
 */
package app;

import bus.BUS;
import dao.DAO;
import db.ConnectDB;
import map.Mapper;
import obj.entity.Product;
import obj.entity.Supplier;

import java.util.List;

/*
 * @description:
 * @outhor: Khang
 * @date:   4/1/2026
 *version:  1.0
 */
public class MainObj01 {
    public static void main(String[] args) {
        System.out.println("Đang kết nối đến Neo4j...");
        String dbName = "khang23646131";
        String userName = "neo4j";
        String pw = "0932897705";
        String url = "neo4j://localhost:7687";

        ConnectDB connectDB = new ConnectDB(url,userName,pw,dbName);
        Mapper mapper = new Mapper();

        DAO dao = new DAO(mapper,connectDB);
        BUS bus = new BUS(dao,mapper);

        System.out.println("C1:");
        List<Product> products = dao.listProductsBySupplier("Specialty Biscuits Ltd.",1,1);
        System.out.println(products.size());
        for (Product product: products){
            System.out.println(product.toString());
        }

        System.out.println("C2:");
        boolean supplier = dao.updateSupplier(new Supplier("S005","Khang","Lake","VN"));
        System.out.println(supplier);

        System.out.println("C3:");
        double tongTien = bus.calculateTotalOrder("O006");
        System.out.println(tongTien);

    }
}
