package com.joshreer.journalApp.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.joshreer.journalApp.Entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/_journal")
public class JournalEntryController 
{
    private Map<ObjectId, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll()
    {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping()
    public boolean postMethodName(@RequestBody JournalEntry myEntity) 
    {
        journalEntries.put(myEntity.getId(),myEntity);
        return true;
    }  
    
    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId) 
    {
        return journalEntries.get(myId);
    }

    @DeleteMapping("/id/{myId}")
    public JournalEntry deleteEntryById(@PathVariable ObjectId myId)
    {
        return journalEntries.remove(myId);
    }

    @PutMapping("id/{myId}")
    public JournalEntry putMethodName(@PathVariable ObjectId myId, @RequestBody JournalEntry entity) 
    {
        return journalEntries.put(myId, entity);
    }
    
}
