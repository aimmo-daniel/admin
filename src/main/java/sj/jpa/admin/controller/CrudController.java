package sj.jpa.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sj.jpa.admin.interfaces.CrudInterface;
import sj.jpa.admin.model.network.Header;
import sj.jpa.admin.service.BaseService;

@Component
public abstract class CrudController<Req, Res, Entity> implements CrudInterface<Req, Res> {

    @Autowired(required = false)
    protected BaseService<Req, Res, Entity> baseService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Header<Res> create(@RequestBody Header<Req> request) {
        return baseService.create(request);
    }

    @Override
    @GetMapping("/{id}")
    public Header<Res> read(@PathVariable("id") Long id) {
        return baseService.read(id);
    }

    @Override
    @PatchMapping
    public Header<Res> update(@RequestBody Header<Req> request) {
        return baseService.update(request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable("id") Long id) {
        return baseService.delete(id);
    }
}
