package com.fastcampus.study.model.network.response;

import com.fastcampus.study.model.entity.OrderGroup;
import com.fastcampus.study.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderInfoApiResponse {

    private UserApiResponse userApiResponse;
    private List<OrderGroupApiResponse> orderGroupApiResponseList;




}
