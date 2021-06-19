package com.lawsystem.lawserver.util;

import com.lawsystem.lawserver.dto.LawDto;
import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.content.LawContent;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DataConverter {

    private final ModelMapper modelMapper;

    public List<LawDto> lawsToDataTransferObjects(List<Law> laws) {
        return laws.stream()
                .map(law -> modelMapper.map(law, LawDto.class).setContent(law.getContent()))
                .peek(law -> law.setContentString(law.getContent().toString()))
                .collect(Collectors.toList());
    }

    public List<LawDto> lawsToDataTransferObjectsById(List<Law> laws, String userId) {
        return laws.stream()
                .map(law -> modelMapper.map(law, LawDto.class).setContent(law.getContent()))
                .peek(law -> law.setContentString(law.getContent().toString()))
                .peek(law -> law.getVotes().stream().filter(vote -> vote.getVoter().getId().equals(userId)).findAny().ifPresent(law::setUserVote))
                .collect(Collectors.toList());
    }

    public LawDto lawToDataTransferObject(Law law, String userId) {
        LawDto output = modelMapper.map(law, LawDto.class).setContent(law.getContent());
        output.setContentString(lawContent(law));
        if(userId != null) {
            output.getVotes().stream().filter(vote -> vote.getVoter().getId().equals(userId)).findAny().ifPresent(output::setUserVote);
        }
        return output;
    }


    public LawDto lawToDataTransferObject(Law law) {
        return lawToDataTransferObject(law, "");
    }


    public String lawContent(Law law) {

        StringBuilder output = new StringBuilder(StringUtils.isEmpty(law.getTitle()) ? "" : (law.getTitle() + "\n"));
        for (int i = 0; i < law.getContent().size(); i++) {
            LawContent section = law.getContent().get(i);

            output.append("§1.").append(i + 1).append(" ").append(section.toString());
        }
        return output.toString();
    }
}
