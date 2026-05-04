package bg.duosoft.bpo.common.web.controller;


import bg.duosoft.bpo.common.security.util.RolesBuilder;
import bg.duosoft.bpo.common.security.util.SecurityUtils;
import bg.duosoft.bpo.common.util.exception.ForbiddenException;
import bg.duosoft.bpo.common.util.exception.UnauthorizedException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 15.09.2022
 * Time: 14:01
 */
public abstract class BaseAccessController {
    public abstract String getEditRole();

    public abstract String getAccessRole();

    public String getDeleteRole() {
        return getEditRole();
    }

    public boolean getAccessIsSecured(){
        return true;
    }

    public static void checkPermissions(String roles) {
        if (!SecurityUtils.isUserAuthenticated()) {
            throw new UnauthorizedException();
        }

        if (Objects.nonNull(roles)) {
            List<String> roleList = splitRoles(roles, RolesBuilder.OR_OPERATOR);
            if (roleList.size() == 1) {
                if (!SecurityUtils.hasRole(roleList.get(0))) {
                    throw new ForbiddenException();
                }
            } else {
                if (!SecurityUtils.hasAnyRole(roleList.toArray(new String[0]))) {
                    throw new ForbiddenException();
                }
            }
        }
    }

    private static List<String> splitRoles(String roles, String operator) {
        return Arrays.stream(roles.split(operator))
                .filter(Objects::nonNull)
                .map(String::trim)
                .toList();
    }
}
