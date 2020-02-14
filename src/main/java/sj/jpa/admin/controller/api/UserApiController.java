package sj.jpa.admin.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sj.jpa.admin.interfaces.CrudInterface;
import sj.jpa.admin.model.network.Header;
import sj.jpa.admin.model.network.request.UserApiRequest;
import sj.jpa.admin.model.network.response.UserApiResponse;
import sj.jpa.admin.service.UserApiLogicService;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    private UserApiLogicService userApiLogicService;

    @Autowired
    public UserApiController(UserApiLogicService userApiLogicService) {
        this.userApiLogicService = userApiLogicService;
    }

    @Override
    @PostMapping
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        log.info("{}", request);
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("/{id}")
    public Header<UserApiResponse> read(@PathVariable("id") Long id) {
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable("id") Long id) {
        return userApiLogicService.delete(id);
    }


}
