package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 11:24
 */
@Data
public class RelationshipTypeDTO implements BaseDTO<RelationshipTypeId> {

    private RelationshipTypeId id;


    private String relationshipName;
    private String relationshipNameEn;
    private String directRelationshipName;
    private String directRelationshipNameEn;
    private String inverseRelationshipName;
    private String inverseRelationshipNameEn;
}
