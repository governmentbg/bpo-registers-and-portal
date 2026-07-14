package bg.duosoft.bpo.common.security.dto;

import bg.duosoft.bpo.common.security.util.ExtRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.07.2022
 * Time: 11:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserDetails {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String parentUsername;
    private List<ExtRole> extendedRoles;
}
