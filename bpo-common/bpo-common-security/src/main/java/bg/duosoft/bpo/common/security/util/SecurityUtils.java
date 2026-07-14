package bg.duosoft.bpo.common.security.util;

import bg.duosoft.bpo.common.security.dto.BaseUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 22.06.2022
 * Time: 11:56
 */
public class SecurityUtils {

    public static final String USERNAME_CLAIM = "preferred_username";
    public static final String FIRST_NAME_CLAIM = "given_name";
    public static final String LAST_NAME_CLAIM = "family_name";
    public static final String EMAIL_CLAIM = "email";
    public static final String SUB_CLAIM = "sub";
    public static final String PARENT_CLAIM = "parent";
    public static final String EXT_ROLES_CLAIM = "extroles";

    public static boolean isUserAuthenticated() {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null &&
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Jwt) {
            return true;
        }
        return false;
    }

    public static Object getClaim(String claim) {
        if (isUserAuthenticated()) {
            Jwt jwt = getJwt();
            if (jwt != null) {
                return jwt.getClaims().get(claim);
            }
        }
        return null;
    }

    public static boolean hasRole(String roleName) {
        return hasAnyRole(roleName);
    }

    public static String getUsername() {
        return (String) getClaim(USERNAME_CLAIM);
    }

    public static String getEmail() {
        return (String) getClaim(EMAIL_CLAIM);
    }

    public static String getAccessToken() {
        if (isUserAuthenticated()) {
            return getJwt().getTokenValue();
        }
        return null;
    }

    public static Jwt getJwt() {
        return (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static boolean hasAnyRole(String... roles) {
        if (roles == null || roles.length == 0 || !isUserAuthenticated()) {
            return false;
        }
        Set<String> rolesWithoutPrefix = Arrays.stream(roles).map(r -> getRoleWithoutPrefix(r)).collect(Collectors.toSet());
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(r -> r.getAuthority()).map(r -> getRoleWithoutPrefix(r)).anyMatch(r -> rolesWithoutPrefix.contains(r));
    }
    private static String getRoleWithoutPrefix(String role) {
        return role.startsWith("ROLE_") ? role.substring("ROLE_".length()) : role;
    }

    public static BaseUserDetails getAccessTokenUserDetails() {
        if (isUserAuthenticated()) {
            BaseUserDetails userDetails = new BaseUserDetails(
                    (String) getClaim(SUB_CLAIM),
                    getUsername(),
                    (String) getClaim(FIRST_NAME_CLAIM),
                    (String) getClaim(LAST_NAME_CLAIM),
                    getEmail(),
                    getParentUsername(),
                    convertExtendedRoles((String) getClaim(EXT_ROLES_CLAIM))
                   );
            return userDetails;
        }
        return null;
    }

    private static List<ExtRole> convertExtendedRoles(String extRolesStr){
        if(StringUtils.hasText(extRolesStr)) {
            return Arrays.stream(extRolesStr.split(",")).map(r -> ExtRole.fromValue(r)).filter(r -> r != null).toList();
        }
        return new ArrayList<>();
    }

    public static String getParentUsername(){
        String parent = (String) getClaim(PARENT_CLAIM);
        if(StringUtils.hasText(parent)){
            return parent;
        }
        return getUsername();
    }

    public static boolean hasAnyExtRole(ExtRole... extRoles) {
        BaseUserDetails userDetails = getAccessTokenUserDetails();
        if(userDetails == null){
            return false;
        }
        boolean hasAny = Arrays.stream(extRoles).map(r -> userDetails.getExtendedRoles().contains(r))
                .reduce((b1, b2) -> b1 || b2).get();

        return hasAny;
    }

}
