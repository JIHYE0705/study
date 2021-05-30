package com.fastcampus.study.controller.api;

import com.fastcampus.study.controller.CrudController;
import com.fastcampus.study.ifs.CrudInterface;
import com.fastcampus.study.model.network.Header;
import com.fastcampus.study.model.network.request.OrderDetailApiRequest;
import com.fastcampus.study.model.network.response.OrderDetailApiResponse;
import com.fastcampus.study.repository.OrderDetailRepository;
import com.fastcampus.study.service.OrderDetailApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailApiController extends CrudController<OrderDetailApiRequest, OrderDetailApiResponse> {

    @Autowired
    OrderDetailApiLogicService orderDetailApiLogicService;

    @PostConstruct
    protected void init() {
        this.baseService = orderDetailApiLogicService;
    }
}
