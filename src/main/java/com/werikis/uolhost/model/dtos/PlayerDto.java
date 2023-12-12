package com.werikis.uolhost.model.dtos;

import com.werikis.uolhost.model.GroupType;

public record PlayerDto(String name, String email, String phoneNumber, GroupType groupType) {
    
}
