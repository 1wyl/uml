package com.example.backend.healthFile;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface HealthRecordMapper {
    @Select("SELECT * FROM healthrecord WHERE uid = #{uid}")
    HealthRecord getHealthRecordByUid(int uid);

    @Update("UPDATE healthrecord SET height=#{healthRecord.height}, weight=#{healthRecord.weight}, bloodtype=#{healthRecord.bloodType}, " +
            "allergies=#{healthRecord.allergies}, medicalhistory=#{healthRecord.medicalHistory}, " +
            "medications=#{healthRecord.medications}, smokingstatus=#{healthRecord.smokingStatus}, " +
            "alcoholuse=#{healthRecord.alcoholUse}, physicalactivity=#{healthRecord.physicalActivity}, " +
            "diet=#{healthRecord.diet}, bloodpressure=#{healthRecord.bloodPressure}, " +
            "cholesterollevel=#{healthRecord.cholesterolLevel}, glucoselevel=#{healthRecord.glucoseLevel} " +
            "WHERE uid=#{uid}")
    void updateHealthRecord(@Param("uid") int uid, @Param("healthRecord") HealthRecord healthRecord);

}
