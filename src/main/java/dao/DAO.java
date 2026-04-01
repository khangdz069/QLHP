/*
 * @ (#) DAO.java       1.0     4/1/2026
 *
 * Copyright (c) 2026 .
 * IUH.
 * All rights reserved.
 */
package dao;

import db.ConnectDB;
import map.Mapper;
import obj.entity.Product;
import obj.entity.Supplier;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @description:
 * @outhor: Khang
 * @date:   4/1/2026
 *version:  1.0
 */
public class DAO {
    ConnectDB connectDB;
    Mapper mapper;

    public DAO(Mapper mapper, ConnectDB connectDB) {
        this.mapper = mapper;
        this.connectDB = connectDB;
    }

    //Tìm sản phẩm bằng id
    public Product getProductByID (String id, int page, int size){
        String cypher = "MATCH (p:Product{product_id:$id}) RETURN p";
        Map<String, Object> data = Map.of("id", id);

        try(Session session = connectDB.getSession()){
            return session.executeRead(tx -> {
                Result result = tx.run(cypher,data);
                Node node = result.single().get("p").asNode();
                Product product = mapper.toObject(node.asMap(), Product.class);
                return product;
            });
        }
    }

    //danh sách sản phẩm cung cấp bởi supplier
    public List<Product> listProductsBySupplier (String companyName, int page, int size){
        String cypher = "MATCH (p:Product) <-  [:SUPPLIES] - (s:Supplier{company_name:$company_name}) RETURN p";
        Map<String, Object> data = Map.of("company_name", companyName);

        try(Session session = connectDB.getSession()){
            return session.executeRead(tx -> {
                Result result = tx.run(cypher,data);
                List<Product> products = new ArrayList<>();
                while (result.hasNext()){
                    Record record = result.next();
                    Node node = record.get("p").asNode();
                    Product product = mapper.toObject(node.asMap(), Product.class);
                    products.add(product);
                }
                return products;
            });
        }
    }

    //tìm supplier theo mã

    //sửa supplier nếu có supplier tồn tại
    public boolean updateSupplier (Supplier supplier){
        String cypher = "MATCH (s:Supplier{supplier_id:$id}) SET s.contact_name=$ctName, s.company_name=$cpnName, s.country=$ctr RETURN s";
        Map<String, Object> params = Map.of(
                "id",supplier.getSupplierID(),
                "ctName", supplier.getContactName(),
                "cpnName", supplier.getCompanyName(),
                "ctr", supplier.getCountry()
                );

        try(Session session = connectDB.getSession()){
            return session.executeWrite(tx -> {
               Result result = tx.run(cypher,params);
               return result.hasNext();
            });
        }
    }

    //tìm theo mã hóa đơn
    public boolean kiemTraHoaDon(String id){
        String cypher = "MATCH (o:Order{order_id:$id}) RETURN o";
        Map<String, Object> params = Map.of("id", id);

        try(Session session = connectDB.getSession()){
            return session.executeRead(tx -> {
                Result result = tx.run(cypher,params);
                return result.hasNext();

            });
        }
    }

    //nếu hóa đơn tồn tại thì tìm
    public double calculateTotalOrder(String orderID) {
        String cypher = """
            MATCH (p:Product) 
            MATCH (o:Order{order_id:$id}) 
            MATCH (o) - [r:ORDERS] -> (p) 
            RETURN SUM (r.quantity * r.unit_price - (r.quantity * r.unit_price * r.discount)) as total;
            """;
        Map<String, Object> params = Map.of("id", orderID);

        try (Session session = connectDB.getSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(cypher,params);
                Record record = result.next();
                var total = record.get("total");
                return total.asDouble();
            });
        }
    }


}
