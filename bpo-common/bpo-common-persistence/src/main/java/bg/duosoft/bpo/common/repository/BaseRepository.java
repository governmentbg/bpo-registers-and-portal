package bg.duosoft.bpo.common.repository;

import bg.duosoft.bpo.common.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository< ID extends Serializable, T extends BaseEntity<ID>> extends JpaRepository<T, ID> {

}
