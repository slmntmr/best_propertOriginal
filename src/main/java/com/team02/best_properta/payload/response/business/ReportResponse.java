package com.team02.best_properta.payload.response.business;

import com.team02.best_properta.entity.concretes.business.Advert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportResponse {
    // Getters and Setters
    private int activatedAdvert;
    private int advertTypes;
    private int categories;
    private long tourRequests;
    private long users;



}