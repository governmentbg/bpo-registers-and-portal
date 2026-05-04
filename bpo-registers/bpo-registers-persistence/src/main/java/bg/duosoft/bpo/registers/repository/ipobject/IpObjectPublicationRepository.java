package bg.duosoft.bpo.registers.repository.ipobject;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPublicationSection;
import bg.duosoft.bpo.registers.entity.ipobject.EIpObjectPublication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 12.02.2024
 * Time: 13:18
 */
public interface IpObjectPublicationRepository extends BaseRepository<Integer, EIpObjectPublication> {
    @Query("""
            SELECT distinct p.publicationYear from EIpObjectPublication p JOIN EIpObject  o on p.objectId = o.id
            WHERE o.objectType.id in (:objectTypes) ORDER by p.publicationYear asc""")
    List<Integer> getObjectPublicationYears(@Param("objectTypes") List<String> objectTypes);

    @Query("""
            SELECT p from EIpObjectPublication p join EIpObject o on p.objectId = o.id
            WHERE o.objectType.id in (:objectTypes) and p.publicationYear = :year and p.publicationNumber = :publicationNumber and p.publicationSection.publicationCode in (:publicationCodes)""")
    public List<EIpObjectPublication> getObjectPublications(@Param("year") Integer year, @Param("publicationNumber") String publicationNumber, @Param("objectTypes") List<String> objectTypes, @Param("publicationCodes") List<String> publicationCodes);

    @Query("""
            SELECT DISTINCT p.publicationNumber FROM EIpObjectPublication p JOIN EIpObject o ON p.objectId= o.id 
            WHERE o.objectType.id in (:objectTypes) AND p.publicationYear = :year ORDER BY p.publicationNumber ASC""")
    List<String> getObjectPublicationNumbers(@Param("year") Integer year, @Param("objectTypes") List<String> objectTypes);

    @Query("""
            SELECT DISTINCT p.publicationSection FROM EIpObjectPublication p JOIN EIpObject o ON p.objectId= o.id 
            WHERE o.objectType.id in (:objectTypes) AND p.publicationYear = :year AND p.publicationNumber = :number ORDER BY p.publicationSection.nmsection ASC""")
    List<EPublicationSection> getObjectPublicationSections(@Param("year") Integer year, @Param("number") String number, @Param("objectTypes") List<String> objectTypes);

    @Query("""
            SELECT DISTINCT p.publicationSection FROM EIpObjectPublication p JOIN EIpObject o ON p.objectId= o.id 
            WHERE o.objectType.id in (:objectTypes) AND p.publicationYear = :year ORDER BY p.publicationSection.nmsection ASC""")
    List<EPublicationSection> getObjectPublicationSections(@Param("year") Integer year, @Param("objectTypes") List<String> objectTypes);

    @Query("""
            SELECT DISTINCT p.publicationSection FROM EIpObjectPublication p JOIN EIpObject o ON p.objectId= o.id 
            WHERE o.objectType.id in (:objectTypes) ORDER BY p.publicationSection.nmsection ASC""")
    List<EPublicationSection> getObjectPublicationSections(@Param("objectTypes") List<String> objectTypes);

}
