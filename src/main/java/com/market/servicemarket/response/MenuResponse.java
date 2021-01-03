package com.market.servicemarket.response;

import com.market.servicemarket.dto.Menu;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MenuResponse {

    List<Menu> menuList;


}
