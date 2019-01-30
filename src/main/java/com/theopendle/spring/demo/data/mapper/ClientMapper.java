package com.theopendle.spring.demo.data.mapper;

import com.theopendle.spring.demo.model.Client;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClientMapper {

    @Select("SELECT id as id, first_name as firstName, last_name as lastName FROM clients WHERE id = #{id}")
    Client selectOne(long id);

    @Insert("INSERT INTO clients (id, first_name, last_name) VALUES (#{id}, #{firstName}, #{lastName})")
    // Sets the object id to the id generated in database
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertClient(Client client);

    Client findByFirstName(String firstName);
}
