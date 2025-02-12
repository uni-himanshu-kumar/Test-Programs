package com.example.demo.service;

import com.example.demo.repository.JournalEntryRepository;
import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        try {
            User user = userService.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error has occurred while saving the entry", e);
        }
    }

    public JournalEntry saveEntry(ObjectId id, JournalEntry newJournalEntry, String username){
        Optional<JournalEntry> existingJournalEntry = journalEntryRepository.findById(id);
        if(existingJournalEntry.isPresent()){
            JournalEntry journalEntry = existingJournalEntry.get();
            if (newJournalEntry.getTitle() != null && !newJournalEntry.getTitle().isEmpty()) {
                journalEntry.setTitle(newJournalEntry.getTitle());
            }
            if (newJournalEntry.getContent() != null && !newJournalEntry.getContent().isEmpty()) {
                journalEntry.setContent(newJournalEntry.getContent());
            }
            journalEntryRepository.save(journalEntry);
            return journalEntry;
        }
        return null;
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public JournalEntry findById(ObjectId id){
        Optional<JournalEntry> byId = journalEntryRepository.findById(id);
        return byId.orElse(null);
    }

    public void deleteById(String username, ObjectId id){
        User user = userService.findByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}
