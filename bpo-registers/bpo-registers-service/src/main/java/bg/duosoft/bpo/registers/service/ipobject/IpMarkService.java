package bg.duosoft.bpo.registers.service.ipobject;


import bg.duosoft.bpo.registers.dto.ipobject.IpMarkDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:42
 */
public interface IpMarkService extends IpObjectServiceBase<IpMarkDTO> {

    Page<IpMarkDTO> getMarksPaged(Pageable pageable);

}
