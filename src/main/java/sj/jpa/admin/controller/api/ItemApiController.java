package sj.jpa.admin.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sj.jpa.admin.controller.CrudController;
import sj.jpa.admin.interfaces.CrudInterface;
import sj.jpa.admin.model.network.Header;
import sj.jpa.admin.model.network.request.ItemApiRequest;
import sj.jpa.admin.model.network.response.ItemApiResponse;
import sj.jpa.admin.service.ItemApiLogicService;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse> {

    private final ItemApiLogicService itemApiLogicService;

    @Autowired
    public ItemApiController(ItemApiLogicService itemApiLogicService) {
        this.itemApiLogicService = itemApiLogicService;
    }

    @PostConstruct
    public void init() {
        this.baseService = itemApiLogicService;
    }

}
