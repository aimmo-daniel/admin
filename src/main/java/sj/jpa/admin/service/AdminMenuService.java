package sj.jpa.admin.service;

import org.springframework.stereotype.Service;
import sj.jpa.admin.model.front.AdminMenu;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminMenuService {

    public List<AdminMenu> getAdminMenu(){

        return Arrays.asList(
                AdminMenu.builder().title("고객 관리").url("/pages/user").code("user").build()
        );

    }

}
