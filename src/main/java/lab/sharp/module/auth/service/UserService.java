package lab.sharp.module.auth.service;

import lab.sharp.core.dao.jpa.BaseDao;
import lab.sharp.core.service.BaseService;
import lab.sharp.module.auth.dao.UserDao;
import lab.sharp.module.auth.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends BaseService<User, Long> {

    @Autowired
    private UserDao userDao;

    @Override
    protected BaseDao<User, Long> getEntityDao() {
        return userDao;
    }
    public User save(User entity) {
        return super.save(entity);
    }
}
