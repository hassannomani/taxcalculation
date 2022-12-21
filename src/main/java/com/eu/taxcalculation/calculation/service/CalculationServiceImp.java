package com.eu.taxcalculation.calculation.service;

import com.eu.taxcalculation.calculation.entity.Calculation;
import com.eu.taxcalculation.calculation.exception.CalculationNotFoundException;
import com.eu.taxcalculation.calculation.respository.CalculationRepository;
import com.eu.taxcalculation.payment.entity.Payment;
import com.eu.taxcalculation.payment.exception.PaymentNotFoundException;
import com.eu.taxcalculation.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculationServiceImp implements CalculationService{
    @Autowired
    private CalculationRepository calculationRepository;
    @Autowired
    public void CalculationServiceImp( CalculationRepository calculationRepository){
        this.calculationRepository = calculationRepository;
    }

    public Calculation exceptionHandler(Calculation calculation) throws CalculationNotFoundException {
        if(calculation==null)
            throw new CalculationNotFoundException("Calculation not found");
        else return calculation;
    }
    public Calculation getCalculationByUUID(String id) throws CalculationNotFoundException {
        Calculation calculation = calculationRepository.findByUuid(id);
        return exceptionHandler(calculation);
    }
    public Calculation getCalculationByTin(String tin) throws CalculationNotFoundException {
        Calculation calculation = calculationRepository.findByTin(tin);
        return exceptionHandler(calculation);
    }
    public List<Calculation> getAllCalculation(){
        return calculationRepository.findAll();
    }
    public Calculation updateCalculationByTinNo(String tin, Calculation calculation) throws CalculationNotFoundException {
        Calculation calculation1 = calculationRepository.findByTin(tin);

        if(calculation1!=null){
            calculation1.setGender(calculation.getGender());
            calculation1.setSalary(calculation.getSalary());
            calculation1.setInvestment(calculation.getInvestment());
            //calculation1.setTin(calculation1.getSalary());
            calculation1.setFestivalBonus(calculation.getFestivalBonus());
            calculation1.setSourceTax(calculation.getSourceTax());
            calculation1.setHouseRent(calculation.getHouseRent());
            calculation1.setAssessmentYear(calculation.getAssessmentYear());
            calculation1.setAmount(calculation.getAmount());

            calculationRepository.save(calculation1);
            return calculation1;
        }else{
            return exceptionHandler(calculation1);
        }

    }
}
