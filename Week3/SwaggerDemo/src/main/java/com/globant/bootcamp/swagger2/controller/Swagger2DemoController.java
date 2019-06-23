package com.globant.bootcamp.swagger2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.globant.bootcamp.swagger2.model.Glober;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Swagger2DemoController")
@RestController
public class Swagger2DemoController {

	private List<Glober> globers = new ArrayList<>();
	{
		globers.add(new Glober("1", "Alejo", "SP", "COL"));
		globers.add(new Glober("2", "Lucho", "SP", "COL"));
		globers.add(new Glober("3", "William", "BD", "USA"));
	}

	@ApiOperation(value = "Get list of Globers in the System ", response = Iterable.class, tags = "getGlobers")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not authorized!"),
			@ApiResponse(code = 403, message = "Forbidden!!!"),
			@ApiResponse(code = 404, message = "Not found!!!") })

	@RequestMapping(value = "/getGlobers", method = RequestMethod.GET)
	public List<Glober> getGlobers() {
		return globers;
	}

	@ApiOperation(value = "Get specific Glober in the System ", response = Glober.class, tags = "getGlober")
	@RequestMapping(value = "/getGlober/{name}", method = RequestMethod.GET)
	public Glober getGlober(@PathVariable(value = "name") String name) {
		return globers.stream().filter(x -> x.getName().equalsIgnoreCase(name)).collect(Collectors.toList()).get(0);
	}

	@ApiOperation(value = "Get specific Glober By Country in the System ", response = Glober.class, tags = "getGloberByCountry")
	@RequestMapping(value = "/getGloberByCountry/{country}", method = RequestMethod.GET)
	public List<Glober> getGloberByCountry(@PathVariable(value = "country") String country) {
		return globers.stream().filter(x -> x.getCountry().equalsIgnoreCase(country)).collect(Collectors.toList());
	}

	@ApiOperation(value = "Get specific Glober By Studio in the System ",response = Glober.class,tags="getGloberByStudio")
	@RequestMapping(value = "/getGloberByStudio/{studio}", method = RequestMethod.GET)
	public List<Glober> getGloberByStudio(@PathVariable(value = "studio") String studio) {
		return globers.stream().filter(x -> x.getStudio().equalsIgnoreCase(studio)).collect(Collectors.toList());
	}

	@ApiOperation(value = "Add new Glober ",response = Glober.class,tags="addGlober")
	@RequestMapping(value = "/addGlober",method = RequestMethod.POST)
	public void addGlober(@RequestBody Glober glober) {
		globers.add(glober);
	}

	@ApiOperation(value = "Update specific Glober By id ",response = Glober.class,tags="putGlober")
	@RequestMapping(value = "/putGlober/{id}", method = RequestMethod.PUT)
	public void putGlober(@RequestBody Glober glober, @PathVariable(value = "id") String id) {
		Glober myGlober = globers.stream().filter(x -> x.getId().equalsIgnoreCase(id)).collect(Collectors.toList()).get(0);
		myGlober.setName(glober.getName());
		myGlober.setCountry(glober.getCountry());
		myGlober.setStudio(glober.getStudio());
	}

	@ApiOperation(value = "Delete specific Glober By id ",response = Glober.class,tags="deleteGlober")
	@RequestMapping(value = "/deleteGlober/{id}", method = RequestMethod.DELETE)
	public void deleteGlober(@PathVariable(value = "id") String id) {
		Glober myGlober = globers.stream().filter(x -> x.getId().equalsIgnoreCase(id)).collect(Collectors.toList()).get(0);
		globers.remove(myGlober);
	}
}
