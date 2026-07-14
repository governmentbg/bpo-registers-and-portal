package bg.duosoft.bpo.registers.service.ipobject.impl;

import bg.duosoft.bpo.registers.dto.filter.PersonFilter;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPerson;
import bg.duosoft.bpo.registers.mapper.ipobject.IpPersonMapper;
import bg.duosoft.bpo.registers.mapper.ipobject.search.PersonFilterMapper;
import bg.duosoft.bpo.registers.nonentity.filter.EIpPersonFilter;
import bg.duosoft.bpo.registers.repository.ipobject.IpPersonRepository;
import bg.duosoft.bpo.registers.service.ipobject.IpPersonService;
import bg.duosoft.bpo.registers.service.search.SearchServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 06.11.2024
 * Time: 17:01
 */
@Service
@RequiredArgsConstructor
@Transactional
public class IpPersonServiceImpl extends SearchServiceBase implements IpPersonService {
    private final IpPersonRepository repository;
    private final IpPersonMapper mapper;
    private final PersonFilterMapper personFilterMapper;

    @Override
    public Page<IpPersonDTO> findPersons(PersonFilter filter) {
        EIpPersonFilter efilter = personFilterMapper.toEntity(filter);
        List<EIpPerson> res = repository.findPersons(efilter);
        Integer count = repository.countPersons(efilter);
        Pageable pageable = getPage(filter);
        return new PageImpl<>(mapper.toDtoList(res), pageable, count);
    }

    @Override
    public IpPersonDTO getPerson(int id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<IpPersonDTO> getMatchingPersons(IpPersonDTO person, int maxCount, boolean strongMatch) {
        return mapper.toDtoList(repository.getMatchingPersons(mapper.toEntity(person), maxCount, strongMatch));
    }
}
