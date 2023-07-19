package com.example.vootingappjforce.Data;

public class Candidate {
    private int id;
    private String candidate;
    private String votes;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCandidate() {
        return candidate;
    }
    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }
    public String getVotes() {
        return votes;
    }
    public void setVotes(String votes) {
        this.votes = votes;
    }
}