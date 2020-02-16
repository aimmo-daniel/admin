package sj.jpa.admin.repository;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import sj.jpa.admin.model.entity.User;
import sj.jpa.admin.model.enumclass.UserStatus;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        String account = "Test03";
        String password = "Test03";
        UserStatus status = UserStatus.REGISTERED;
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .phoneNumber(phoneNumber)
                .registeredAt(registeredAt)
                .build();

        User newUser = userRepository.save(user);

        assertThat(newUser).isNotNull();
    }

    @Test
    @Transactional
    public void read() {
        String phoneNumber = "010-1111-2222";
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc(phoneNumber);

        user.getOrderGroupList().stream().forEach(orderGroup -> {

            System.out.println("===========주문묶음=================");
            System.out.println("수령인 : " + orderGroup.getRevName());
            System.out.println("수령지 : " + orderGroup.getRevAddress());
            System.out.println("총금액 : " + orderGroup.getTotalPrice());
            System.out.println("총수량 : " + orderGroup.getTotalQuantity());

            System.out.println("===========주문상세=================");
            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문의 상태 : " + orderDetail.getStatus());
                System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());
            });
        });

        assertThat(user).isNotNull();
    }

    @Test
    public void updated() {
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            selectUser.setAccount("aaaa");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("이끄");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    public void delete() {
        Optional<User> user = userRepository.findById(1L);

        assertThat(user.isPresent()).isTrue();

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(1L);

        assertThat(deleteUser.isPresent()).isFalse();
    }

}
