package com.bank.controller;

import com.bank.model.Bank;
import com.bank.model.Branch;
import com.bank.model.Service;
import com.bank.repository.BankRepository;
import com.bank.repository.BranchRepository;
import com.bank.repository.ServiceRepository;
import com.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping

public class BankController {

    @Autowired
    private BankService bankService;
    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/api/v1/bank/{id}/branches")
    public String getBankBranches(@PathVariable Long id, Model model) {
        Bank bank = bankRepository.findById(id).orElse(null);
        List<Branch> branches = branchRepository.findByCity("Bangalore");
        model.addAttribute("bank", bank);
        model.addAttribute("branches", branches);
        return "bank-branches";
    }

    @GetMapping("/api/v1/bank/{id}/services")
    public String getBankServices(@PathVariable("id") Long bankId, Model model) {
        Bank bank = bankService.getBankById(bankId);
        List<Service> services = bankService.getServicesByBankId(bankId);

        model.addAttribute("bankName", bank != null ? bank.getName() : "Bank not found");
        model.addAttribute("services", services);

        return "services";
    }
}
