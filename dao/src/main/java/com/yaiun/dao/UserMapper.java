package com.yaiun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yaiun.common.model.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wj
 * @since 2021-09-22
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

}
