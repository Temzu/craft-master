package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.entities.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserInfoDto {
    private String login;
    private String name;
    private Float rating;
    private Integer numRatings;
    private List<CredentialDto> credentials;

    public UserInfoDto(User user) {
        this.login = user.getLogin();
        this.name = user.getName();
        this.rating = user.getRating();
        this.numRatings = user.getNumRatings();
        this.credentials =user.getCredentials().stream().map(CredentialDto::new).collect(Collectors.toList());
    }
}
