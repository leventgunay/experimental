package org.basesource.cakemake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.basesource.cakemake.domain.Cake;
import org.basesource.cakemake.domain.User;

import org.basesource.cakemake.repository.UserRepository;
import org.basesource.cakemake.repository.CakeRepository;

@RestController
public class CakeCRUDController {

    @Autowired
    private UserRepository users;

    @Autowired
    private CakeRepository cakes;

    @RequestMapping(path="/api/like", method = RequestMethod.POST)
    public Cake addFavorite(@RequestBody Cake cake) {
        return cake;
    }
}
