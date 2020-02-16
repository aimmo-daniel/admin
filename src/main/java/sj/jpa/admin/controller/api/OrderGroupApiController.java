package sj.jpa.admin.controller.api;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sj.jpa.admin.controller.CrudController;
import sj.jpa.admin.interfaces.CrudInterface;
import sj.jpa.admin.model.network.Header;
import sj.jpa.admin.model.network.request.OrderGroupApiRequest;
import sj.jpa.admin.model.network.response.OrderGroupApiResponse;
import sj.jpa.admin.service.OrderGroupApiLogicService;

import javax.annotation.PostConstruct;

@RequestMapping("/api/orderGroup")
@RestController
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse> {

    private final OrderGroupApiLogicService orderGroupApiLogicService;

    @Autowired
    public OrderGroupApiController(OrderGroupApiLogicService orderGroupApiLogicService) {
        this.orderGroupApiLogicService = orderGroupApiLogicService;
    }

    @PostConstruct
    private void init() {
        this.baseService = orderGroupApiLogicService;
    }

}
