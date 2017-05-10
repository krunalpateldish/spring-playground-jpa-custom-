package com.example;

import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

/**
 * Created by trainer2 on 4/12/17.
 */

    public interface LessonRepository extends CrudRepository<Lesson, Long> {


    Lesson findByTitle(String title);


    List<Lesson> findByDeliveredOnBetween(Date start, Date end);


    }

