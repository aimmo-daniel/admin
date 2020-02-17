package sj.jpa.admin.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import sj.jpa.admin.controller.CrudController;
import sj.jpa.admin.model.entity.Category;
import sj.jpa.admin.model.network.request.CategoryApiRequest;
import sj.jpa.admin.model.network.response.CategoryApiResponse;
import sj.jpa.admin.service.CategoryService;

public class CategoryApiController extends CrudController<CategoryApiRequest, CategoryApiResponse, Category> {

    private final CategoryService categoryService;

    @Autowired
    public CategoryApiController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
