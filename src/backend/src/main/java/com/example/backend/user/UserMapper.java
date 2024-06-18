package com.example.backend.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    void insertUser(User user);

    User findUserByUsername(String username);
    @Update("        UPDATE user\n" +
            "        SET\n" +
            "            name = #{name},\n" +
            "            gender = #{gender},\n" +
            "            birthDate = #{birthDate},\n" +
            "            description = #{description},\n" +
            "            age = #{age},\n" +
            "            address = #{address},\n" +
            "            phone = #{phone}\n" +
            "        WHERE username = #{username}")
    void updateUser(User user);
    @Select("SELECT menu, path FROM role WHERE rid = #{role}")
    List<Map<String,String>>  getMenuList(int role);
    @Select("SELECT username,name,gender,age,uid FROM user where role = 1")
    List<User> getUserList();
    @Insert("INSERT INTO healthrecord (uid, name, gender, age) VALUES (#{uid}, #{name}, #{gender}, #{age})")
    void addFile(User user);

}
