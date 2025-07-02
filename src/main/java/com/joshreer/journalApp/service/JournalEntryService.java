package com.joshreer.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joshreer.journalApp.Entity.JournalEntry;
import com.joshreer.journalApp.repository.JournalEntryRepository;

@Component
public class JournalEntryService 
{
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public JournalEntry saveEntry(JournalEntry journalEntry)
    {
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