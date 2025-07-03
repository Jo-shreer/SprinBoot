package com.joshreer.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joshreer.journalApp.Controller.JournalEntryControllerV2;
import com.joshreer.journalApp.Entity.JournalEntry;
import com.joshreer.journalApp.repository.JournalEntryRepository;

@Component
public class JournalEntryService 
{
    private static final Logger logger = LoggerFactory.getLogger(JournalEntryControllerV2.class);
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public JournalEntry saveEntry(JournalEntry journalEntry)
    {
        logger.info( "calling saveEntry");
        return journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll()
    {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id)
    {
        return journalEntryRepository.findById(id);
    }

    public Optional<JournalEntry> deleteById(ObjectId id)
    {
       return  journalEntryRepository.findById(id);
    }


}