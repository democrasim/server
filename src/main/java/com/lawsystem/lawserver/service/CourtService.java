package com.lawsystem.lawserver.service;

import com.lawsystem.lawserver.dto.ProsecutionDto;
import com.lawsystem.lawserver.exception.InvalidProsecution;
import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.Prosecution;
import com.lawsystem.lawserver.model.ProsecutionStatus;
import com.lawsystem.lawserver.model.content.LawContent;
import com.lawsystem.lawserver.model.content.PunishmentContent;
import com.lawsystem.lawserver.repo.LawRepository;
import com.lawsystem.lawserver.repo.MemberRepository;
import com.lawsystem.lawserver.repo.ProsecutionRepository;
import com.lawsystem.lawserver.service.punishment_executors.MainPunishmentExecutor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourtService {
    private MemberRepository memberRepository;
    private LawRepository lawRepository;
    private ProsecutionRepository prosecutionRepository;
    private MainPunishmentExecutor punishmentExecutor;
    private WhatsAppService whatsAppService;

    public Prosecution prosecute(ProsecutionDto prosecutionDto) throws InvalidProsecution {
        Law law = lawRepository.findById(prosecutionDto.getLaw()).orElseThrow(IllegalArgumentException::new);
        if (prosecutionDto.getSection() < 0 || prosecutionDto.getSection() >= law.getContent().size()) {
            throw new InvalidProsecution();
        }
        LawContent content = law.getContent().get(prosecutionDto.getSection());
        if (!(content instanceof PunishmentContent)) {
            throw new InvalidProsecution();
        }
        PunishmentContent punishmentContent = (PunishmentContent) content;
        Prosecution prosecution = new Prosecution(law, prosecutionDto.getSection(), punishmentContent,
                memberRepository.findById(prosecutionDto.getProsecutor()).orElseThrow(IllegalArgumentException::new),
                memberRepository.findById(prosecutionDto.getProsecuted()).orElseThrow(IllegalArgumentException::new),
                prosecutionDto.getInfo(), ProsecutionStatus.IN_PROCESS, false);
        prosecutionRepository.save(prosecution);
        whatsAppService.sendProsecution(prosecution);
        return prosecution;
    }
    public void accept(String prosecutionId){
        Prosecution prosecution=prosecutionRepository.findById(prosecutionId).orElseThrow(IllegalArgumentException::new);
        punishmentExecutor.execute(prosecution.getPunishmentContent().getPunishment(), prosecution.getProsecuted());
        prosecution.setStatus(ProsecutionStatus.ACCEPTED);
        prosecutionRepository.save(prosecution);
        whatsAppService.sendProsecutionDecided(prosecution);
    }
    public void deny(String prosecutionId){
        Prosecution prosecution=prosecutionRepository.findById(prosecutionId).orElseThrow(IllegalArgumentException::new);
        prosecution.setStatus(ProsecutionStatus.DENIED);
        prosecutionRepository.save(prosecution);
        whatsAppService.sendProsecutionDecided(prosecution);
    }
    public void appeal(String prosecutionId){
        Prosecution prosecution=prosecutionRepository.findById(prosecutionId).orElseThrow(IllegalArgumentException::new);
        if (prosecution.isAppealed()){
            throw new IllegalArgumentException();
        }
        prosecution.setAppealed(true);
        prosecutionRepository.save(prosecution);
        whatsAppService.sendProsecutionAppealed(prosecution);
    }
    public void acceptAppeal(String prosecutionId){
        Prosecution prosecution=prosecutionRepository.findById(prosecutionId).orElseThrow(IllegalArgumentException::new);
        if (!prosecution.isAppealed()||prosecution.getStatus()==ProsecutionStatus.IN_PROCESS){
            throw new IllegalArgumentException();
        }
        if (prosecution.getStatus()==ProsecutionStatus.ACCEPTED){
            prosecution.setStatus(ProsecutionStatus.DENIED);
            punishmentExecutor.undo(prosecution.getPunishmentContent().getPunishment(), prosecution.getProsecuted());
        }
        if (prosecution.getStatus()==ProsecutionStatus.DENIED) {
            prosecution.setStatus(ProsecutionStatus.ACCEPTED);
            punishmentExecutor.execute(prosecution.getPunishmentContent().getPunishment(), prosecution.getProsecuted());
        }
        prosecution.setAppealed(false);
        prosecutionRepository.save(prosecution);
        whatsAppService.sendProsecutionAppealedDecided(prosecution);
    }
}
