package com.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.dto.UserRequestDTO;
import com.dto.UserResponseDTO;

@Mapper
@Repository
public interface UserRepository {

	String insert = "INSERT INTO user (id, name, img) VALUES (#{id}, #{name}, #{img})";
	
	String select = "SELECT * FROM user";
	
	@Insert(insert)
	public int insertUser(UserRequestDTO dto);
	
	@Select(select)
	public UserResponseDTO selectUser();
}
