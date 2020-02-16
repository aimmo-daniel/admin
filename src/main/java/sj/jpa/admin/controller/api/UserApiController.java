package sj.jpa.admin.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sj.jpa.admin.controller.CrudController;
import sj.jpa.admin.interfaces.CrudInterface;
import sj.jpa.admin.model.network.Header;
import sj.jpa.admin.model.network.request.UserApiRequest;
import sj.jpa.admin.model.network.response.UserApiResponse;
import sj.jpa.admin.service.UserApiLogicService;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse> {

    private UserApiLogicService userApiLogicService;

    @Autowired
    public UserApiController(UserApiLogicService userApiLogicService) {
        this.userApiLogicService = userApiLogicService;
    }

    @PostConstruct
    public void init() {
        this.baseService = userApiLogicService;
    }

}
