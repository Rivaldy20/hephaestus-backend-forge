package com.example.demo_day2.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo_day2.dto.CreateLoanApplicationRequest;
import com.example.demo_day2.dto.LoanApplicationResponse;
import com.example.demo_day2.model.LoanApplication;

@Service
public class LoanApplicationService {

    private Map<Long, LoanApplication> storage = new HashMap<>();
    private Long seq = 1L;

    public LoanApplicationResponse create(CreateLoanApplicationRequest req) {
        LoanApplication loan = new LoanApplication(
                seq,
                req.getCustomerId(),
                req.getLoanAmount(),
                req.getTenorMonth(),
                req.getPurpose(),
                "SUBMITTED");

        storage.put(seq, loan);
        seq++;

        return toResponse(loan);
    }

    public List<LoanApplicationResponse> getAll() {
        return storage.values().stream().map(this::toResponse).toList();
    }

    public LoanApplicationResponse getById(Long id) {
        LoanApplication loan = storage.get(id);

        if (loan == null) {
            throw new RuntimeException("Loan application not found");
        }

        return toResponse(loan);
    }

    public LoanApplicationResponse approve(Long id) {
        LoanApplication loan = storage.get(id);

        if (loan == null)
            throw new RuntimeException("Loan application not found");

        loan.setStatus("APPROVED");
        return toResponse(loan);
    }

    public LoanApplicationResponse reject(Long id) {
        LoanApplication loan = storage.get(id);

        if (loan == null)
            throw new RuntimeException("Loan application not found");

        loan.setStatus("REJECTED");
        return toResponse(loan);
    }

    private LoanApplicationResponse toResponse(LoanApplication loan) {
        LoanApplicationResponse res = new LoanApplicationResponse();
        res.setId(loan.getId());
        res.setCustomerId(loan.getCustomerId());
        res.setLoanAmount(loan.getLoanAmount());
        res.setTenorMonth(loan.getTenorMonth());
        res.setPurpose(loan.getPurpose());
        res.setStatus(loan.getStatus());
        return res;
    }
}
