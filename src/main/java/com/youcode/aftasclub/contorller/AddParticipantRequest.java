package com.youcode.aftasclub.contorller;

import com.youcode.aftasclub.model.Competition;
import com.youcode.aftasclub.model.Member;

public class AddParticipantRequest {
    private Competition competition;
    private Member member;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}