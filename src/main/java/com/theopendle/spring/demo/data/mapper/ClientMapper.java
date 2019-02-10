package com.theopendle.spring.demo.data.mapper;

import com.theopendle.spring.demo.model.Client;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClientMapper {

    @Select("SELECT id as id, first_name as firstName, last_name as lastName FROM spring_demo.clients WHERE id = #{id}")
    Client selectOne(long id);

    @Select("SELECT id as id, first_name as firstName, last_name as lastName FROM spring_demo.clients")
    List<Client> findAll();

    @Insert("INSERT INTO spring_demo.clients (id, first_name, last_name) VALUES (#{id}, #{firstName}, #{lastName})")
    // Sets the object id to the id generated in database
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertClient(Client client);

    List<Client> findByFirstName(String firstName);
}
