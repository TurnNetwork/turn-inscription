package com.turn.inscription.dao.custommapper;

import com.turn.inscription.bean.CustomVote;
import com.turn.inscription.bean.CustomVoteProposal;

import java.util.List;

public interface CustomVoteMapper {
    List<CustomVote> selectAll ();

    CustomVoteProposal selectVotePropal ( String hash );
}
