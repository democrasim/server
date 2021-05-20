package com.lawsystem.lawserver.service;

import java.util.Objects;

import com.lawsystem.lawserver.model.DatabaseSequence;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@AllArgsConstructor
public class SequenceGeneratorService {

    final MongoOperations mongoOperations;

    public long generateSequence(String name) {
        DatabaseSequence sequence = mongoOperations.findAndModify(Query.query(where("_id").is(name)),
                new Update().inc("seq", 1), FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);

        return !Objects.isNull(sequence) ? sequence.getSeq() : 1;
    }

}
