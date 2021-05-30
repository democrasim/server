package com.lawsystem.lawserver.repo;

import java.util.List;

import com.lawsystem.lawserver.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {


    boolean existsByPhoneOrName(String phone, String name);

    Member findByPhone(String phone);

    List<Member> findAll();

    List<Member> findAllByNameContaining(String name);

    List<Member> findAllByRegistered(boolean registered);
}
