package com.lawsystem.lawserver.listener;

import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.service.SequenceGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LawModelListener extends AbstractMongoEventListener<Law> {

    final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Law> event) {
        if(event.getSource().getNumber() < 1) {
            event.getSource().setNumber(sequenceGeneratorService.generateSequence(Law.SEQUENCE_NAME));
        }
    }
}
