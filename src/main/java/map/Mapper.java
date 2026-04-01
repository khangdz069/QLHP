/*
 * @ (#) Mapper.java       1.0     4/1/2026
 *
 * Copyright (c) 2026 .
 * IUH.
 * All rights reserved.
 */
package map;

import tools.jackson.databind.ObjectMapper;

import java.util.Map;

/*
 * @description:
 * @outhor: Khang
 * @date:   4/1/2026
 *version:  1.0
 */
public class Mapper {
    ObjectMapper mapper;

    public Mapper() {
        this.mapper = new ObjectMapper();
    }

    public Map<String, Object> toMap(Object object){
        return mapper.convertValue(object, Map.class);
    }

    public <T> T toObject (Map<String, Object> map, Class<T> clazz){
        return mapper.convertValue(map,clazz);
    }
}
