package sj.jpa.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sj.jpa.admin.interfaces.CrudInterface;
import sj.jpa.admin.model.network.Header;

public abstract class CrudController<Req, Res> implements CrudInterface<Req, Res> {

    protected CrudInterface<Req, Res> baseService;

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
    @PutMapping
    public Header<Res> update(@RequestBody Header<Req> request) {
        return baseService.update(request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable("id") Long id) {
        return baseService.delete(id);
    }
}
