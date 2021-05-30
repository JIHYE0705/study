package com.fastcampus.study.controller.api;

import com.fastcampus.study.controller.CrudController;
import com.fastcampus.study.model.entity.Partner;
import com.fastcampus.study.model.network.request.PartnerApiRequest;
import com.fastcampus.study.model.network.response.PartnerApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {



}
