package com.lawsystem.lawserver.repo;

import com.lawsystem.lawserver.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {


    boolean existsByPhoneOrName(String phone, String name);
    List<Member> findAll();
    List<Member> findAllByNameContaining(String name);
}
