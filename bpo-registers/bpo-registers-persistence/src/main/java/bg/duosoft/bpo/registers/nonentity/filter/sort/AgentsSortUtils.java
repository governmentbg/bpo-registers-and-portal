package bg.duosoft.bpo.registers.nonentity.filter.sort;

import java.util.HashMap;
import java.util.Map;

public class AgentsSortUtils {

    public static final String agentNameBg = "agentName-bg";
    public static final String agentNameEn = "agentName-en";

    public static final String partnershipNameBg = "partnershipName-bg";
    public static final String partnershipNameEn = "partnershipName-en";
    public static final String agentCodeBg = "agentCode-bg";
    public static final String agentCodeEn = "agentCode-en";
    public static final String agentSpecialityBg = "agentSpeciality-bg";
    public static final String agentSpecialityEn = "agentSpeciality-en";
    public static final String ipoAreaBg = "ipoArea-bg";
    public static final String ipoAreaEn = "ipoArea-en";
    public static final String agentStatusBg = "agentStatus-bg";
    public static final String agentStatusEn = "agentStatus-en";

    public static Map<String, String> sorterColumnMap() {
        Map<String, String> map = new HashMap<>();
        map.put(agentNameBg, "ag.name");
        map.put(agentNameEn, "ag.agent.nameEn");
        map.put(partnershipNameBg, "ag.name");
        map.put(partnershipNameEn, "ag.agent.nameEn");
        map.put(agentCodeBg, "CAST(replace(ag.agent.agentCode,'A','') as INTEGER)");
        map.put(agentCodeEn, "CAST(replace(ag.agent.agentCode,'A','') as INTEGER)");
        map.put(agentSpecialityBg, "ag.agent.speciality");
        map.put(agentSpecialityEn, "ag.agent.specialityEn");
        map.put(ipoAreaBg, "ag.agent.agentSpeciality.name");
        map.put(ipoAreaEn, "ag.agent.agentSpeciality.nameEn");
        map.put(agentStatusBg, "ag.agent.agentStatus.name");
        map.put(agentStatusEn, "ag.agent.agentStatus.nameEn");
        return map;
    }
}
