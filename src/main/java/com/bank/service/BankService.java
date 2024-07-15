package com.bank.service;

import com.bank.model.Bank;
import com.bank.model.Service;
import com.bank.repository.BankRepository;
import com.bank.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@org.springframework.stereotype.Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public Bank getBankById(Long id) {
        return bankRepository.findById(id).orElse(null);
    }

    public List<Service> getServicesByBankId(Long bankId) {
        return serviceRepository.findByBankId(bankId);
    }
}
