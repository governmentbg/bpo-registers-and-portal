package bg.duosoft.bpo.portal.repository;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.portal.entity.FeedbackEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends BaseRepository<Integer, FeedbackEntity> {
}
