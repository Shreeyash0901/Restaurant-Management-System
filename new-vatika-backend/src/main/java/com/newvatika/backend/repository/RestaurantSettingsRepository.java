package com.newvatika.backend.repository;

import com.newvatika.backend.entity.RestaurantSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantSettingsRepository
        extends JpaRepository<RestaurantSettings, Long> {
}