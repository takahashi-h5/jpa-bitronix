package com.example.demo.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.secondary.EmpUser2;
 
@Repository
public interface SecondaryRepository  extends JpaRepository<EmpUser2, String>{
  @Query(value="select * from empuser" ,nativeQuery=true)
  public EmpUser2 findBySample();
  

  @Modifying
  @Query(value="update empuser set name = '123456'" ,nativeQuery=true)
  public void updateBySample();
}
