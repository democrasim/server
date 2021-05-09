package com.lawsystem.lawserver.repo;

import com.lawsystem.lawserver.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {


    boolean existsByPhoneOrName(String phone, String name);
    Member findByPhone(String phone);
    List<Member> findAll();
    List<Member> findAllByNameContaining(String name);
    List<Member> findAllByRegistered(boolean registered);
}
