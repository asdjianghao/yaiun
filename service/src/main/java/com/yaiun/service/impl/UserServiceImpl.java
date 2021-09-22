package com.yaiun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaiun.dao.UserMapper;
import com.yaiun.service.UserService;
import com.yaiun.common.model.UserDO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wj
 * @since 2021-09-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

}
