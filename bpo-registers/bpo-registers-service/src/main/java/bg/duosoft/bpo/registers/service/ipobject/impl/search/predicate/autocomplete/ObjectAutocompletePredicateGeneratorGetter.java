package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.autocomplete;

/**
 * User: ggeorgiev
 * Date: 09.02.2024
 * Time: 17:47
 */
public class ObjectAutocompletePredicateGeneratorGetter {
    public static AutocompletePredicateGenerator getFilingNumberPredicateGenerator() {
        return new ObjectAutocompleteFilingNumberGenerator();
    }
    public static AutocompletePredicateGenerator getObjectTypePredicateGenerator() {
        return new ObjectAutocompleteObjectTypePredicateGenerator();
    }
    public static AutocompletePredicateGenerator getRegistrationNumberPredicateGenerator() {
        return new ObjectAutocompleteRegistrationNumberPredicateGenerator();
    }
}
