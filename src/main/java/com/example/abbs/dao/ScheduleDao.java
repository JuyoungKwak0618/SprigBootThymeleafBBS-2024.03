package com.example.abbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.abbs.entity.Schedule;

@Mapper
public interface ScheduleDao {

	@Select("select * from schedule where sid=#{sid}")
	Schedule getSchedule(int sid);
	
	@Select("select * from schedule where uid=#{uid} and "
			+ " sdate between #{startDay} and #{endDay} order by sdate, startTime")
	List<Schedule> getScheduleList(String uid, String startDay, String endDay);
	
	@Insert("insert into schedule values"
			+ " (default,#{uid}, #{sdate}, #{title}, #{place}, #{startTime}, #{endTime}, #{isimportant}, #{memo})")
	void insertSchedule(Schedule schedule);
	
	@Update("update schedule set sdate=#{sdate}, title=#{title}, place=#{place},"
			+ " startTime=#{startTime}, endTime=#{endTime},isimportant=#{isimportant}, memo=#{memo} where sid=#{sid}")
	void updateSchedule(Schedule schedule);
	
	@Delete("delete from schedule where sid=#{sid}")
	void deleteSchedule(int sid);
	
	
	
}
