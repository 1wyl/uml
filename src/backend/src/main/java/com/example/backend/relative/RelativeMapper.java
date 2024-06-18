package com.example.backend.relative;


import com.example.backend.task.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RelativeMapper {
    void addRelative(Relative relative);
    List<Relative> getAllRelatives(@Param("id") int id);
    Relative findRelativeByRid(@Param("rid") int rid);
    void updateRelative(Relative relative);
}

