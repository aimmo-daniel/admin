package sj.jpa.admin.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sj.jpa.admin.controller.CrudController;
import sj.jpa.admin.model.entity.Partner;
import sj.jpa.admin.model.network.request.PartnerApiRequest;
import sj.jpa.admin.model.network.response.PartnerApiResponse;
import sj.jpa.admin.service.PartnerService;

@RequestMapping("/api/partner")
@RestController
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {

    private final PartnerService partnerService;

    @Autowired
    public PartnerApiController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }
}
