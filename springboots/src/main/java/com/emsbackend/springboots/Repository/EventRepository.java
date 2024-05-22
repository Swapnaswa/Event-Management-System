package com.emsbackend.springboots.Repository;

import com.emsbackend.springboots.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
