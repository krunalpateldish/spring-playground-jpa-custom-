package com.example;

import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by trainer2 on 4/12/17.
 */

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Lesson specificLesson(@PathVariable String id) {
        Long lessonId = Long.parseLong(id);
        return this.repository.findOne(lessonId);
    }

    @GetMapping("/find/{title}")
    public Lesson lessonByName(@PathVariable String title) {
        //String titleName =
        return this.repository.findByTitle(title);
    }

    @GetMapping("/find")
    public List<Lesson> lessonBWDates(@RequestParam Map<String, String> requestParams) throws ParseException {

        String reqDate1 = requestParams.get("date1");
        String reqDate2 =  requestParams.get("date2");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = formatter.parse(reqDate1);
        Date date3 = formatter.parse(reqDate1);

        java.sql.Date sql = new java.sql.Date(reqDate1);
        Date date2 = (Date) formatter.parse(reqDate2);

        return  this.repository.findByDeliveredOnBetween(date1, date2);
    }


    @PatchMapping("/{id}")
    public Lesson update1(@PathVariable String id, @RequestBody Lesson lesson) throws ParseException {
        Long lessonId = Long.parseLong(id);
        Lesson update = repository.findOne(lessonId);
        update.setId(lesson.getId());
        update.setTitle(lesson.getTitle());
        update.setDeliveredOn(lesson.getDeliveredOn());
        return  this.repository.save(update);
    }


    @DeleteMapping("/{id}")
    public Iterable<Lesson> delete(@PathVariable String id) {
        Long lessonId = Long.parseLong(id);
        repository.delete(lessonId);
        return this.repository.findAll();

    }

    @RequestMapping(consumes = "application/json")
    @PostMapping("")
    public Lesson  createLesson(@RequestBody Lesson lesson) {

        Lesson newLesson = new Lesson();
        newLesson.setId(lesson.getId());
        newLesson.setTitle(lesson.getTitle());
        newLesson.setDeliveredOn(lesson.getDeliveredOn());
        this.repository.save(newLesson);

        return newLesson;
    }

}