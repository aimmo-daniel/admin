package sj.jpa.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sj.jpa.admin.model.entity.Item;
import sj.jpa.admin.model.network.Header;
import sj.jpa.admin.model.network.request.ItemApiRequest;
import sj.jpa.admin.model.network.response.ItemApiResponse;
import sj.jpa.admin.repository.PartnerRepository;

import java.time.LocalDateTime;

@Service
public class ItemService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    private final PartnerRepository partnerRepository;

    @Autowired
    public ItemService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        ItemApiRequest body = request.getData();

        Item item = Item.builder()
                .status(body.getStatus())
                .name(body.getName())
                .title(body.getTitle())
                .content(body.getContent())
                .price(body.getPrice())
                .brandName(body.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getOne(body.getPartnerId()))
                .build();

        Item newItem = baseRepository.save(item);

        return Header.OK(response(newItem));
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(item -> Header.OK(response(item)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(item -> {
                    if(!StringUtils.isEmpty(body.getStatus())) item.setStatus(body.getStatus());
                    if(!StringUtils.isEmpty(body.getName())) item.setName(body.getName());
                    if(!StringUtils.isEmpty(body.getTitle())) item.setTitle(body.getContent());
                    if(!StringUtils.isEmpty(body.getContent())) item.setContent(body.getContent());
                    if(!StringUtils.isEmpty(body.getPrice())) item.setPrice(body.getPrice());
                    if(!StringUtils.isEmpty(body.getRegisteredAt())) item.setRegisteredAt(body.getRegisteredAt());
                    if(!StringUtils.isEmpty(body.getUnregisteredAt())) item.setUnregisteredAt(body.getUnregisteredAt());
                    if(!StringUtils.isEmpty(body.getPartnerId())) item.setPartner(partnerRepository.getOne(body.getPartnerId()));

                    return item;
                })
                .map(newItem -> baseRepository.save(newItem))
                .map(updateItem -> Header.OK(response(updateItem)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(item -> {
                    baseRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public ItemApiResponse response(Item item) {
        ItemApiResponse response = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();

        return response;
    }

}
