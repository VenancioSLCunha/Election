package br.edu.ulbra.election.election.client;

import br.edu.ulbra.election.election.output.v1.CandidateOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CandidateClientService {

    private final CandidateClient candidateClient;

    @Autowired
    public CandidateClientService(CandidateClient candidateClient){

        this.candidateClient = candidateClient;

    }

    public Long getCountElectionById(Long electionId){
        return this.candidateClient.getCountElectionById(electionId);
    }

    public List<CandidateOutput> getAll(){
        return this.candidateClient.getAll();
    }

    public Long getCandidateByCandidateNumber(Long candidateNumber){
        return this.candidateClient.getCandidateByCandidateNumber(candidateNumber);
    }


    @FeignClient(value="candidate-service", url="http://localhost:8082")
    public interface CandidateClient{

        @GetMapping("/v1/candidate/")
        List<CandidateOutput> getAll();

        @GetMapping("/v1/candidate/election/{electionId}")
        Long getCountElectionById(@PathVariable(name = "electionId") Long electionId);

        @GetMapping("/v1/candidate/candidatenumber/{candidateNumber}")
        Long getCandidateByCandidateNumber(@PathVariable(name = "candidateNumber") Long candidateNumber);

    }
}
