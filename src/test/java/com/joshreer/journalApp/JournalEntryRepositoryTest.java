package com.joshreer.journalApp;

import com.joshreer.journalApp.Entity.JournalEntry;
import com.joshreer.journalApp.repository.JournalEntryRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class JournalEntryRepositoryTest {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Test
    public void testJournalEntryRepository() {
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setContent("Rain");
        journalEntry.setTitle("Monday");

        JournalEntry saved = journalEntryRepository.save(journalEntry);
        
        System.out.println("after saved");
        System.out.println(saved.getContent());
        System.out.println(saved.getTitle());

        assertNotNull(saved.getId());
        assertEquals("Rain", saved.getContent());
        assertEquals("Monday", saved.getTitle());

        JournalEntry fetched = journalEntryRepository.findById(saved.getId()).orElse(null);
        System.out.println("after fetched");
        System.out.println(fetched.getContent());
        System.out.println(fetched.getTitle());

        assertEquals("Rain", fetched.getContent());
        assertEquals("Monday", fetched.getTitle());
        assertNotNull(fetched);
        assertEquals("Rain", fetched.getContent());
    }
}
