package org.basesource.cakemake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import org.basesource.cakemake.domain.Cake;
import org.basesource.cakemake.repository.CakeRepository;

@Component
public class DemoCakeLoader implements CommandLineRunner {

	@Autowired
    private CakeRepository cakes;

	@Override
	public void run(String... args) throws Exception {
		this.cakes.saveAll(Arrays.asList(
			new Cake("Super #1", "https://images.pexels.com/photos/291528/pexels-photo-291528.jpeg?auto=compress&cs=tinysrgb&h=500", "Super duper cake.."),
			new Cake("Uber #2", "https://images.pexels.com/photos/806363/pexels-photo-806363.jpeg?auto=compress&cs=tinysrgb&h=500", "Uber super cake.."),
			new Cake("Yumma #3", "https://images.pexels.com/photos/1028714/pexels-photo-1028714.jpeg?auto=compress&cs=tinysrgb&h=500", "Yumma mumma cake.."),
			new Cake("Zumba #4", "https://images.pexels.com/photos/461431/pexels-photo-461431.jpeg?auto=compress&cs=tinysrgb&h=500", "Zumba mumba cake.."),
			new Cake("Lumbo #5", "https://images.pexels.com/photos/45202/brownie-dessert-cake-sweet-45202.jpeg?auto=compress&cs=tinysrgb&h=500", "Lumbo mumbo cake.."),
			new Cake("Loco #5", "https://images.pexels.com/photos/954199/pexels-photo-954199.jpeg?auto=compress&cs=tinysrgb&h=500", "Loco moco cake.."),
			new Cake("Heltho #5", "https://images.pexels.com/photos/1126359/pexels-photo-1126359.jpeg?auto=compress&cs=tinysrgb&h=500", "Heltho meltho cake..")
		));
	}
}