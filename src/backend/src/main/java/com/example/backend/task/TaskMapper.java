package com.example.backend.task;


import com.example.backend.Request;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TaskMapper {

    List<Task> findByUserId(Integer userId);

    void insertTask(Task task);
    void updateTask(Task task);

    void deleteTask(Task task);
    Task findTaskById(Long taskId);
    @Select("SELECT rid, name, address, request_time as requestTime, phone, service_type as serviceType, product_name as productName, completion_status as completionStatus FROM request")
    List<Request> getTasks();
    @Update("update request set completion_status = #{code} where rid = #{rid}")
    void toggle(boolean code,int rid);
}
