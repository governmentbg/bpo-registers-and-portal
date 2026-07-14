package bg.duosoft.bpo.common.service.service.impl;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.common.service.validator.BadRequestValidator;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.common.util.exception.BadRequestException;
import bg.duosoft.bpo.common.util.exception.ResourceNotFoundException;
import bg.duosoft.logging.annotation.LogObjectChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 14.09.2022
 * Time: 17:38
 */
@Slf4j
public abstract class CrudServiceBaseImpl<ID extends Serializable, DTO extends BaseDTO<ID>> implements CrudServiceBase<ID, DTO> {
    protected static final String ALL_VALUES_CACHE_NAME = "'$all-values$'";

    @Autowired
    private CacheManager cacheManager;

    protected abstract <EID extends Serializable, E extends BaseEntity<EID>> BaseRepository<EID, E> getRepository();

    protected abstract <EID extends Serializable, E extends BaseEntity<EID>> BaseObjectMapper<E, DTO> getMapper();

    protected abstract Validator<DTO> getValidator();

    public boolean isCacheable() {
        return false;
    }

    public boolean isLoggable() {
        return true;
    }

    protected boolean isSortable() {
        return false;
    }

    protected Sort.Direction getSortDirection() {
        return Sort.Direction.ASC;
    }

    protected String getSortColumn() {
        return null;
    }

    protected <EID extends Serializable> EID toEntityId(ID id) {
        try {
            return (EID)id;
        } catch (Exception e) {
            throw new RuntimeException("Method toEntityId should be implemented for complex id types idType:" + id.getClass().getName(), e);
        }
    }

    /**
     * @param d
     * @return the String representation of the dto's id which will be used inside the cache!
     */
    protected String getCacheId(ID d) {
        return d.toString();
    }
    @Cacheable(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_VALUES_CACHE_NAME)
    @Transactional
    public List<DTO> getAll() {
        List<BaseEntity<Serializable>> entities;

        if (isSortable() && StringUtils.hasText(getSortColumn())) {
            entities = getRepository().findAll(Sort.by(List.of(new Sort.Order(getSortDirection(), getSortColumn()))));
        } else {
            entities = getRepository().findAll();
        }
        return getMapper().toDtoList(entities);
    }

    @Cacheable(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = "#objectId == null ? null : #objectId.toString()")
    @Transactional
    public DTO getById(ID objectId) {
        if (isCacheable()) {
            if (cacheValuesIfNotCached()) {
                return getCacheValue(objectId);
            }
        }
        BaseEntity<Serializable> e = getRepository().findById(toEntityId(objectId)).orElse(null);
        return getMapper().toDto(e);
    }

    /**
     * @return
     *  - true if the cache was empty before the call and the elements are getting cached now
     *  - false if the values were already cached
     */
    protected boolean cacheValuesIfNotCached() {
        Cache cache = cacheManager.getCache(getCacheName());
        String cachedKey = "$cached-single-values$";
        if (cache.get(cachedKey, Boolean.class) == null) {
            synchronized (this) {
                log.debug("cache is empty. caching objects for class ..." + getClass().getName());
                List<BaseEntity<Serializable>> recs = getRepository().findAll();
                getMapper().toDtoList(recs).forEach(r -> cache.put(getCacheId(r.getId()), r));
                cache.put(cachedKey, true);
            }
            return true;
        }
        return false;
    }
    protected DTO getCacheValue(ID objectId) {
        Cache cache = cacheManager.getCache(getCacheName());
        Cache.ValueWrapper res = objectId == null ? null : cache.get(getCacheId(objectId));
        return res == null ? null : (DTO) res.get();
    }
    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_VALUES_CACHE_NAME)
    @LogObjectChange(id = "#result.id", after = "#result", condition = "#root.target.isLoggable()")
    @Transactional
    public DTO create(DTO dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException();
        }
        Validator<DTO> validator = getValidator();
        if (validator != null) {
            BadRequestValidator.validateRequest(validator, dto, true, this);
        }
        beforeCreate(dto);
        beforeCreateOrUpdate(dto);
        BaseEntity<Serializable> e = getMapper().toEntity(dto);
        e = getRepository().save(e);
        return getMapper().toDto(e);
    }
    @Caching(
            evict = {
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = "#root.target.getCacheId(#dto)"),
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_VALUES_CACHE_NAME)
            }
    )
    @LogObjectChange(id = "#dto.id", before = "#root.target.selectById(#dto.id)", after = "#result", condition = "#root.target.isLoggable()")
    @Transactional
    public DTO update(DTO dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException();
        }
        Validator<DTO> validator = getValidator();
        if (validator != null) {
            BadRequestValidator.validateRequest(validator, dto, false, this);
        }
        beforeUpdate(dto);
        beforeCreateOrUpdate(dto);
        BaseEntity<Serializable> e = getRepository().save(getMapper().toEntity(dto));
        return getMapper().toDto(e);
    }

    @Caching(
            evict = {
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = "#objectId == null ? null : #objectId.toString()"),
                    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", key = ALL_VALUES_CACHE_NAME)
            }
    )
    @LogObjectChange(id = "#objectId", before = "#root.target.selectById(#objectId)", condition = "#root.target.isLoggable()")
    @Transactional
    public void delete(ID objectId) {
        BaseEntity<Serializable> e = getRepository().findById(toEntityId(objectId)).orElse(null);
        if (Objects.isNull(e)) {
            throw new ResourceNotFoundException();
        }
        beforeDeleteById(objectId);
        getRepository().delete(e);
    }
    public String getCacheName() {
        return getClass().getSimpleName();
    }

    @CacheEvict(cacheResolver = "crudCacheResolver", condition = "#root.target.isCacheable()", allEntries = true)
    @Transactional
    public void deleteAll() {
        getRepository().deleteAll();
    }


    /**
     * executed before calling the update method
     * @param dto
     */
    protected void beforeUpdate(DTO dto) {

    }

    /**
     * executed before calling the save method
     * @param dto
     */
    protected void beforeCreate(DTO dto) {

    }

    /**
     * executed before calling the create or update method.
     * @param dto
     */
    protected void beforeCreateOrUpdate(DTO dto) {

    }
    protected void beforeDeleteById(ID objectId) {

    }
}
