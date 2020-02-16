package sj.jpa.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sj.jpa.admin.interfaces.CrudInterface;
import sj.jpa.admin.model.entity.Item;
import sj.jpa.admin.model.network.Header;
import sj.jpa.admin.model.network.request.ItemApiRequest;
import sj.jpa.admin.model.network.response.ItemApiResponse;
import sj.jpa.admin.repository.ItemRepository;
import sj.jpa.admin.repository.PartnerRepository;

import java.time.LocalDateTime;

@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    private final PartnerRepository partnerRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemApiLogicService(PartnerRepository partnerRepository, ItemRepository itemRepository) {
        this.partnerRepository = partnerRepository;
        this.itemRepository = itemRepository;
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

        Item newItem = itemRepository.save(item);

        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return itemRepository.findById(id)
                .map(item -> response(item))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest body = request.getData();

        return itemRepository.findById(body.getId())
                .map(item -> {
                    item.setStatus(body.getStatus())
                            .setName(body.getName())
                            .setTitle(body.getContent())
                            .setContent(body.getContent())
                            .setPrice(body.getPrice())
                            .setBrandName(body.getBrandName())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt())
                            .setPartner(partnerRepository.getOne(body.getPartnerId()));

                    return item;
                })
                .map(newItem -> itemRepository.save(newItem))
                .map(updateItem -> response(updateItem))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return itemRepository.findById(id)
                .map(item -> {
                    itemRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<ItemApiResponse> response(Item item) {
        ItemApiResponse body = ItemApiResponse.builder()
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

        return Header.OK(body);
    }

}
