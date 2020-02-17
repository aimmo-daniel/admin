package sj.jpa.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sj.jpa.admin.model.entity.OrderGroup;
import sj.jpa.admin.model.entity.User;
import sj.jpa.admin.model.network.Header;
import sj.jpa.admin.model.network.request.UserApiRequest;
import sj.jpa.admin.model.network.response.ItemApiResponse;
import sj.jpa.admin.model.network.response.OrderGroupApiResponse;
import sj.jpa.admin.model.network.response.UserApiResponse;
import sj.jpa.admin.model.network.response.UserOrderInfoApiResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService<UserApiRequest, UserApiResponse, User> {

    private final OrderGroupService orderGroupService;
    private final ItemService itemService;

    @Autowired
    public UserService(OrderGroupService orderGroupService, ItemService itemService) {
        this.orderGroupService = orderGroupService;
        this.itemService = itemService;
    }

    public Header<List<UserApiResponse>> search(Pageable pageable) {
        Page<User> users = baseRepository.findAll(pageable);

        List<UserApiResponse> userApiResponseList = users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());

        return Header.OK(userApiResponseList);
    }


    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {
        // 1. 사용자를 찾아오고
        User user = baseRepository.getOne(id);
        UserApiResponse userApiResponse = response(user);

        // 2. 주문그룹을 가져오고
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse = orderGroupService.response(orderGroup);

                    // 3. 해당그룹의 아이템들을 찾아서 지정
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(detail -> detail.getItem())
                            .map(item -> itemService.response(item))
                            .collect(Collectors.toList());

                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                    return orderGroupApiResponse;
                })
                .collect(Collectors.toList());

        // 4. 유저에 그룹내역 지정
        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);

        // 5. 유저 주문정보에 담아서 리턴
       UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();

        return Header.OK(userOrderInfoApiResponse);
    }

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(userApiRequest.getStatus())
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = baseRepository.save(user);

        // 3. 생성된 데이터 -> userApiResponse return

        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(user -> Header.OK(response(user)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }


    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        // 1. data
        UserApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(user -> {
                    if (!StringUtils.isEmpty(body.getAccount())) user.setAccount(body.getAccount());
                    if (!StringUtils.isEmpty(body.getPassword())) user.setPassword(body.getPassword());
                    if (!StringUtils.isEmpty(body.getStatus())) user.setStatus(body.getStatus());
                    if (!StringUtils.isEmpty(body.getPhoneNumber())) user.setPhoneNumber(body.getPhoneNumber());
                    if (!StringUtils.isEmpty(body.getEmail())) user.setEmail(body.getEmail());
                    if (!StringUtils.isEmpty(body.getRegisteredAt())) user.setRegisteredAt(body.getRegisteredAt());
                    if (!StringUtils.isEmpty(body.getUnregisteredAt()))
                        user.setUnregisteredAt(body.getUnregisteredAt());

                    return user;
                })
                .map(newUser -> baseRepository.save(newUser)) // update -> newUser
                .map(updateUser -> Header.OK(response(updateUser)))            // userApiResponse
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(user -> {
                    baseRepository.delete(user);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }


    private UserApiResponse response(User user) {
        // user -> userApiResponse

        UserApiResponse response = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // TODO : 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return
        return response;
    }

}
