package com.gdut.imis.esclientdemo.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gdut.imis.esclientdemo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lele
 * @since 2019-06-10
 */
public interface UserDao extends BaseMapper<User> {
List<User> getUserForTestMapperLocation();
void test();
@Select("SELECT u.* FROM USER u LEFT JOIN `role-user` ru ON u.id=ru.user_id LEFT JOIN role r ON r.id=ru.role_id ${ew.customSqlSegment}")
List<User> findFreash(@Param(Constants.WRAPPER)Wrapper wrapper);
@Select("select ${ew.sqlSelect} from user ${ew.customSqlSegment} ")
List<User> testSqlSelect(@Param(Constants.WRAPPER)Wrapper wrapper);
@Update("update `role-user` ${ew.sqlSet} ${ew.customSqlSegment}")
int testSqlSet(@Param(Constants.WRAPPER)Wrapper wrapper);

    @Select("SELECT * FROM USER u LEFT JOIN `role-user` ru ON u.id=ru.user_id LEFT JOIN role r ON r.id=ru.role_id ${ew.customSqlSegment}")
    List<Map<String,Object>> findAll(@Param(Constants.WRAPPER)Wrapper wrapper);
    @Select("SELECT * FROM USER u LEFT JOIN `role-user` ru ON u.id=ru.user_id LEFT JOIN role r ON r.id=ru.role_id ${ew.customSqlSegment}")
    List<Map<String,Object>> findWithPage(@Param(Constants.WRAPPER)Wrapper wrapper, IPage page);

}
