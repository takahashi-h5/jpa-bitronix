package com.example.demo.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.primary.Empuser;

@Repository
public interface PrimaryRepository extends JpaRepository<Empuser, String>{
  @Query(value="select * from empuser " ,nativeQuery=true)
  public Empuser findBySample();
  
  @Modifying
  @Query(value="update empuser set name = 'a'",nativeQuery=true)
  public void updateBySample();
}
