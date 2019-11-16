package io.codefountain.swagger.controllers;

import io.codefountain.swagger.exceptions.DonorAlreadyExistsException;
import io.codefountain.swagger.exceptions.DonorNotFoundException;
import io.codefountain.swagger.exceptions.GenericApiException;
import io.codefountain.swagger.model.Donor;
import io.codefountain.swagger.repository.DonorRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@RestController
@RequestMapping("/api/donors")
@Api(produces = "application/json", value = "Operations pertaining to manager blood donors in the application")
public class DonorController {

    @Autowired
    private DonorRepository donorRepository;

    @PostMapping
    @ApiOperation(value = "Create a new donor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created a new donor"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public Donor createDonor(@RequestBody Donor donor){
        try{
            return donorRepository.save(donor);
        }
        catch(Exception e){
            if( e instanceof ConstraintViolationException){
                throw new DonorAlreadyExistsException(e.getMessage());
            }
            throw new GenericApiException(e.getMessage());
        }
    }

    @GetMapping
    @ApiOperation(value = "View all donors", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all donors"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public Iterable<Donor> getDonors(){
        return donorRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Retrieve specific donor with the supplied donor id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the donor with the supplied id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public Donor getDonor(@PathVariable("id") Long id){
        Optional<Donor> donor = donorRepository.findById(id);
        if(!donor.isPresent()){
            throw new DonorNotFoundException("Donor with id "+id +" does not exists");
        }
        return donor.get();
    }

    @PutMapping
    @ApiOperation(value = "Update a donor information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated donor information"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public Donor UpdateDonor(@RequestBody Donor donor){
        try{
            Optional findDonor = donorRepository.findById(donor.getDonorId());
            if(!findDonor.isPresent()){
                throw new DonorNotFoundException("Donor with id "+donor.getDonorId()+" is not present");
            }
            return donorRepository.save(donor);
        }
        catch(Exception e){
           throw new GenericApiException("Failed to update donor "+donor.getDonorId());
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes specific donor with the supplied donor id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deletes the specific donor"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public void delete(@PathVariable("id") Long id){
        donorRepository.deleteById(id);
    }

}
