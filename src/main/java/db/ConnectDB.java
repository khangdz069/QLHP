/*
 * @ (#) ConnectDB.java       1.0     3/31/2026
 *
 * Copyright (c) 2026 .
 * IUH.
 * All rights reserved.
 */
package db;

import org.neo4j.driver.*;

/*
 * @description:
 * @outhor: Khang
 * @date:   3/31/2026
 *version:  1.0
 */


public class ConnectDB {
    Driver driver;
    String dbName;

    public ConnectDB(String url, String userName, String pw, String dbName) {
        this.driver = GraphDatabase.driver(url, AuthTokens.basic(userName,pw));
        this.dbName = dbName;
    }

    public Session getSession(){
        return driver.session(SessionConfig.forDatabase(dbName));
    }

    public void close(){
        driver.close();
    }
}
