package sj.jpa.admin.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sj.jpa.admin.controller.CrudController;
import sj.jpa.admin.model.entity.OrderGroup;
import sj.jpa.admin.model.network.request.OrderGroupApiRequest;
import sj.jpa.admin.model.network.response.OrderGroupApiResponse;
import sj.jpa.admin.service.OrderGroupService;

@RequestMapping("/api/orderGroup")
@RestController
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

    private final OrderGroupService orderGroupService;

    @Autowired
    public OrderGroupApiController(OrderGroupService orderGroupService) {
        this.orderGroupService = orderGroupService;
    }
}
