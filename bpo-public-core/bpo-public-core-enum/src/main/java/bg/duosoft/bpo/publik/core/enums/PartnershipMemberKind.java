package bg.duosoft.bpo.publik.core.enums;

import java.util.Arrays;

public enum PartnershipMemberKind {
    MANAGER,
    MEMBER,
    SOLE_OWNER,
    MEMBER_OF_MANAGEMENT_BOARD,
    MEMBER_OF_BOARD_OF_DIRECTORS,
    OTHER;

    public String value() {
        return name();
    }

    public static PartnershipMemberKind selectByName(String name) {
        PartnershipMemberKind result = Arrays.stream(
                PartnershipMemberKind.values())
                .filter(c -> c.name().equals(name))
                .findFirst()
                .orElse(null);

        return result;
    }
}
