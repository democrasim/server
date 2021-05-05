package com.lawsystem.lawserver.service;

import com.lawsystem.lawserver.exception.DeadlineUnmetException;
import com.lawsystem.lawserver.exception.LawNotPassesException;
import com.lawsystem.lawserver.exception.UnenforceableLawException;
import com.lawsystem.lawserver.exception.UnregisteredMemberException;
import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.LawStatus;
import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.Prosecution;
import com.lawsystem.lawserver.model.content.PunishmentLawContent;
import com.lawsystem.lawserver.model.content.RequirementContent;
import com.lawsystem.lawserver.repo.LawRepository;
import com.lawsystem.lawserver.repo.MemberRepository;
import com.lawsystem.lawserver.repo.ProsecutionRepository;
import com.lawsystem.lawserver.service.punishment_executors.MainPunishmentExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CourtService {
    private LawRepository lawRepository;
    private MemberRepository memberRepository;
    private ProsecutionRepository prosecutionRepository;
    private MainPunishmentExecutor punishmentExecutor;

    public void prosecute(String law, String prosecutorId, String prosecutedId, String reason) throws UnenforceableLawException, LawNotPassesException, DeadlineUnmetException, UnregisteredMemberException {
        Law lawObject = lawRepository.findById(law).orElseThrow(IllegalArgumentException::new);
        Member prosecuted = memberRepository.findById(prosecutedId).orElseThrow(IllegalArgumentException::new);
        Member prosecutor = memberRepository.findById(prosecutorId).orElseThrow(IllegalArgumentException::new);
        if (!(lawObject.getContent() instanceof PunishmentLawContent)) {
            throw new UnenforceableLawException();
        }
        if (lawObject.getStatus() != LawStatus.PASSED) {
            throw new LawNotPassesException();
        }
        if (lawObject.getContent() instanceof RequirementContent && ((RequirementContent) lawObject.getContent()).getDue().after(new Date())) {
            throw new DeadlineUnmetException();
        }
        if (!prosecuted.isRegistered() || !prosecutor.isRegistered()) {
            throw new UnregisteredMemberException();
        }
        Prosecution prosecution=new Prosecution().setProsecuted(prosecuted).setProsecutor(prosecutor).setReason(reason).setPunishmentLawContent(lawObject);
        prosecutionRepository.save(prosecution);
    }
    public void judgeReply(String prosecutionId, boolean accepted){
        Prosecution prosecution=prosecutionRepository.findById(prosecutionId).orElseThrow(IllegalArgumentException::new);
        if (accepted){
            punishmentExecutor.execute(((PunishmentLawContent)prosecution.getPunishmentLawContent().getContent()).getPunishment(), prosecution.getProsecuted());
        }
        prosecution.setAccepted(accepted);
        prosecutionRepository.save(prosecution);
    }
}
