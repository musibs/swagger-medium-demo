package io.codefountain.swagger.controllers;

import io.codefountain.swagger.DonorNotFoundException;
import io.codefountain.swagger.model.Donor;
import io.codefountain.swagger.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    @Autowired
    private DonorRepository donorRepository;

    @PostMapping
    public ResponseEntity<Donor> createDonor(@RequestBody Donor donor){
        try{
            Donor savedDonor = donorRepository.save(donor);
            return new ResponseEntity<Donor>(savedDonor, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<Donor>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public Iterable<Donor> getDonors(){
        return donorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donor> getDonor(@PathVariable("id") Long id){
        Optional donor = donorRepository.findById(id);
        if(!donor.isPresent()){
            return new ResponseEntity<Donor>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Donor>((Donor) donor.get(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Donor> UpdateDonor(@RequestBody Donor donor){
        try{
            Optional findDonor = donorRepository.findById(donor.getDonorId());
            if(!findDonor.isPresent()){
                throw new DonorNotFoundException("Donor with id "+donor.getDonorId()+" is not present");
            }
            Donor savedDonor = donorRepository.save(donor);
            return new ResponseEntity<Donor>(savedDonor, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<Donor>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        donorRepository.deleteById(id);
    }

}
