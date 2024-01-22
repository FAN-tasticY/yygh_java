package com.atguigu.yygh.hosp.repository;

import com.atguigu.yygh.hosp.bean.Actor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActorRepository extends MongoRepository<Actor,String> {
}
