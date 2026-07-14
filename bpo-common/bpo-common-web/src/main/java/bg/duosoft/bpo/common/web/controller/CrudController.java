package bg.duosoft.bpo.common.web.controller;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.common.util.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * User: ggeorgiev
 * Date: 15.09.2022
 * Time: 14:01
 */
public abstract class CrudController<ID extends Serializable, D extends BaseDTO<ID>> extends BaseAccessController {
    protected abstract CrudServiceBase<ID, D> getService();

    public enum ENDPOINT {
        GET, CREATE, UPDATE, DELETE, LIST;
    }

    protected Set<ENDPOINT> getSupportedEndpoints() {
        return Set.of(ENDPOINT.values());
    }

    @GetMapping(value = "/{id}")
    @Operation(description = "Select single value")
    public D getById(@PathVariable("id") ID id) {
        checkIfEndpointIsSupported(ENDPOINT.GET);
        D result = getService().getById(id);
        if (Objects.isNull(result)) {
            throw new ResourceNotFoundException();
        }
        return result;
    }
    @PostMapping
    @Operation(description = "Insert  value")
    public D create(@RequestBody D dto) {
        checkIfEndpointIsSupported(ENDPOINT.CREATE);
        return getService().create(dto);
    }

    @PutMapping
    @Operation(description = "Update value")
    public D update(@RequestBody D dto) {
        checkIfEndpointIsSupported(ENDPOINT.UPDATE);
        return getService().update(dto);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(description = "Delete value")
    public void delete(@PathVariable("id") ID id) {
        checkIfEndpointIsSupported(ENDPOINT.DELETE);
        getService().delete(id);
    }

    @GetMapping
    @Operation(description = "Select all records")
    public List<D> getAll() {
        checkIfEndpointIsSupported(ENDPOINT.LIST);
        return getService().getAll();
    }

    private void checkIfEndpointIsSupported(ENDPOINT endpoint){
        if (!getSupportedEndpoints().contains(endpoint)) {
            throw new RuntimeException("Endpoint not supported: "+endpoint.name());
        }
    }
}
