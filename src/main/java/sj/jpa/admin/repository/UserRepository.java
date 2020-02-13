package sj.jpa.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.jpa.admin.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);

}
