package com.renguangli;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

     Long insert(@Param("user") User user);

     User delete(@Param("userId") Integer userId);

     User update(@Param("username") String username,@Param("password") String password);

     User get(@Param("userId") Integer userId, @Param("username") String username,@Param("password") String password);

     User getAll();
}
