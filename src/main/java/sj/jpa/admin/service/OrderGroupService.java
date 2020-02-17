package sj.jpa.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sj.jpa.admin.interfaces.CrudInterface;
import sj.jpa.admin.model.entity.OrderGroup;
import sj.jpa.admin.model.network.Header;
import sj.jpa.admin.model.network.request.OrderGroupApiRequest;
import sj.jpa.admin.model.network.response.OrderGroupApiResponse;
import sj.jpa.admin.repository.OrderGroupRepository;
import sj.jpa.admin.repository.UserRepository;

import java.time.LocalDateTime;

@Service
public class OrderGroupService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

    private final UserRepository userRepository;

    @Autowired
    public OrderGroupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest body = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .status(body.getStatus())
                .orderType(body.getOrderType())
                .revAddress(body.getRevAddress())
                .revName(body.getRevName())
                .paymentType(body.getPaymentType())
                .totalPrice(body.getTotalPrice())
                .totalQuantity(body.getTotalQuantity())
                .orderAt(LocalDateTime.now())
                .user(userRepository.getOne(body.getUserId()))
                .build();

        OrderGroup newOrderGroup = baseRepository.save(orderGroup);

        return Header.OK(response(newOrderGroup));
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(orderGroup -> Header.OK(response(orderGroup)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(orderGroup -> {
                    if(!StringUtils.isEmpty(body.getStatus())) orderGroup.setStatus(body.getStatus());
                    if(!StringUtils.isEmpty(body.getOrderType())) orderGroup.setOrderType(body.getOrderType());
                    if(!StringUtils.isEmpty(body.getRevAddress())) orderGroup.setRevAddress(body.getRevAddress());
                    if(!StringUtils.isEmpty(body.getRevName())) orderGroup.setRevName(body.getRevName());
                    if(!StringUtils.isEmpty(body.getPaymentType())) orderGroup.setPaymentType(body.getPaymentType());
                    if(!StringUtils.isEmpty(body.getTotalPrice())) orderGroup.setTotalPrice(body.getTotalPrice());
                    if(!StringUtils.isEmpty(body.getTotalQuantity())) orderGroup.setTotalQuantity(body.getTotalQuantity());
                    if(!StringUtils.isEmpty(body.getOrderAt())) orderGroup.setOrderAt(body.getOrderAt());
                    if(!StringUtils.isEmpty(body.getUserId())) orderGroup.setUser(userRepository.getOne(body.getUserId()));

                    return orderGroup;
                })
                .map(newOrderGroup -> baseRepository.save(newOrderGroup))
                .map(updateOrderGroup -> Header.OK(response(updateOrderGroup)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        return baseRepository.findById(id)
                .map(orderGroup -> {
                    baseRepository.delete(orderGroup);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public OrderGroupApiResponse response(OrderGroup orderGroup) {
        OrderGroupApiResponse response = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();

        return response;
    }

}
