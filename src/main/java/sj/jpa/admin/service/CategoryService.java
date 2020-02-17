package sj.jpa.admin.service;

import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import sj.jpa.admin.model.entity.Category;
import sj.jpa.admin.model.network.Header;
import sj.jpa.admin.model.network.request.CategoryApiRequest;
import sj.jpa.admin.model.network.response.CategoryApiResponse;
import sj.jpa.admin.model.network.response.UserOrderInfoApiResponse;

import java.util.List;

public class CategoryService extends BaseService<CategoryApiRequest, CategoryApiResponse, Category> {

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        CategoryApiRequest body = request.getData();

        Category category = Category.builder()
                .title(body.getTitle())
                .type(body.getType())
                .createdAt(body.getCreatedAt())
                .createdBy(body.getCreatedBy())
                .updatedAt(body.getUpdatedAt())
                .updatedBy(body.getUpdatedBy())
                .build();

        Category newCategory = baseRepository.save(category);

        return response(newCategory);
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(category -> response(category))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
        CategoryApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(category -> {
                    if(!StringUtils.isEmpty(body.getTitle())) category.setTitle(body.getTitle());
                    if(!StringUtils.isEmpty(body.getType())) category.setType(body.getType());
                    if(!StringUtils.isEmpty(body.getCreatedAt())) category.setCreatedAt(body.getCreatedAt());
                    if(!StringUtils.isEmpty(body.getCreatedBy())) category.setCreatedBy(body.getCreatedBy());
                    if(!StringUtils.isEmpty(body.getUpdatedAt())) category.setUpdatedAt(body.getUpdatedAt());
                    if(!StringUtils.isEmpty(body.getUpdatedBy())) category.setUpdatedBy(body.getUpdatedBy());

                    return category;
                })
                .map(newCategory -> baseRepository.save(newCategory))
                .map(updatedCategory -> response(updatedCategory))
                .orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(category -> {
                    baseRepository.delete(category);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<CategoryApiResponse> response(Category category) {
        CategoryApiResponse body = CategoryApiResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .type(category.getType())
                .createdAt(category.getCreatedAt())
                .createdBy(category.getCreatedBy())
                .updatedAt(category.getUpdatedAt())
                .updatedBy(category.getUpdatedBy())
                .build();

        return Header.OK(body);
    }

}
