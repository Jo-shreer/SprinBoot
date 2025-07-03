package com.joshreer.journalApp.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.joshreer.journalApp.Entity.*;
import com.joshreer.journalApp.service.JournalEntryService;

import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2
{
    @Autowired
    private JournalEntryService journalEntryService;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryControllerV2.class);

    @PostMapping()
    public ResponseEntity<JournalEntry> CreateJournalEntry(@RequestBody JournalEntry myEntity) 
    {
       try
       {
            myEntity.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntity);
            logger.info("Successful CreateJournalEntry");
            return new ResponseEntity<>(myEntity, HttpStatus.CREATED);
       }
       catch(Exception e)
       {
            logger.error("Exception occured while CreateJournalEntry");
            return new ResponseEntity<>(myEntity, HttpStatus.BAD_REQUEST);
       }
    } 

   @GetMapping()
   public ResponseEntity<?> getJournalEntryAll()
   {
        List<JournalEntry>JournalEntries  = journalEntryService.getAll();
        if(JournalEntries != null && !JournalEntries.isEmpty())
            return new ResponseEntity<>(JournalEntries, HttpStatus.OK);
        else
             return new ResponseEntity<>(JournalEntries, HttpStatus.NOT_FOUND);
   }

   @GetMapping("id/{myid}")
   public ResponseEntity<JournalEntry> getJournalEntryEntryById(@PathVariable ObjectId myid)
   {
        Optional<JournalEntry> journalEntryournalEntry = journalEntryService.findById(myid);
        if(journalEntryournalEntry.isPresent())
        {
            return new ResponseEntity<>(journalEntryournalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   @DeleteMapping("id/{myId}")
   public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId)
   {
         journalEntryService.deleteById(myId);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

   @PutMapping("id/{myId}")
   public ResponseEntity<JournalEntry> updateJournalEntryById(@RequestBody ObjectId myId , JournalEntry newEntry)
   {
        Optional<JournalEntry> OptionalOldJournalEntry = journalEntryService.findById(myId);
        if(OptionalOldJournalEntry.isPresent())
        {
            JournalEntry oldJournalEntry = OptionalOldJournalEntry.get();
            oldJournalEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldJournalEntry.getTitle());
            oldJournalEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldJournalEntry.getContent());
            newEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(oldJournalEntry);
            return new ResponseEntity<>(oldJournalEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }








    // private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    // @GetMapping
    // public List<JournalEntry> getAll()
    // {
    //     return new ArrayList<>(journalEntries.values());
    // }

    // @PostMapping()
    // public boolean postMethodName(@RequestBody JournalEntry myEntity) 
    // {
    //     journalEntries.put(myEntity.getId(),myEntity);
    //     return true;
    // }  
    
    // @GetMapping("id/{myId}")
    // public JournalEntry getJournalEntryById(@PathVariable long myId) 
    // {
    //     return journalEntries.get(myId);
    // }

    // @DeleteMapping("/id/{myId}")
    // public JournalEntry deleteEntryById(@PathVariable long myId)
    // {
    //     return journalEntries.remove(myId);
    // }

    // @PutMapping("id/{myId}")
    // public JournalEntry putMethodName(@PathVariable long myId, @RequestBody JournalEntry entity) 
    // {
    //     return journalEntries.put(myId, entity);
    // }
    
}
