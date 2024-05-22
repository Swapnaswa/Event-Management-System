package com.emsbackend.springboots.controller;

import com.emsbackend.springboots.Repository.EventRepository;
import com.emsbackend.springboots.execution.ResourceNotFoundException;
import com.emsbackend.springboots.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    @GetMapping
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
    // build create employee REST API
    @PostMapping
    public Event createEmployee(@RequestBody Event employee) {
        return eventRepository.save(employee);
    }
    // build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Event> getEmployeeById(@PathVariable  long id){
        Event employee = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return ResponseEntity.ok(employee);
    }
    // Update an event
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable long id, @RequestBody Event eventDetails) {
        Event updateEvent = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        updateEvent.setTitle(eventDetails.getTitle());
        updateEvent.setDescription(eventDetails.getDescription());
        updateEvent.setDateTime(eventDetails.getDateTime());
        updateEvent.setLocation(eventDetails.getLocation());

        eventRepository.save(updateEvent);

        return ResponseEntity.ok(updateEvent);
    }
    // build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

        Event employee = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        eventRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
