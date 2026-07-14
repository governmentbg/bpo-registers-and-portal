package bg.duosoft.bpo.registers.utils.search.bridge;

import bg.duosoft.bpo.registers.entity.ipobject.EIpPersonToIpObject;
import bg.duosoft.bpo.publik.core.enums.PersonRole;
import org.hibernate.search.engine.backend.document.DocumentElement;
import org.hibernate.search.engine.backend.document.IndexFieldReference;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.bridge.TypeBridge;
import org.hibernate.search.mapper.pojo.bridge.binding.TypeBindingContext;
import org.hibernate.search.mapper.pojo.bridge.mapping.programmatic.TypeBinder;
import org.hibernate.search.mapper.pojo.bridge.runtime.TypeBridgeWriteContext;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * User: ggeorgiev
 * Date: 09.05.2022
 * Time: 13:31
 */
public class RepresentativeBinder implements TypeBinder {
    private static final String AGENT_CODE_FIELD_NAME = "agentCodeExact";
    private static final String REPRESENTATIVE_TYPE_FIELD_NAME = "representativeType";


    @Override
    public void bind(TypeBindingContext context) {
        context.dependencies()
                .use( "person.agent" );
        Map<String, IndexFieldReference<String>> fields = new HashMap<>();
        IndexFieldReference<String> agentCodeField = context.indexSchemaElement()
                .field( AGENT_CODE_FIELD_NAME, f -> f.asString().projectable(Projectable.NO).searchable(Searchable.YES) )
                .toReference();
        fields.put(AGENT_CODE_FIELD_NAME, agentCodeField);


        IndexFieldReference<String> representativeTypeField = context.indexSchemaElement()
                .field( REPRESENTATIVE_TYPE_FIELD_NAME, f -> f.asString().projectable(Projectable.NO).searchable(Searchable.YES) )
                .toReference();
        fields.put(REPRESENTATIVE_TYPE_FIELD_NAME, representativeTypeField);

        context.bridge(EIpPersonToIpObject.class, new RepresentativeBridge(fields));
    }


    private static class RepresentativeBridge implements TypeBridge<EIpPersonToIpObject> {
        private Map<String, IndexFieldReference<String>> fields;
        public RepresentativeBridge(Map<String, IndexFieldReference<String>> fields) {
            this.fields = fields;
        }

        @Override
        public void write(DocumentElement target, EIpPersonToIpObject eIpPersonToIpObject, TypeBridgeWriteContext typeBridgeWriteContext) {
            if (eIpPersonToIpObject.getId().getPersonRole().equals(PersonRole.REPRESENTATIVE.code())) {
                if (eIpPersonToIpObject.getRepresentativeType().getHasAgentCode() == 1) {
                    if (eIpPersonToIpObject.getPerson().getAgent() != null && !ObjectUtils.isEmpty(eIpPersonToIpObject.getPerson().getAgent().getAgentCode())) {
                        target.addValue( fields.get(AGENT_CODE_FIELD_NAME), eIpPersonToIpObject.getPerson().getAgent().getAgentCode() );
                    } else {
                        throw new RuntimeException("RepresentativeType is " + eIpPersonToIpObject.getRepresentativeType().getId() + " and there is no agent code for the give person " + eIpPersonToIpObject.getPerson().getId());
                    }
                } else {
                    target.addValue( fields.get(AGENT_CODE_FIELD_NAME), eIpPersonToIpObject.getPerson().getId().toString() );
                }
                target.addValue(fields.get(REPRESENTATIVE_TYPE_FIELD_NAME), eIpPersonToIpObject.getRepresentativeType().getId());
            }
        }
    }
}
