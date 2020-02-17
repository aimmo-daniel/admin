package sj.jpa.admin.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sj.jpa.admin.controller.CrudController;
import sj.jpa.admin.model.entity.Item;
import sj.jpa.admin.model.network.request.ItemApiRequest;
import sj.jpa.admin.model.network.response.ItemApiResponse;
import sj.jpa.admin.service.ItemService;

@Slf4j
@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {


    private final ItemService itemService;

    @Autowired
    public ItemApiController(ItemService itemService) {
        this.itemService = itemService;
    }

}
