package sj.jpa.admin.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sj.jpa.admin.service.AdminMenuService;

@Controller
@RequestMapping("/pages")
public class PageController {

    @Autowired
    private AdminMenuService adminMenuService;

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("/pages/main")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main")
                ;
    }

    @GetMapping("/user")
    public ModelAndView user() {
        return new ModelAndView("/pages/user")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "user")
                ;
    }
}
