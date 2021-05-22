package com.fastcampus.study.controller.api;

import com.fastcampus.study.ifs.CrudInterface;
import com.fastcampus.study.model.network.Header;
import com.fastcampus.study.model.network.request.UserApiRequest;
import com.fastcampus.study.model.network.response.UserApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")

public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {


    @Override
    @PostMapping("") // /api/user
    public Header<UserApiResponse> create(@RequestBody UserApiRequest userApiRequest) {
        return null;
    }

    @Override
    @GetMapping("{id}") // /api/user/{id}
    public Header read(@PathVariable(name = "id") Long id) {
        return null;
    }

    @Override
    @PutMapping("") // /api/user
    public Header<UserApiResponse> update(@RequestBody UserApiRequest request) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")  // /api/user/{id}
    public Header<UserApiResponse> delete(@PathVariable Long id) {
        return null;
    }
}
