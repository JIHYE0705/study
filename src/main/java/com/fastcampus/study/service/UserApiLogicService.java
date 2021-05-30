package com.fastcampus.study.service;

import com.fastcampus.study.model.entity.User;
import com.fastcampus.study.model.enumclass.UserStatus;
import com.fastcampus.study.model.network.Header;
import com.fastcampus.study.model.network.Pagination;
import com.fastcampus.study.model.network.request.UserApiRequest;
import com.fastcampus.study.model.network.response.UserApiResponse;
import org.apache.catalina.util.ErrorPageSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse,User> {



    // 1. request data
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse return



    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = baseRepository.save(user);

        // 3. 생성된 데이터 -> userApiResponse return



        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // id -> repository getOne, getById
        // user -> userApiResponse return

        return baseRepository.findById(id)
                .map(user -> response(user))
                //.map(userApiResponse -> Header.OK(userApiResponse))
                .map(Header::OK)
                .orElseGet(
                        ()->Header.ERROR("데이터 없음")
                );

    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. date
        UserApiRequest userApiRequest = request.getData();

        // 2. id -> user 데이터를 찾고
        Optional<User> optional = baseRepository.findById(userApiRequest.getId());

        return optional.map(user -> {

            // 3. data -> update
            // id

            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt())
                    ;

            return user;

            // 4. userApiResponse

        })
                .map(user -> baseRepository.save(user))     // update -> newUser
                .map(updateUser -> response(updateUser))    // userApiResponse
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {
        // 1. id -> repository -> user

        Optional<User> optional = baseRepository.findById(id);

        // 2. repository -> delete

       return optional.map(user -> {
            baseRepository.delete(user);

            return Header.OK();

        })
                .orElseGet(()->Header.ERROR("데이터 없음"));

        // 3. response return

    }

    private UserApiResponse response(User user) {
        // user -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())   // todo 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return

        return userApiResponse;
    }



    public Header<List<UserApiResponse>> search(Pageable pageable) {

        Page<User> users = baseRepository.findAll(pageable);

        List<UserApiResponse> userApiResponseList = users.stream()
              .map(user -> response(user))
              .collect(Collectors.toList());

      // List<UserApiResponse>
      // Header<List<UserApiResponse>>

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();

      return Header.OK(userApiResponseList,pagination);


    }
}
