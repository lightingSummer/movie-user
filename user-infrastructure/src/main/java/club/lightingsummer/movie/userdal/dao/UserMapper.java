package club.lightingsummer.movie.userdal.dao;

import club.lightingsummer.movie.userapi.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("select " +
            "UUID, user_name, user_pwd, nick_name, user_sex, birthday, email, user_phone, address, head_url, biography, life_state, begin_time, update_time, salt " +
            "from tb_user " +
            "where user_name = #{userName}")
    User selectUserByName(@Param("userName") String userName);
}