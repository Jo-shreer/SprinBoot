package com.joshreer.journalApp;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.joshreer.journalApp.Entity.JournalEntry;
import com.joshreer.journalApp.repository.JournalEntryRepository;
import com.joshreer.journalApp.service.JournalEntryService;

@ExtendWith(MockitoExtension.class)
public class JournalEntryServiceTest 
{
    @Mock
    JournalEntryRepository journalEntryRepository;

    @InjectMocks
    JournalEntryService journalEntryService;

    @Test
    public void testAddEntries()
    {
        JournalEntry journalEntry = new JournalEntry();
        ObjectId id = new ObjectId();
        journalEntry.setContent("abc");
        journalEntry.setTitle("sbr");
        journalEntry.setId(id);
        
        Mockito.when(journalEntryService.saveEntry(journalEntry)).thenReturn(journalEntry);
        JournalEntry saved = journalEntryService.saveEntry(journalEntry);
        assertNotNull(saved);

        Mockito.when(journalEntryService.findById(id)).thenReturn(Optional.of(journalEntry));
        Optional<JournalEntry> fetched = journalEntryService.findById(id);
        assertNotNull(fetched);
    }
}
