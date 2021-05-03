package com.lawsystem.lawserver.controller;

import com.lawsystem.lawserver.dto.LawDto;
import com.lawsystem.lawserver.model.Law;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class LawControllerTest {

    @Autowired
    LawController lawController;

    @Test
    void getAllPassedLaws() {
        assertNotNull(lawController);
        List<LawDto> list = lawController.getAllPassedLaws();

        assertNotNull(list);
        assertFalse(list.isEmpty());
        list.forEach(Assertions::assertNotNull);

    }

    @Test
    void testGetAllPassedLaws() {
    }

    @Test
    void proposeBan() {
    }

    @Test
    void proposeFact() {
    }

    @BeforeEach
    public void before() {

    }

    @Test
    void vote()
    {
    }
}