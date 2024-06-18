package com.example.backend.source;

import com.example.backend.Event;
import com.example.backend.Request;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Mapper
public interface SourceMapper {
    void addPicture(Picture picture);
    void addMedicine(Medicine medicine);
    void addFood(Food food);
//    void addVideo(Video video);
//    List<String> getAllPictureUrls();
//    List<String> getAllVideoUrls();
    @Select("SELECT fid,description, url FROM food")
    List<Map<String, String>> getAllPictures();

    @Select("SELECT mid,description, url FROM medicine")
    List<Map<String, String>> getAllMeds();

    @Insert("INSERT INTO request (name, address, request_time, phone, service_type, product_name, completion_status) " +
            "VALUES (#{name}, #{address}, #{requestTime}, #{phone}, #{serviceType}, #{productName}, #{completionStatus})")
    @Options(useGeneratedKeys = true, keyProperty = "rid")
    void addRequest(Request request);

    @Select("SELECT description FROM food WHERE fid = #{fid}")
    String getFoodName(int fid);

    @Select("SELECT description FROM medicine WHERE mid = #{mid}")
    String getMedicineName(int mid);
    @Insert("INSERT INTO event(name, phone, time, address, location, description, isSolved) " +
            "VALUES(#{name}, #{phone}, #{time}, #{address}, #{location}, #{description}, #{isSolved})")
    void addEvent(Event event);
    @Select("SELECT * from event")
    List<Event> fetchEvent();
}
