package sj.jpa.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sj.jpa.admin.interfaces.CrudInterface;
import sj.jpa.admin.model.entity.OrderGroup;
import sj.jpa.admin.model.network.Header;
import sj.jpa.admin.model.network.request.OrderGroupApiRequest;
import sj.jpa.admin.model.network.response.OrderGroupApiResponse;
import sj.jpa.admin.repository.OrderGroupRepository;
import sj.jpa.admin.repository.UserRepository;

import java.time.LocalDateTime;

@Service
public class OrderGroupApiLogicService implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    private final OrderGroupRepository orderGroupRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderGroupApiLogicService(OrderGroupRepository orderGroupRepository, UserRepository userRepository) {
        this.orderGroupRepository = orderGroupRepository;
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

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);

        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return orderGroupRepository.findById(id)
                .map(orderGroup -> response(orderGroup))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest body = request.getData();

        return orderGroupRepository.findById(body.getId())
                .map(orderGroup -> {
                    orderGroup.setStatus(body.getStatus())
                            .setOrderType(body.getOrderType())
                            .setRevAddress(body.getRevAddress())
                            .setRevName(body.getRevName())
                            .setPaymentType(body.getPaymentType())
                            .setTotalPrice(body.getTotalPrice())
                            .setTotalQuantity(body.getTotalQuantity())
                            .setOrderAt(body.getOrderAt())
                            .setUser(userRepository.getOne(body.getUserId()));

                    return orderGroup;
                })
                .map(newOrderGroup -> orderGroupRepository.save(newOrderGroup))
                .map(updateOrderGroup -> response(updateOrderGroup))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        return orderGroupRepository.findById(id)
                .map(orderGroup -> {
                    orderGroupRepository.delete(orderGroup);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<OrderGroupApiResponse> response(OrderGroup orderGroup) {
        OrderGroupApiResponse body = OrderGroupApiResponse.builder()
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

        return Header.OK(body);
    }

}
