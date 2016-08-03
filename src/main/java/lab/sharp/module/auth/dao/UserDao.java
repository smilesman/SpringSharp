package lab.sharp.module.auth.dao;

import lab.sharp.core.dao.jpa.BaseDao;
import lab.sharp.module.auth.entity.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<User, Long> {

}