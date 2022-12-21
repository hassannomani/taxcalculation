package com.eu.taxcalculation.calculation.controller;

import com.eu.taxcalculation.calculation.entity.Calculation;
import com.eu.taxcalculation.calculation.exception.CalculationNotFoundException;
import com.eu.taxcalculation.calculation.respository.CalculationRepository;
import com.eu.taxcalculation.calculation.service.CalculationService;
import com.eu.taxcalculation.payment.entity.Payment;
import com.eu.taxcalculation.user.config.JwtGeneratorInterface;
import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.exception.UserNotFoundException;
import com.eu.taxcalculation.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculation")
public class CalculationController {
    @Autowired
    private CalculationService  calculationService;

    @Autowired
    private CalculationRepository calculationRepository;
    @Autowired
    public CalculationController(CalculationService calculationService){
        this.calculationService = calculationService;
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveCalculation(@RequestBody Calculation calculation){
        try{
            calculationRepository.save(calculation);
            return new ResponseEntity<>("Posted", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/calculation-tin/{tin}")
    public ResponseEntity<?> getCalculationByTin(@PathVariable String tin){
        try{

            return new ResponseEntity<>(calculationService.getCalculationByTin(tin), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/calculation-uuid/{uuid}")
    public ResponseEntity<?> getCalculationByUUID(@PathVariable String uuid){
        try{
            return new ResponseEntity<>(calculationService.getCalculationByUUID(uuid), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/update/{tin}")
    public ResponseEntity<?> updateCalculation(@PathVariable String tin, @RequestBody Calculation calculation) {
        try {

            Calculation calculation1 = calculationService.updateCalculationByTinNo(tin,calculation);
           /* if(calculation1 == null){
                throw new CalculationNotFoundException("Calculation Not Found");
            }*/
            return new ResponseEntity<>(calculation1, HttpStatus.OK);
        } catch (CalculationNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/all")

    public ResponseEntity<?> GetAllCalculation() {
        return new ResponseEntity<>(calculationService.getAllCalculation(), HttpStatus.OK);
    }
}
