package sj.jpa.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sj.jpa.admin.model.entity.Partner;
import sj.jpa.admin.model.network.Header;
import sj.jpa.admin.model.network.request.PartnerApiRequest;
import sj.jpa.admin.model.network.response.PartnerApiResponse;
import sj.jpa.admin.repository.CategoryRepository;

@Service
public class PartnerService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {

    private final CategoryRepository categoryRepository;

    @Autowired
    public PartnerService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest body = request.getData();

        Partner partner = Partner.builder()
                .name(body.getName())
                .status(body.getStatus())
                .address(body.getAddress())
                .callCenter(body.getCallCenter())
                .partnerNumber(body.getPartnerNumber())
                .businessNumber(body.getBusinessNumber())
                .ceoName(body.getCeoName())
                .registeredAt(body.getRegisteredAt())
                .unregisteredAt(body.getUnregisteredAt())
                .category(categoryRepository.getOne(body.getCategoryId()))
                .build();

        Partner newPartner = baseRepository.save(partner);

        return response(newPartner);
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(partner -> response(partner))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(partner -> {
                    if(!StringUtils.isEmpty(body.getName())) partner.setName(body.getName());
                    if(!StringUtils.isEmpty(body.getStatus())) partner.setStatus(body.getStatus());
                    if(!StringUtils.isEmpty(body.getAddress())) partner.setAddress(body.getAddress());
                    if(!StringUtils.isEmpty(body.getCallCenter())) partner.setCallCenter(body.getCallCenter());
                    if(!StringUtils.isEmpty(body.getPartnerNumber())) partner.setPartnerNumber(body.getPartnerNumber());
                    if(!StringUtils.isEmpty(body.getBusinessNumber())) partner.setBusinessNumber(body.getBusinessNumber());
                    if(!StringUtils.isEmpty(body.getCeoName())) partner.setCeoName(body.getCeoName());
                    if(!StringUtils.isEmpty(body.getRegisteredAt())) partner.setRegisteredAt(body.getRegisteredAt());
                    if(!StringUtils.isEmpty(body.getUnregisteredAt())) partner.setUnregisteredAt(body.getUnregisteredAt());
                    if(!StringUtils.isEmpty(body.getCategoryId())) partner.setCategory(categoryRepository.getOne(body.getCategoryId()));

                    return partner;
                })
                .map(newPartner -> baseRepository.save(newPartner))
                .map(updatePartner -> response(updatePartner))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(partner -> {
                    baseRepository.delete(partner);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<PartnerApiResponse> response(Partner partner) {
        PartnerApiResponse body = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .unregisteredAt(partner.getUnregisteredAt())
                .categoryId(partner.getCategory().getId())
                .build();
        return Header.OK(body);
    }

}
