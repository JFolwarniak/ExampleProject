package org.example;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

    @Mapper
    public interface WeatherMapper {
        @Select("SELECT data FROM city WHERE state = #{state}")
        String findByState(@Param("state") String state);

        @Insert("Insert into weather(temperature) values (#{temp})")
        public void save(Temperature temperature);

    }

