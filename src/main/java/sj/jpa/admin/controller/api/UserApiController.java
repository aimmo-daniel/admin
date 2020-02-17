package sj.jpa.admin.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sj.jpa.admin.controller.CrudController;
import sj.jpa.admin.model.entity.User;
import sj.jpa.admin.model.network.Header;
import sj.jpa.admin.model.network.request.UserApiRequest;
import sj.jpa.admin.model.network.response.UserApiResponse;
import sj.jpa.admin.model.network.response.UserOrderInfoApiResponse;
import sj.jpa.admin.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable("id") Long id) {
        return userService.orderInfo(id);
    }

    @GetMapping
    public Header<List<UserApiResponse>> search(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 15) Pageable pageable) {
        return userService.search(pageable);
    }

}
