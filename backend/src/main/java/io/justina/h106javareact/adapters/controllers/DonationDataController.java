package io.justina.h106javareact.adapters.controllers;

import io.justina.h106javareact.application.services.DonationDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crossDonation")
@RequiredArgsConstructor
public class DonationDataController {
    private final DonationDataService crossDonationDataService;



}
