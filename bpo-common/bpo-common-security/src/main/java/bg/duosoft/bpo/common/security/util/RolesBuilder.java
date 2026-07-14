package bg.duosoft.bpo.common.security.util;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class RolesBuilder {

    public static final String OR_OPERATOR = "-or-";

    public static String matchAnyRole(List<String> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return null;
        }

        return String.join(OR_OPERATOR, roles);
    }
}
