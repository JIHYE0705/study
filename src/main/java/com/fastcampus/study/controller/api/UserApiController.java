package com.fastcampus.study.controller.api;

import com.fastcampus.study.controller.CrudController;
import com.fastcampus.study.model.entity.User;
import com.fastcampus.study.model.network.Header;
import com.fastcampus.study.model.network.request.UserApiRequest;
import com.fastcampus.study.model.network.response.UserApiResponse;
import com.fastcampus.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")

public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {


    @Autowired
    private UserApiLogicService userApiLogicService;

    @GetMapping("")
    public Header<List<UserApiResponse>> search(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10) Pageable pageable) {
        log.info("{}", pageable);

        return userApiLogicService.search(pageable);
    }
}
