package com.newvatika.backend.service;

import com.newvatika.backend.dto.PasswordChangeRequest;
import com.newvatika.backend.dto.SettingsDto;
import com.newvatika.backend.entity.RestaurantSettings;
import com.newvatika.backend.entity.User;
import com.newvatika.backend.repository.RestaurantSettingsRepository;
import com.newvatika.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingsService {

    private final RestaurantSettingsRepository settingsRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SettingsDto getSettings() {

        RestaurantSettings settings = settingsRepository.findAll()
                .stream()
                .findFirst()
                .orElseGet(() -> {

                    RestaurantSettings s = RestaurantSettings.builder()
                            .restaurantName("New Vatika Cafe")
                            .ownerName("")
                            .phone("")
                            .email("")
                            .gst("")
                            .address("")
                            .openingTime("09:00")
                            .closingTime("23:00")
                            .cashEnabled(true)
                            .upiEnabled(true)
                            .cardEnabled(false)
                            .autoAcceptOrders(false)
                            .allowCustomerNotes(true)
                            .pickupEnabled(true)
                            .deliveryEnabled(true)
                            .build();

                    return settingsRepository.save(s);
                });

        return SettingsDto.builder()
                .restaurantName(settings.getRestaurantName())
                .ownerName(settings.getOwnerName())
                .phone(settings.getPhone())
                .email(settings.getEmail())
                .gst(settings.getGst())
                .address(settings.getAddress())
                .openingTime(settings.getOpeningTime())
                .closingTime(settings.getClosingTime())
                .cashEnabled(settings.getCashEnabled())
                .upiEnabled(settings.getUpiEnabled())
                .cardEnabled(settings.getCardEnabled())
                .autoAcceptOrders(settings.getAutoAcceptOrders())
                .allowCustomerNotes(settings.getAllowCustomerNotes())
                .pickupEnabled(settings.getPickupEnabled())
                .deliveryEnabled(settings.getDeliveryEnabled())
                .build();
    }

    public SettingsDto updateSettings(SettingsDto dto) {

        RestaurantSettings settings = settingsRepository.findAll()
                .stream()
                .findFirst()
                .orElse(new RestaurantSettings());

        settings.setRestaurantName(dto.getRestaurantName());
        settings.setOwnerName(dto.getOwnerName());
        settings.setPhone(dto.getPhone());
        settings.setEmail(dto.getEmail());
        settings.setGst(dto.getGst());
        settings.setAddress(dto.getAddress());
        settings.setOpeningTime(dto.getOpeningTime());
        settings.setClosingTime(dto.getClosingTime());

        settings.setCashEnabled(dto.getCashEnabled());
        settings.setUpiEnabled(dto.getUpiEnabled());
        settings.setCardEnabled(dto.getCardEnabled());

        settings.setAutoAcceptOrders(dto.getAutoAcceptOrders());
        settings.setAllowCustomerNotes(dto.getAllowCustomerNotes());
        settings.setPickupEnabled(dto.getPickupEnabled());
        settings.setDeliveryEnabled(dto.getDeliveryEnabled());

        settingsRepository.save(settings);

        return dto;
    }

    public String changePassword(String username,
                                 PasswordChangeRequest request) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!passwordEncoder.matches(
                request.getCurrentPassword(),
                user.getPassword())) {

            throw new RuntimeException("Current password is incorrect.");
        }

        if (!request.getNewPassword()
                .equals(request.getConfirmPassword())) {

            throw new RuntimeException("Passwords do not match.");
        }

        user.setPassword(
                passwordEncoder.encode(request.getNewPassword())
        );

        userRepository.save(user);

        return "Password Updated Successfully";
    }

}