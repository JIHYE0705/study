package com.fastcampus.study.controller.api;

import com.fastcampus.study.controller.CrudController;
import com.fastcampus.study.ifs.CrudInterface;
import com.fastcampus.study.model.entity.OrderGroup;
import com.fastcampus.study.model.network.Header;
import com.fastcampus.study.model.network.request.OrderGroupApiRequest;
import com.fastcampus.study.model.network.response.OrderGroupApiResponse;
import com.fastcampus.study.service.OrderGroupApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {



}
