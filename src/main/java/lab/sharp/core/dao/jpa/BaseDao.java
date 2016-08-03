package lab.sharp.core.dao.jpa;

import java.io.Serializable;

import lab.sharp.core.dao.MiniBaseDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>, MiniBaseDao<T, ID> {

}
