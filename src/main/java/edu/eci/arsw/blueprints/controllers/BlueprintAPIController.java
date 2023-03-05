/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
    BlueprintsServices service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> controllerGetBlueprints(){

        try {
            String json = new Gson().toJson(service.getAllBlueprints());

            return new ResponseEntity<>(json,HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error"+ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    // busqueda por autor

    @RequestMapping(path = "/{author}", method = RequestMethod.GET)
    public ResponseEntity<?> controllerGetBluePrintsByAuthor(@PathVariable String author){
        try {
            String json = new Gson().toJson(service.getBlueprintsByAuthor(author));
            return new ResponseEntity<>(json,HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error, el autor no existe"+ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // busqueda por autor y plano
    @RequestMapping(path = "/{author}/{bpname}", method = RequestMethod.GET)
    public ResponseEntity<?> controllerGetBluePrintsByName(@PathVariable String author, @PathVariable String bpname){
        try {
            String json = new Gson().toJson(service.getBlueprint(author, bpname));
            return new ResponseEntity<>(json,HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error no existe "+ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    //creacion de nuevos planos
    @RequestMapping(path = "/addBlueprint",method = RequestMethod.POST)
    public ResponseEntity<?> controllerNewBluePrint(@RequestBody Blueprint bp){
        try {
            service.addNewBlueprint(bp);
            return new ResponseEntity<>("Se agrego correctamente",HttpStatus.CREATED);
        } catch (Exception e) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error no se pudo agregar "+e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
    // actualizar un plano
    @RequestMapping(method = RequestMethod.PUT, path = "{author}/{bprint}")
    public ResponseEntity<Blueprint> PutBluePrint(@RequestBody Blueprint blue, @PathVariable("author") String author, @PathVariable("bprint") String bprint){
        try {
            service.setBlueprint(blue, author, bprint);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

}
